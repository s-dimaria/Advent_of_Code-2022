import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartOne {

    static TreeMap<Integer, ArrayList<Character>> path = new TreeMap<>();

    static int lastX = -1;
    static int lastY = -1;

    static final int START_SAND = 500;

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while(sc.hasNextLine()) {
                String line = sc.nextLine().replaceAll(" ->", "");
                StringTokenizer strLine = new StringTokenizer(line);

                while (strLine.hasMoreTokens()) {
                    StringTokenizer xy = new StringTokenizer(strLine.nextToken(),",");

                    int x = Integer.parseInt(xy.nextToken());
                    int y = Integer.parseInt(xy.nextToken());
                    createArray(x,y);
                    createConnection(x,y);
                    lastX = x;
                    lastY = y;
                }

                lastX = -1;
                lastY = -1;

            }

            for(Integer i: path.keySet()) {
                if(i == START_SAND) {
                    path.get(i).set(0, '+');
                }

            }

            System.out.println("-----Print Map of Rock-----");
            for(Integer i: path.keySet())
                System.out.println(i + " " + path.get(i));

            System.out.println("\nSand falling into the endless void after: " +  startFall());


            System.out.println("\n-----Print Map with drawn Sand-----");

            for(Integer k: path.keySet())
                System.out.println(k + " " + path.get(k));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private static int startFall() {

        ArrayList<Character> start;
        boolean go = true;

        int count = 0;
        while(go) {

            try {
                int temp = START_SAND;
                start = path.get(START_SAND);
                for (int i = 0; i < start.size() - 1; i++) {

                    if (start.get(i + 1) == '.');
                    else if (path.get(temp - 1).get(i + 1) == '.') {
                        start = path.get(temp - 1);
                        temp--;
                    } else if (path.get(temp + 1).get(i + 1) == '.') {
                        start = path.get(temp + 1);
                        temp++;
                    } else {
                        start.set(i, 'o');
                        break;
                    }

                }
                count++;

            }catch(NullPointerException | IndexOutOfBoundsException e) {
                return count;
            }
        }
        return 0;
    }


    private static void createConnection(int x, int y) {

        ArrayList<Character> temp;
        if(lastX != -1 && lastY != -1) {
            if (lastX != x) {
                for (int i = Math.min(lastX, x); i <= Math.max(lastX, x); i++) {
                    if (!path.containsKey(i))
                        createArray(i, y);
                    else {
                        temp = path.get(i);

                        if(temp.size() > y)
                            temp.set(y,'#');
                        else {
                            for (int k = temp.size(); k < y; k++)
                                temp.add(k, '.');
                            temp.add(y,'#');
                        }
                    }
                }
            } else {
                temp = path.get(x);

                if (lastY < y)
                    for (int i = lastY; i < y; i++)
                        temp.set(i, '#');
                else
                    for (int i = y; i < lastY; i++)
                        temp.set(i, '#');
            }
        }


    }
    private static void createArray(int x, int y) {

        ArrayList<Character> temp;

        if(!path.containsKey(x)) {
            temp = new ArrayList<Character>();

            for (int i = 0; i < y; i++)
                temp.add('.');
            temp.add('#');

            path.put(x, temp);
        }

        else {
            temp = path.get(x);

            if(temp.size() > y)
                temp.set(y,'#');
            else {
                for (int i = temp.size(); i < y; i++)
                    temp.add(i, '.');
                temp.add(y,'#');
            }
            path.put(x, temp);
        }
    }
}
