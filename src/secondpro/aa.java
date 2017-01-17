package secondpro;
import java.io.File;
import java.io.FileFilter;

/**
 * Created by wu on 1/15/2017.
 */
public class aa {
    //FileFilter filter;
    public static void main(String[] args) {
        aa Fred = new aa();
        System.out.print("This is arg0: " + args[0]);
        File folder = new File(args[0]);
        //Fred.setFilter();
        Fred.lister(folder);
    }

//    public void setFilter(){
//        this.filter = new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.getName().endsWith(".class");
//            }
//        };
//    }
    public void lister(File folder){
        File[] fileList = folder.listFiles();
        for (File subFile : fileList) {
            if (subFile.isDirectory()) {
                lister(subFile);
            } else {
                if (subFile.getName().endsWith(".class")){
                    System.out.println(" sub file: " + subFile.getAbsolutePath());
                }
            }
        }
    }
}
