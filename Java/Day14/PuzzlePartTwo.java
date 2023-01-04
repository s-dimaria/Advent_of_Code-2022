import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartTwo {

    static TreeMap<Integer, ArrayList<Character>> path = new TreeMap<>();

    static int lastX = -1;
    static int lastY = -1;
    static int maxSize = 0;

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
                    if(y > maxSize) maxSize = y;
                    createArray(x,y);
                    createConnection(x,y);
                    lastX = x;
                    lastY = y;
                }

                lastX = -1;
                lastY = -1;


            }
            System.out.println(maxSize);

            for(Integer i: path.keySet()) {
                if(i == START_SAND) {
                    path.get(i).set(0, '+');
                }

                for(int k = path.get(i).lastIndexOf('#'); k < maxSize + 2; k++) {
                    if (k == (maxSize + 2) - 1)
                        path.get(i).add('#');
                    else
                        path.get(i).add('.');
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

        ArrayList<Character> start = path.get(START_SAND);
        int count = 0;
        while(start.get(0) != 'o') {
            int temp = START_SAND;
            start = path.get(START_SAND);

            try {

                for (int i = 0; i < start.size() - 1; i++) {

                    if(temp+1 == path.lastKey()+1 || temp-1 == path.firstKey()-1) {
                        ArrayList<Character> t = new ArrayList<>();
                        ArrayList<Character> t1 = new ArrayList<>();

                        for(int k = 0; k < maxSize + 2; k++) {
                            t.add('.');
                            t1.add('.');
                        }
                        t.add('#');
                        t1.add('#');

                        path.put(path.lastKey() + 1, t);
                        path.put(path.firstKey() - 1, t1);
                    }

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
        return count;
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
