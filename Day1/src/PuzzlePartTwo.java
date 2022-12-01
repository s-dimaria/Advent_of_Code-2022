import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PuzzlePartTwo {

    public static void shift(int[] max, int s, int p) {

        for(int i = max.length-1 ; i > p; i--) {
            max[i] = max[i-1];
        }
        max[p] = s;

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));

        int max[] = new int[3];
        int sum = 0;

        while(sc.hasNext()) {
            String s = sc.nextLine();
            if(s.equals("")) {
                for(int i=0 ; i < max.length; i++) {
                    if(max[i] <= sum) {
                        shift(max,sum,i);
                        break;
                    }
                }
                sum = 0;
            }
            else {
                sum = sum + Integer.parseInt(s);
            }

        }

        System.out.println(Arrays.stream(max).sum());


    }
}
