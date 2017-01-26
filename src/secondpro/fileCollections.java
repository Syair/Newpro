package secondpro;

import java.util.ArrayList;

public class fileCollections {
    long RemainSize;
    boolean extra;
    ArrayList<files> Infiles;
    long filesize;
    // constructor with size
    public fileCollections(long size) {
        RemainSize = size;
        Infiles = new ArrayList<>();
    }

    // constructor with bool
    public fileCollections(boolean b) {
        if (b == true){
            extra = true;
        }
        Infiles = new ArrayList<>();
    }

    public boolean add(files file) {
        if (this.extra == true){
            Infiles.add(file);
            filesize = filesize + file.fileSize;
            return true;
        }
        else {
            if (file.fileSize > RemainSize) {
                return false;
            } else {
                RemainSize = RemainSize - file.fileSize;
                Infiles.add(file);
                filesize = filesize + file.fileSize;
                return true;
            }
        }
    }
}
