import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartTwo {

    final static List<Object> dividerOne = Arrays.asList(Arrays.asList(2));
    final static List<Object> dividerTwo = Arrays.asList(Arrays.asList(6));
    static ArrayList<Object> listLeft = null;
    static ArrayList<Object> listRight = null;

    static ArrayList<Object> correctOrder = new ArrayList<>();

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(new File("input.txt"));

            while(sc.hasNext()) {
                String left = sc.next();
                String right = sc.next();

                listLeft = createList(left);
                listRight = createList(right);

                correctOrder.add(listLeft);
                correctOrder.add(listRight);

                sort(correctOrder);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

/*        for(Object o : correctOrder)
            System.out.println(o);*/

        System.out.println("Decoder key: " + findPos(correctOrder, dividerOne) * (1 + findPos(correctOrder, dividerTwo)));

    }

    private static int findPos(ArrayList<Object> correctOrder, List<Object> divider) {
        int pos = 0;
        for(int i = 0 ; i < correctOrder.size()-1; i++) {
            if(compare((List<Object>) correctOrder.get(i), divider) > 0 ) {
                pos = i + 1;
                break;
            }
        }
        return pos;
    }

    private static void sort(ArrayList<Object> correctOrder) {

        for(int i = 0 ; i < correctOrder.size()-1; i++) {
            if(compare((List<Object>) correctOrder.get(i), (List<Object>) correctOrder.get(i+1)) > 0 ) {
                Object obj1 = correctOrder.get(i);
                correctOrder.set(i,correctOrder.get(i+1));
                correctOrder.set(i+1,obj1);
                i = 0;
            }
        }

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
