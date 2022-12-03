import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PuzzlePartOne {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));

        int max = 0;
        int sum = 0;

        while(sc.hasNext()) {
            String s = sc.nextLine();
            if(s.equals("")) {
                if(max < sum) {
                    max = sum;
                }
                sum = 0;
            }
            else {
                sum = sum + Integer.parseInt(s);
            }

        }

        System.out.println(max);


    }
}
