package ModernJavaInAction;

import java.io.File;
import java.io.FileFilter;

public class FileReader {
    File[] files;

    public FileReader() {
        files = acquireFilesNewWay(".");
    }

    private File[] acquireFilesOldWay(String path) {
        return new File(path).listFiles(
            new FileFilter() {
                public boolean accept(File file) {
                    return file.isHidden();
                }
            }
        );
    }

    // NOTE: It is not clear how to check for invert of a function
    // i.e. we are not allowed to do File::!isHidden
    private File[] acquireFilesNewWay(String path) {
        return new File(path).listFiles(File::isHidden);
    }

    public File[] getFiles() {
        return files;
    }
}