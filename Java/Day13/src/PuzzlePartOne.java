import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartOne {

    static ArrayList<Object> listLeft = null;
    static ArrayList<Object> listRight = null;

    public static void main(String[] args) {

        int pair = 1;

        ArrayList<Integer> correctIndex = new ArrayList<Integer>();

        try {
            Scanner sc = new Scanner(new File("input.txt"));

            while(sc.hasNext()) {
                String left = sc.next();
                String right = sc.next();

                listLeft = createList(left);
                listRight = createList(right);

                System.out.println(listLeft);
                System.out.println(listRight);
                System.out.println("");


                if(compare(listLeft, listRight) < 0)
                    correctIndex.add(pair);

                pair++;

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Right Order Packet: " + correctIndex.stream().reduce(0, Integer::sum));

    }

    private static int compare(List<Object> left, List<Object> right) {

        for(int i = 0 ; i < Math.min(left.size(), right.size()); i++) {
            if(left.get(i) instanceof Integer leftInt && right.get(i) instanceof Integer rightInt) {
                if((leftInt - rightInt) != 0)
                    return leftInt - rightInt;
            }
            else if (left.get(i) instanceof Integer leftInt) {
                int comp = compare(List.of(leftInt), (List<Object>) right.get(i));
                if (comp != 0) return comp;
            }
            else if (right.get(i) instanceof Integer rightInt) {
                int comp = compare((List<Object>) left.get(i), List.of(rightInt));
                if (comp != 0) return comp;
            }
            else {
                int comp = compare((List<Object>) left.get(i), (List<Object>) right.get(i));
                if(comp != 0) return comp;
            }
        }
        return left.size() - right.size();

    }

    //edit problem with 2 decimal [ex 10]
    private static ArrayList<Object> createList(String str){
        Deque<ArrayList<Object>> lists = new LinkedList<>();
        String tmp = "";

        while(!str.isEmpty()) {
            String s = String.valueOf(str.charAt(0));

            if(s.equals("[")) {
                lists.push(new ArrayList<>());
                str = str.substring(1);
                continue;
            }

            if(s.equals("]")) {
                ArrayList<Object> arr = lists.pop();
                if(lists.isEmpty()) {
                    return arr;
                }
                else
                    lists.peek().add(arr);

                str = str.substring(1);
            }

            else if(s.equals(",")) {
                if (!tmp.isEmpty())
                    lists.peek().add(Integer.parseInt(tmp));
                str = str.substring(1);
            }

            else {
                int i = 0;
                while(str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    tmp = tmp.concat(String.valueOf(str.charAt(i)));
                    i++;
                }

                lists.peek().add(Integer.parseInt(tmp));
                tmp = "";
                str = str.substring(i);
            }

        }

        return null;
    }
}
