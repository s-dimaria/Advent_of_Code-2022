import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PuzzlePartTwo {

    private static ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
    private static ArrayList<String> commands = new ArrayList<>();

    private static int lastPosRock = 0;

    public static void main(String[] args) {

        prepareGame();
        readCommand();
        initializedBoard(Constants.WIDE, "#");

        int cmd = 0;
        int next = 0;

        List<Integer> dy = new ArrayList<>();
        int cycleLength;

        long start = System.currentTimeMillis();

        while ((cycleLength = findCycleLength(dy)) == 0) {
            long y1 = board.stream().filter(x -> x.contains("#")).count()-1;
            System.out.println("====== ROCKS '" + Constants.ROCKS_NAMES.get(next) + "' FALLING ======");
            spawnRocks(next);
            while (true) {

                if (cmd == commands.size())
                    cmd = 0;

                switch (commands.get(cmd)) {
                    case (">"): {
                        /*System.out.println(">");*/
                        shiftRight(next);
                        break;
                    }
                    case ("<"): {
                        /*System.out.println("<");*/
                        shiftLeft(next);
                        break;
                    }
                }

                if (goDown(Constants.ROCKS_NAMES.get(next), next)) {
                    if (next == Constants.ROCKS_NAMES.size() - 1)
                        next = 0;
                    else
                        next++;

                    cmd++;

                    long y2 = board.stream().filter(x -> x.contains("#")).count()-1;
                    dy.add((int) (y2 - y1));

                    break;
                }

                lastPosRock--;
                cmd++;

            }

        }

        printBoard();

        System.out.println(calculateHeight(dy, cycleLength));
        System.out.println((System.currentTimeMillis() - start)/1000.0 + "s");

    }

    private static String calculateHeight(List<Integer> dy, int cycleLength) {

        long rocks = 1000000000000L;

        // Add the heights of the rocks before the cycle starts
        long count = dy.size() - 2L * cycleLength;
        long height = dy.subList(0, (int) count).stream().mapToInt(Integer::intValue).sum();

        rocks -= count;

        // Add the heights of all the full cycles
        dy = dy.subList((int) count, (int) count + cycleLength);
        count = rocks / dy.size();
        height += count * dy.stream().mapToInt(Integer::intValue).sum();
        rocks -= count * dy.size();

        // Add the heights of the last partial cycle
        height += dy.subList(0, (int) rocks).stream().mapToInt(Integer::intValue).sum();

        return String.valueOf(height);
    }

    private static int findCycleLength(List<Integer> sequence) {
        for (int length = commands.size()-1; length < sequence.size() / 2; length++) {
            boolean matches = true;
            for (int i = 0; i < length; i++) {
                if (!Objects.equals(sequence.get(sequence.size() - 2 * length + i), sequence.get(sequence.size() - length + i))) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                return length;
            }
        }
        return 0; // Not found
    }

    private static long resetBoard() {

        ArrayList<Boolean> check = new ArrayList<>();
        for(int i = 0 ; i <Constants.WIDE; i++)
            check.add(false);

        int min = Integer.MAX_VALUE;
        int l = board.size()-1;
        for(int i = l; i > 0 ; i--) {
            for(int j = 0; j < board.get(i).size(); j++) {
                if(board.get(i).get(j).equals("#")) {
                    check.set(j,true);
                    if(i < min)
                        min = i;
                }
                if(check.stream().filter(x -> x == true).count() == Constants.WIDE) {
                    removeArray(min);
                    return (l - (l - min));
                }
            }
        }
        return 0;
    }


    private static void removeArray(int i) {

        while(i > 0) {
            board.remove(i-1);
            i--;
        }

    }

    private static boolean goDown(char r, int next) {

        int p = lastPosRock;
        ArrayList<String> s;

        for (int i = 0; i < Constants.ROCKS_TYPES.get(next).length; i++) {
            p--;
        }

        int x0 = board.get(p + 1).indexOf("@");
        int xn = board.get(p + 1).lastIndexOf("@");

        if(r != '+') {

                int k;
                for (k = x0; k <= xn; k++) {
                    if (!board.get(p).get(k).equals("."))
                        break;
                }

                if (k == xn + 1) {
                    for(int y = p+1; y <= lastPosRock; y++) {

                        x0 = board.get(y).indexOf("@");
                        xn = board.get(y).lastIndexOf("@");

                        for (int h = x0; h <= xn; h++) {
                            board.get(p).set(h, "@");
                            board.get(p + 1).set(h, ".");
                        }
                        p++;
                    }


                } else {
                    for(int y = p+1; y <= lastPosRock; y++) {

                        x0 = board.get(y).indexOf("@");
                        xn = board.get(y).lastIndexOf("@");

                        for (int h = x0; h <= xn; h++)
                            board.get(y).set(h, "#");
                    }

                    return true;

                }

            return false;
        }
        else {

            if(board.get(p+1).get(x0-1).equals(".")
                    && board.get(p+1).get(x0+1).equals(".")
                    && board.get(p).get(x0).equals(".")) {

                for(int y = p+1; y <= lastPosRock; y++) {

                    x0 = board.get(y).indexOf("@");
                    xn = board.get(y).lastIndexOf("@");

                    for (int h = x0; h <= xn; h++) {
                        board.get(p).set(h, "@");
                        board.get(p + 1).set(h, ".");
                    }
                    p++;
                }

                return false;
            }
            else {

                for(int y = p+1; y <= lastPosRock; y++) {

                    x0 = board.get(y).indexOf("@");
                    xn = board.get(y).lastIndexOf("@");

                    for (int h = x0; h <= xn; h++)
                        board.get(y).set(h, "#");
                }

                return true;
            }
        }

    }

    private static void shiftRight(int next) {

        ArrayList<String> s;
        try {
            for(int i = 0; i < Constants.ROCKS_TYPES.get(next).length; i++) {
                if(!board.get(lastPosRock - i).get(board.get(lastPosRock - i).lastIndexOf("@") + 1).equals("."))
                    return;
            }

            for(int i = 0; i < Constants.ROCKS_TYPES.get(next).length; i++) {

                s = board.get(lastPosRock - i);

                int x0 = s.indexOf("@");
                int xn = s.lastIndexOf("@");

                for (int k = xn; k >= x0; k--) {
                    s.set(k + 1, s.get(k));
                }
                s.set(x0, ".");

                board.set(lastPosRock-i, s);
            }
        } catch (IndexOutOfBoundsException e) {}

    }

    private static void shiftLeft(int next) {

        ArrayList<String> s;

        try {
            for(int i = 0; i < Constants.ROCKS_TYPES.get(next).length; i++) {
                if(!board.get(lastPosRock - i).get(board.get(lastPosRock - i).indexOf("@") - 1).equals("."))
                    return;
            }

            for(int i = 0; i < Constants.ROCKS_TYPES.get(next).length; i++) {

                s = board.get(lastPosRock - i);

                int x0 = s.indexOf("@");
                int xn = s.lastIndexOf("@");

                for (int k = x0; k <= xn; k++) {
                    s.set(k - 1, s.get(k));
                }
                s.set(xn, ".");

                board.set(lastPosRock-i, s);
            }
        } catch (IndexOutOfBoundsException e) {}

    }

    private static void spawnRocks(int next) {

        int i;
        for(i = 0; i < board.size()-1; i++) {
            if(!board.get(i).contains("#"))
                break;
        }

        if(i != 0) {
            for (int j = board.size() - 1; j >= i; j--) {
                board.remove(j);
            }
        }

        for(int k = 0; k < 3; k++) {
            initializedBoard(Constants.WIDE, ".");
        }

        ArrayList<ArrayList<String>> fallRock = new ArrayList<>();

        for (int k = 0; k < Constants.ROCKS_TYPES.get(next).length; k++) {
            ArrayList<String> t = new ArrayList<>();
            for (int j = 0; j < Constants.ROCKS_TYPES.get(next)[0].length; j++) {
                t.add(String.valueOf(Constants.ROCKS_TYPES.get(next)[k][j]));
            }
            fallRock.add(t);
        }

        for(int k = fallRock.size()-1; k >= 0 ; k--)
            board.add(fallRock.get(k));

        lastPosRock = board.size()-1;

    }


    private static ArrayList<String> initializedBoard(int wide, String line) {
        ArrayList<String> ground = new ArrayList<>();

        for(int i = 0 ; i < wide; i++)
            ground.add(line);

        board.add(ground);

        return ground;
    }

    private static void readCommand(){
        try {
            BufferedReader bf = new BufferedReader(new FileReader("input.txt"));

            while(bf.ready())
                commands.add(String.valueOf((char)bf.read()));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void prepareGame() {

        Constants.initializedTypes();
        System.out.println("======= TYPE OF ROCKS =======");
        for(int k = 0 ; k < Constants.ROCKS_TYPES.size(); k++) {
            for (int i = 0; i < Constants.ROCKS_TYPES.get(k).length; i++) {
                for (int j = 0; j < Constants.ROCKS_TYPES.get(k)[0].length; j++)
                    System.out.print("\t" + Constants.ROCKS_TYPES.get(k)[i][j]);
                System.out.println("");
            }
            System.out.println("");
        }
        System.out.println("======= TYPE OF ROCKS =======");
    }

    private static void printBoard() {
        for (int k = board.size() - 1; k >= 0; k--) {
            System.out.print(board.get(k));
            System.out.println("");
        }
        System.out.println("");
    }
}
