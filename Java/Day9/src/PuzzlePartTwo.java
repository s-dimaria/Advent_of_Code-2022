import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;


public class PuzzlePartTwo {

    static HashSet<Knots> posTail = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {

        final int x = 0;
        final int y = 0;

        final int length = 10;

        Knots[] knots = new Knots[length];

        for(int i = 0; i < length ; i++) {
            knots[i] = new Knots(x,y);
        }

        Scanner sc = new Scanner(new File("input.txt"));

        while(sc.hasNext()) {
            String cmd = sc.next();
            int move = sc.nextInt();

            while( move > 0) {
                headMove(knots,cmd);
                move-=1;
            }
        }

        System.out.println("Move of tail: " + (posTail.size()));
    }

    private static void headMove(Knots[] knots, String cmd) {

            if (cmd.equals("L") || cmd.equals("R")) {
                knots[(knots.length)-1].setX(knots[(knots.length)-1].getX() - (cmd.equals("L") ? 1 : -1));
                for(int i = (knots.length)-1; i > 0 ; i--) {
                    Knots head = knots[i];
                    Knots tail = knots[i - 1];
                    tailMove(head, tail);
                }
            } else {
                knots[(knots.length)-1].setY(knots[(knots.length)-1].getY() - (cmd.equals("U") ? 1 : -1));
                for(int i = (knots.length)-1; i > 0 ; i--) {
                    Knots head = knots[i];
                    Knots tail = knots[i - 1];
                    tailMove(head, tail);
                }
            }
        posTail.add(new Knots(knots[0].getX(), knots[0].getY()));
    }


    private static void tailMove(Knots head, Knots tail) {

         if (Math.abs(head.getY() - tail.getY()) == 2) {
             if((head.getY() - tail.getY()) > 0)
                tail.setY(tail.getY() + 1);
             else
                 tail.setY(tail.getY() - 1);

            if(head.getX() - tail.getX() == 2)
                tail.setX(tail.getX() + 1);

            else if (head.getX() - tail.getX() == -2)
                tail.setX(tail.getX() - 1);

            else if(head.getX() - tail.getX() == 1)
                tail.setX(tail.getX() + (head.getX() - tail.getX()));
            else
                tail.setX(tail.getX() - Math.abs(head.getX() - tail.getX()));

        }

        if (Math.abs(head.getX() - tail.getX()) == 2) {
            if((head.getX() - tail.getX()) > 0)
                tail.setX(tail.getX() + 1);
            else
                tail.setX(tail.getX() - 1);

            if (head.getY() - tail.getY() == 1)
                tail.setY(tail.getY() + (head.getY() - tail.getY()));
            else
                tail.setY(tail.getY() - Math.abs(head.getY() - tail.getY()));

        }

    }

}
