import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PuzzlePartOne {

    public static void main(String[] args) {

        int totalSignalStrengths = 0;
        int registerX = 1;
        int ciclies = 0;

        try {
            Scanner sc = new Scanner(new File("input.txt"));

            while(sc.hasNextLine()) {

                String cmd = sc.next();
                if (cmd.equals("addx")) {
                    int v = sc.nextInt();
                    for(int i = 0; i < 2; i++) {
                        if((++ciclies-20)%40 == 0) {
                            totalSignalStrengths += ciclies * registerX;
                        }
                    }
                    registerX += v;

                } else {
                    if((++ciclies-20)%40 == 0) {
                        totalSignalStrengths += ciclies * registerX;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(totalSignalStrengths);

    }
}
