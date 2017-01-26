package secondpro;

import java.io.File;

/**
 * Created by Yang on 2017/1/25.
 */
public class files implements Comparable<files> {
    File path;
    long fileSize;

    public files(File subFile, long size) {
        this.path = subFile;
        this.fileSize = size;

    }

    @Override
    public int compareTo(files other) {
        return Long.compare(other.fileSize,fileSize);
    }
}
