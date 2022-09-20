import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        String folderPath = "C:/Users/Work/Downloads";
        File file = new File(folderPath);
        Node root = new Node(file);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator =
                new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);

        System.out.println(root);

        long duration = (System.currentTimeMillis() - start)/1000;
        System.out.println(duration + "seconds");

    }

    public static long getFolderSize(File folder){
        if (folder.isFile()){
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files){
            sum = sum + getFolderSize(file);
        }
        return sum;
    }




}

