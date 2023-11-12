import java.io.*;
import java.security.*;

public class ClassManipInnerPrivateClassAccess {

    public static void main(String[] args) throws Exception {
        DoNotTerminate.forbidExit();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(br.readLine().trim());
            Object o;// Must be used to hold the reference of the instance of the class
                     // ClassManipInnerPrivateClassAccess.Inner.Private

            // Access inner private class
            o = new Inner().new Private();
            System.out.println(num + " is " + ((ClassManipInnerPrivateClassAccess.Inner.Private) o).powerof2(num));

            // Alternate Solution
            // o = new Object();
            // ClassManipInnerPrivateClassAccess.Inner instance = new
            // ClassManipInnerPrivateClassAccess.Inner();
            // Inner.Private inner = instance.new Private();
            // o = (Object) inner;
            // System.out.println(num + " is " + inner.powerof2(num)) ;

            // Alternate one line solution
            // System.out.println(num + " is " +
            // ((ClassManipInnerPrivateClassAccess.Inner.Private)(o = (Object) new
            // ClassManipInnerPrivateClassAccess.Inner().new Private())).powerof2(num));

            // Alternate solution using java reflection
            // System.out.println(String.format("%d is %s", num, new Inner().new
            // Private().powerof2(num)));

            System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");

        } // end of try

        catch (DoNotTerminate.ExitTrappedException e) {
            System.out.println("Unsuccessful Termination!!");
        }
    }// end of main

    static class Inner {
        private class Private {
            private String powerof2(int num) {
                return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
            }
        }
    }// end of Inner

}// end of ClassManipInnerPrivateClassAccess

class DoNotTerminate { // This class prevents exit(0)

    public static class ExitTrappedException extends SecurityException {

        private static final long serialVersionUID = 1L;
    }

    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}
