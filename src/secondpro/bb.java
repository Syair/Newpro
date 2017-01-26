package secondpro;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class bb {
    int count = 0;
    long filesize = 0;
    StringBuilder sb;
    FileFilter filter;
    public static void main(String[] args) {
        bb Fred = new bb();
        Fred.sb = new StringBuilder();
        if (args.length < 3){
            System.out.println("Usage:\n" +
                    "  java ca.cmpt213.as1.FileCollector <# collections> <bytes per collection> «\n" +
                    "<input list file>\n" +
                    "Examples:\n" +
                    "  java ca.cmpt213.as1.FileCollector 3 1024 daFiles.txt\n" +
                    "  java ca.cmpt213.as1.FileCollector 10 1048576 c:\\files\\input.txt");
        }
        else{
            File file = new File(args[2]);
            File subFile;
            ArrayList<files> arrFiles = new ArrayList<>();
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    subFile = new File(sc.nextLine());
                    long size = subFile.length();
                    arrFiles.add(new files(subFile,size));
                    //System.out.println(size);
                }
                sc.close();
                java.util.Collections.sort(arrFiles);
                for (files item : arrFiles) {
                    //System.out.println(item.path + "; size: " + item.fileSize);
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // declare and init collections
            System.out.println("Now building collection:\n" +
                    "**************************");
            int collectionSize = Integer.parseInt(args[0]);         //parse collection size
            System.out.println("# Collections: " + collectionSize);
            ArrayList<fileCollections> arrCollections = new ArrayList<>(collectionSize);
            // init
            for (int x = 0; x < collectionSize; x++){
                arrCollections.add(x, new fileCollections(Long.parseLong(args[1])));
            }
            // create the extra collections
            fileCollections extra = new fileCollections(true);

            // start adding files
            int index = 0;
            for (int i = 0; i < arrFiles.size(); ++i){
                java.util.Collections.shuffle(arrCollections);
                while(!arrCollections.get(index).add(arrFiles.get(i))){// while adding ith file into indexth colllection is not success
                    if(index == collectionSize-1){
                        extra.add(arrFiles.get(i));
                        break;
                    }
                    index++;
                }
                index = 0;
            }

            DecimalFormat df = new DecimalFormat("#,##0.00");
            DecimalFormat by = new DecimalFormat("#,###");
            for (int y = 0; y < collectionSize; ++y){
                System.out.println("Collection "+ (y+1) +": "+ df.format(arrCollections.get(y).filesize/1024/1024) + " MiB (" + by.format(arrCollections.get(y).filesize) +" bytes)");
                System.out.println("*****************");
                for(files item: arrCollections.get(y).Infiles){
                    System.out.println(item.path);
                }
                System.out.println();
            }

            System.out.println("Extra files: "+ df.format(extra.filesize/1024/1024) + " MiB (" + by.format(extra.filesize) +" bytes)");
            System.out.println("*****************");
            for(files item: extra.Infiles){
                System.out.println(item.path);
            }
            System.out.println();
        }
    }
}
