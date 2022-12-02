import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartOne {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("input.txt"));

        Map<String,Integer> point = new HashMap<String, Integer>();

        point.put("A",1);
        point.put("B",2);
        point.put("C",3);
        point.put("X",1);
        point.put("Y",2);
        point.put("Z",3);

        int sum = 0;

        while(sc.hasNext()) {
            String opp = sc.next(); // C
            String elf = sc.next(); // Y

            int pOpp = point.get(opp);
            int pElf = point.get(elf);

            // draw
            if(pOpp == pElf) {
                sum += (pElf+3);
            }
            // win/lose elf
            else {
                sum += ((pOpp == 1 && pElf == 2) || (pOpp == 2 && pElf == 3) || (pOpp == 3 && pElf == 1)) ? pElf + 6 : pElf;
            }
        }

        System.out.println(sum);
    }

}
