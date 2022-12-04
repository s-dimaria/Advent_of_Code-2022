import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class PuzzlePartOne {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("input.txt"));

        int count = 0;

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            boolean[] arr = new boolean[100];

            StringTokenizer tok = new StringTokenizer(line, ",");

            StringTokenizer range1 = new StringTokenizer(tok.nextToken(), "-");

            int range1In = parseInt(range1.nextToken());
            int range1Out = parseInt(range1.nextToken());

            StringTokenizer range2 = new StringTokenizer(tok.nextToken(), "-");

            int range2In = parseInt(range2.nextToken());
            int range2Out = parseInt(range2.nextToken());

            if(range1In >= range2In && range1Out <= range2Out) {
                for (int i = range2In; i <= range2Out; i++) {
                    arr[i] = true;
                }
                if (arr[range1In]) {
                    if (arr[range1Out]) {
                        count += 1;
                    }
                }
            }
            else {
                for (int i = range1In; i <= range1Out; i++) {
                    arr[i] = true;
                }
                if (arr[range2In]) {
                    if (arr[range2Out]) {
                        count += 1;
                    }
                }
            }

        }

        System.out.println(count);

    }
}
