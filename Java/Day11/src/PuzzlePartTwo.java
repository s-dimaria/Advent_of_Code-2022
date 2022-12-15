import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class PuzzlePartTwo {

    final static int ROUND = 10000;

    static int commonDiv = 1;

    public static void main(String[] args) {

        long[] max = new long[2];

        TreeMap<String,Monkey> monkeys = readData();

        for(int i = 0 ; i < ROUND; i++) {
            for (String id : monkeys.keySet()) {
                Monkey m = monkeys.get(id);
                while (!m.getItems().isEmpty()) {
                    m.incrementInspectsItem();
                    long item = m.getItems().poll();
                    String op = m.getOperation();
                    if (m.getOperation().contains("*")) {
                        if (!op.substring(op.indexOf("*") + 1).equals("old"))
                            item = item * parseInt(op.substring(op.indexOf("*") + 1));
                        else
                            item = item * item;
                    } else if (m.getOperation().contains("+"))
                        item = item + parseInt(op.substring(op.indexOf("+") + 1));

                    item %= commonDiv;

                    if (item % m.getTest() == 0)
                        monkeys.get(m.getIsTrue()).getItems().add(item);

                    else
                        monkeys.get(m.getIsFalse()).getItems().add(item);

                }
            }
        }

        for (Monkey m : monkeys.values())
            setMax(m.getInspectsItem(),max);

        System.out.println(max[0] * max[1]);

    }

    private static void setMax(int v, long[] a) {

        for(int i = 0 ; i < a.length; i++) {
            if(a[i]<=v) {
                shiftArray(a, v, i);
                break;
            }

        }
    }

    private static void shiftArray(long[] max,int s, int p) {

        for(int i = max.length-1 ; i > p; i--) {
            max[i] = max[i-1];
        }
        max[p] = s;

    }

    private static TreeMap<String,Monkey> readData() {

        TreeMap<String,Monkey> monkeys = new TreeMap<String,Monkey>();

        try {
            Scanner sc = new Scanner(new File("input.txt"));

            while(sc.hasNextLine()) {
                if(sc.next().equals("Monkey")){
                    String id = sc.next().substring(0,1);
                    Monkey newMonkey = new Monkey();
                    sc.next(); //skip Starting
                    sc.next(); //skip items:
                    String readItems = sc.nextLine();
                    readItems = readItems.replaceAll(" ","");
                    StringTokenizer items = new StringTokenizer(readItems,",");
                    while(items.hasMoreTokens())
                    {
                        newMonkey.getItems().add(parseLong(items.nextToken()));
                    }
                    String readOp = sc.nextLine();
                    readOp = readOp.replaceAll(" ","");
                    StringTokenizer op = new StringTokenizer(readOp,":");
                    op.nextToken();
                    newMonkey.setOperation(op.nextToken());

                    String readTest = sc.nextLine();
                    readTest = readTest.replaceAll(" ","");
                    StringTokenizer test = new StringTokenizer(readTest,":");
                    test.nextToken();
                    String testDiv = test.nextToken();

                    commonDiv = mcm(commonDiv,parseInt(testDiv.replace("divisibleby", "")));

                    newMonkey.setTest(parseInt(testDiv.replace("divisibleby", "")));

                    String readIsTrue = sc.nextLine();
                    readIsTrue = readIsTrue.replaceAll(" ","");
                    newMonkey.setIsTrue(readIsTrue.substring(readIsTrue.length()-1,readIsTrue.length()));

                    String readIsFalse = sc.nextLine();
                    readIsFalse = readIsFalse.replaceAll(" ","");
                    newMonkey.setIsFalse(readIsFalse.substring(readIsFalse.length()-1,readIsFalse.length()));

                    monkeys.put(id,newMonkey);
                }
            }

            return monkeys;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static int mcd(int a, int b)
    { while (a != b)
        if (a > b)
            a -= b;
        else
            b -= a;
        return a;
    }

    public static int mcm(int a, int b)
    { return (a * b) / mcd(a, b);
    }
}

