import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PuzzlePartTwo {

    static String[] CRT = new String[40*6];
    static String[] sprite = new String[40];

    public static void main(String[] args) {

        int registerX = 1;
        int ciclies = 0;

        initSprite(registerX);

        try {
            Scanner sc = new Scanner(new File("input.txt"));

            while(sc.hasNextLine()) {
                String cmd = sc.next();
                if (cmd.equals("addx")) {
                    int v = sc.nextInt();
                    for(int i = 0; i < 2; i++) {
                        drawSprite(ciclies);
                        ciclies++;
                    }
                    registerX += v;
                    shiftSprite(registerX);

                } else {
                    drawSprite(ciclies);
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

    private static void drawSprite(int ciclies) {

        if(sprite[ciclies%40].equals("#"))
            CRT[ciclies] = "#";
        else
            CRT[ciclies] = " ";

    }
    private static void initSprite(int registerX) {

        for(int i = 0; i < 40; i++)
            sprite[i] = " ";

        for (int i = (registerX - 1); i < 3; i++) {
            sprite[i] = "#";
        }

    }

    private static void shiftSprite(int registerX) {

        initSprite(1);

        while(registerX-1 > 0) {
            for (int i = 39; i > 0; i--) {
                String temp = sprite[i-1];
                sprite[i] = temp;
                sprite[i-1]= ".";
            }
            registerX--;
        }

    }
}
