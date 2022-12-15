import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class PuzzlePartOne {

    final static int ROUND = 20;

    public static void main(String[] args) {

        int[] max = new int[2];

        HashMap<String,Monkey> monkeys = readData();

        for(int i=0 ; i < ROUND; i++) {
            for (String id : monkeys.keySet()) {
                Monkey m = monkeys.get(id);
                while (!m.getItems().isEmpty()) {
                    long item = m.getItems().poll();
                    String op = m.getOperation();
                    if (m.getOperation().contains("*")) {
                        if (!op.substring(op.indexOf("*") + 1).equals("old"))
                            item = item * parseInt(op.substring(op.indexOf("*") + 1));
                        else
                            item = item * item;
                    } else if (m.getOperation().contains("+"))
                        item = item + parseInt(op.substring(op.indexOf("+") + 1));

                    item /= 3;

                    if (item % m.getTest() == 0)
                        monkeys.get(m.getIsTrue()).getItems().add(item);

                    else
                        monkeys.get(m.getIsFalse()).getItems().add(item);

                    m.incrementInspectsItem();
                }
            }
        }


        for (Monkey m : monkeys.values())
            setMax(m.getInspectsItem(),max);

        System.out.println(max[0] * max[1]);

    }

    private static void setMax(int v, int[] a) {

        for(int i = 0 ; i < a.length; i++) {
            if(a[i]<=v) {
                shiftArray(a, v, i);
                break;
            }

        }
    }

    private static void shiftArray(int[] max,int s, int p) {

        for(int i = max.length-1 ; i > p; i--) {
            max[i] = max[i-1];
        }
        max[p] = s;

    }

    private static HashMap<String,Monkey> readData() {

        HashMap<String,Monkey> monkeys = new HashMap<String,Monkey>();

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
                    newMonkey.setTest(parseInt(test.nextToken().replace("divisibleby", "")));

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
}

