package AdventOfCode2022.Problem7;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DirSpaceCalc {

   private static int ansTotalLimit = 0;
   private static int limitToAns = 100000;
   private static int neededUnusedSpace = 30000000;
   private static int totalDiskSpace = 70000000;
   private static int currentUnusedSpace = totalDiskSpace - 47870454; // 47870454 from problem 1
   private static int currentSmallestDirectory = totalDiskSpace;

   // Create file
   static class DirFile {
      int size;
      String fileName;

      DirFile(int size, String fileName) {
         this.size = size;
         this.fileName = fileName;
      }

      String fileToString() {
         return "fileName = " + fileName + " with size " + size;
      }
   }

   // Create Directory
   static class Directory {
      Directory parentDir;
      String name;
      List<Directory> subDirs = new ArrayList<Directory>();
      List<DirFile> files = new ArrayList<DirFile>();

      Directory(Directory parentDir, String name) {
         this.parentDir = parentDir;
         this.name = name;
      }

      String dirToString() {
         String listSubdirs = "none";
         if (!subDirs.isEmpty()) {
            StringBuilder listSubdirsSB = new StringBuilder();
            for (Directory subDir : subDirs) {
               listSubdirsSB.append(subDir.name + " and ");
            }
            listSubdirs = listSubdirsSB.toString();
         }
         String listFilesAns = "none";
         if (!files.isEmpty()) {
            StringBuilder listFiles = new StringBuilder();
            for (DirFile dirFile : files) {
               listFiles.append(dirFile.fileToString() + " and ");
            }
            listFilesAns = listFiles.toString();
         }

         String parentName = (parentDir == null) ? "none" : parentDir.name;
         return " Directory = " + name + " with parent = " + parentName + " and subdirs = " + listSubdirs
               + " and files = " + listFilesAns + " end dirToString";
      }

      int getDirSizeExcludingSubDirs() {
         int total = 0;
         if (!files.isEmpty()) {
            for (DirFile dirFile : files) {
               total += dirFile.size;
            }
         }
         return total;
      }

      Directory getSubDir(String name) {
         for (Directory subDir : subDirs) {
            if (subDir.name.equals(name))
               return subDir;
         }
         return null;
      }
   }

   public static void main(String[] args) throws FileNotFoundException {
      URL path = DirSpaceCalc.class.getResource("cmdinput.txt");
      File file = new File(path.getFile());

      try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {

         Directory currentDir = null;
         Directory wholeDirectory = null;
         while (sc.hasNextLine()) {
            String command = sc.nextLine();
            System.out.println("Line value = " + command);

            String cdCommand = "$ cd ";
            if (command.startsWith(cdCommand)) {
               int afterCd = cdCommand.length();
               if (command.charAt(afterCd) == '/') {
                  wholeDirectory = new Directory(null, "/");
                  currentDir = wholeDirectory;
               } else if (command.charAt(afterCd) == '.') {
                  currentDir = currentDir.parentDir;
                  System.out.println("currentDir now = " + currentDir.dirToString());
               } else {
                  String directoryMentioned = command.substring(afterCd);
                  System.out.println("directoryMentioned = " + directoryMentioned);
                  currentDir = currentDir.getSubDir(directoryMentioned);
                  System.out.println("currentDir now = " + currentDir.dirToString());
               }
            } else if (command.startsWith("$ ls")) {
               while (sc.hasNextLine() && !sc.hasNext("\\$")) {
                  String nextCommand = sc.nextLine();
                  System.out.println("nextCommand = " + nextCommand);
                  if (nextCommand.startsWith("dir ")) {
                     String[] dirInfo = nextCommand.split(" ");
                     Directory newSubDir = new Directory(currentDir, dirInfo[1]);
                     currentDir.subDirs.add(newSubDir);
                     System.out.println("add Directory = " + newSubDir.name
                           + " to current directory = " + currentDir.dirToString());
                  } else {
                     String[] fileInfo = nextCommand.split(" ");
                     currentDir.files.add(new DirFile(Integer.valueOf(fileInfo[0]), fileInfo[1]));
                     System.out.println("file list add filename = " + fileInfo[1] + " with size = " + fileInfo[0]
                           + " to current directory = " + currentDir.dirToString());
                  }
               }
            }
         }
         System.out.println("wholeDirectory as a whole = " + recursivelyPrintDirectory(wholeDirectory).toString());
         System.out.println("get total size of whole directory = " + recursivelyGetAllSubDirSize(wholeDirectory));
         System.out.println("get sum of the total sizes of directories with a total size of at most 100000 = "
               + ansTotalLimit); // problem 1
         System.out
               .println("Smallest Dir Size to delete to make " + currentUnusedSpace + " become " + neededUnusedSpace +
                     " of space = " + currentSmallestDirectory); // problem 2
      }
   }

   static int recursivelyGetAllSubDirSize(Directory directory) {
      int recurseSubTotal = 0;

      for (DirFile dirfile : directory.files) {
         recurseSubTotal += dirfile.size;
      }

      System.out.println("Size of Directory = " + directory.name + " without subdirectories = " + recurseSubTotal);

      for (Directory subdir : directory.subDirs) {
         recurseSubTotal += recursivelyGetAllSubDirSize(subdir);
      }

      System.out.println("Total Size of Directory = " + directory.name + " = " + recurseSubTotal);

      // Problem 1
      if (recurseSubTotal <= limitToAns) {
         ansTotalLimit += recurseSubTotal;
      }

      // Problem 2
      if (recurseSubTotal < currentSmallestDirectory && recurseSubTotal >= (neededUnusedSpace - currentUnusedSpace)) {
         System.out.println("Current smallest Directory = " + directory.name + " with size = " + recurseSubTotal);
         currentSmallestDirectory = recurseSubTotal;
      }

      return recurseSubTotal;
   }

   static StringBuilder recursivelyPrintDirectory(Directory directory) {
      StringBuilder recursePrint = new StringBuilder();

      recursePrint.append(directory.dirToString());

      for (Directory subdir : directory.subDirs) {
         recursePrint.append(recursivelyPrintDirectory(subdir));
      }
      return recursePrint;
   }
}
