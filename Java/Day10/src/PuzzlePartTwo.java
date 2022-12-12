import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PuzzlePartTwo {

    static String[] CRT = new String[40*6];

    public static void main(String[] args) {

        int registerX = 1;
        int ciclies = 0;

        try {
            Scanner sc = new Scanner(new File("input.txt"));

            while(sc.hasNextLine()) {
                String cmd = sc.next();
                if (cmd.equals("addx")) {
                    int v = sc.nextInt();
                    for(int i = 0; i < 2; i++) {
                        if((ciclies%40) >= (registerX-1) && (ciclies%40) <= (registerX+1))
                            CRT[ciclies] = "#";
                        else
                            CRT[ciclies] = " ";
                        ciclies++;
                    }
                    registerX += v;

                } else {
                    if((ciclies%40) >= (registerX-1) && (ciclies%40) <= (registerX+1))
                        CRT[ciclies] = "#";
                    else
                        CRT[ciclies] = " ";
                    ciclies++;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(int i = 0 ; i < 240; i++) {
            System.out.print(CRT[i]);
            if((i+1)%40 == 0)
                System.out.println("");
        }
    }

}
