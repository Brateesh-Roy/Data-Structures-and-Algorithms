import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tester {
    public static void main(String args[]){
        try {
            FileInputStream fstream = new FileInputStream("webpages/stacklighting");
            Scanner s = new Scanner(fstream);
            String pageprocessor = new String();
            while (s.hasNext()) {
                pageprocessor = pageprocessor + s.nextLine();
            }
            System.out.println(pageprocessor);
        }
        catch (FileNotFoundException e){
            System.out.println("notfound");
        }
    }
}
