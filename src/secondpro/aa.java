package secondpro;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class aa {
    int count = 0;
    long filesize = 0;
    StringBuilder sb;
    FileFilter filter;
    public static void main(String[] args) {
        aa Fred = new aa();
        Fred.sb = new StringBuilder();
        if (args.length < 2){
            System.out.println("Usage:\n" +
                    "    java FileLister <source folder> <target file> <filters>\n" +
                    "Examples:\n" +
                    "    java ca.cmpt213.as1.FileLister C:\\Music\\ C:\\list.txt .mp3\n" +
                    "    java ca.cmpt213.as1.FileLister C:\\ C:\\test\\files.txt .mp3 .jpg");
        }
        else{

            File folder = new File(args[0]);
            File targetFile = new File(args[1]);
            Fred.setFilter(args);
            DecimalFormat df = new DecimalFormat("#,##0.00");
            DecimalFormat by = new DecimalFormat("#,###");
            System.out.println("Statistics on Files Found:");
            System.out.println("**************************");
            System.out.println("Source Path: " + args[0]);//C:\Test\Tree
            System.out.println("Target Path: " + args[1]);//C:\Test\TreeFiles.txt
            System.out.println("Extensions:");
            Fred.lister(folder);
            System.out.println("Files Found: " + Fred.count);//14
            //long mibSize = Fred.filesize/1024L/1024L; //leave it to 2 deci
            System.out.println("Total size: "+ df.format(Fred.filesize/1024/1024) + " MiB (" + by.format(Fred.filesize) +" bytes)");
            System.out.println("Files:");
            System.out.println("*****************");
            System.out.println(Fred.sb);
            try {
                PrintWriter writer = new PrintWriter(targetFile);
                System.out.println("Writing file list to output file: "+ args[1]);
                writer.print(Fred.sb);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    public void setFilter(String[] args){
        this.filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (args.length == 2){
                    return true;
                }
                for (int i = 2; i < args.length; ++i){
                    if (pathname.getName().endsWith(args[i])){
                        return true;
                    }
                }
                return false;
                //return pathname.getName().endsWith(".class");
            }
        };
    }

    public void lister(File folder){
        File[] fileList = folder.listFiles(this.filter);
        for (File subFile : fileList) {
            if (subFile.isDirectory()) {
                lister(subFile);
            } else {
                    this.count ++;
                    filesize = filesize + subFile.length();
                    this.sb.append(subFile.getAbsolutePath());
                    this.sb.append('\n');
            }
        }
    }
}
