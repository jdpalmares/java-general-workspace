import java.io.File;

public class GetFilenameDeepestDir {
    static int deepestLevel = 0;
    static String deepestItem = "";
    static String deepestFilename = "";
    static String deepestPath = "";

    public static void main(String[] args) {
        String pathToDir = "D:\\Codes\\JavascriptNodejsWorkspace\\babelTutorial";
        File baseDir = new File(pathToDir);
        System.out.println("Deepest file = " + findDeepestItem(baseDir));
    }

    static public String findDeepestItem(File folder) {
        int currentLevel = 0;

        String result = "";
        File[] folderEntries = folder.listFiles();

        for (File entry : folderEntries) {
            currentLevel = countMatches(entry.getPath(), "\\");
            if (entry.isDirectory()) {
                findDeepestItem(entry);
                if (deepestLevel < currentLevel) {
                    deepestLevel = currentLevel;
                    deepestItem = entry.getPath();
                    deepestFilename = entry.getName();
                    deepestPath = entry.getAbsolutePath().substring(0, entry.getAbsolutePath().lastIndexOf("\\") + 1);
                }
                continue;
            } else {
                if (deepestLevel < currentLevel) {
                    deepestLevel = currentLevel;
                    deepestItem = entry.getPath();
                    deepestFilename = entry.getName();
                    deepestPath = entry.getAbsolutePath().substring(0, entry.getAbsolutePath().lastIndexOf("\\") + 1);
                }
            }
        }
        result = "Level" + String.valueOf(deepestLevel) + System.lineSeparator() + "Full Path:" + deepestItem
                + System.lineSeparator() + "File name only:" + deepestFilename + System.lineSeparator()
                + "File Path only:" + deepestPath;
        return result;
    }

    private static int countMatches(String path, String string) {
        int number = 0;
        String[] array = path.split("");
        for (int i = 0; i < path.length(); i++) {
            if (string.contentEquals(array[i])) {
                number++;
            }
        }
        return number;
    }
}