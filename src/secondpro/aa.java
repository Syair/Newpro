package secondpro;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 * Created by wu on 1/15/2017.
 */
public class aa {
    int count = 0;
    long filesize = 0;
    StringBuilder sb;
    FileFilter filter;
    public static void main(String[] args) {
        aa Fred = new aa();
        Fred.sb = new StringBuilder();
        File folder = new File(args[0]);
        File targetFile = new File(args[1]);
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


        //Fred.setFilter();
    }

    public void setFilter(){
        this.filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".class");
            }
        };
    }

    public void lister(File folder){
        File[] fileList = folder.listFiles(this.filter);
        for (File subFile : fileList) {
            if (subFile.isDirectory()) {
                lister(subFile);
            } else {
                //if (subFile.getName().endsWith(".class")){
                    this.count ++;
                    filesize = filesize + subFile.length();
                    this.sb.append(subFile.getAbsolutePath());//System.out.println(subFile.getAbsolutePath());
                    this.sb.append('\n');
                //}
            }
        }
    }
}
