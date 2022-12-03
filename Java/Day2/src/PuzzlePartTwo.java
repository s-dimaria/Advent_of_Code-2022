import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzlePartTwo {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("input.txt"));

        Map<String,Integer> point = new HashMap<String, Integer>();

        point.put("A",1);
        point.put("B",2);
        point.put("C",3);

        String lose = "X";
        String draw = "Y";

        int sum = 0;

        while(sc.hasNext()) {
            String opp = sc.next(); // C
            String elf = sc.next(); // Y

            int pOpp = point.get(opp);

            // Y (draw)
            if(elf.equals(draw)) {
                sum += (3 + pOpp);
            }
            // X (lose)
            else if(elf.equals(lose)) {
                switch(pOpp) {
                    case 1: {
                        sum += 3;
                        break;
                    }
                    case 2: {
                        sum += 1;
                        break;
                    }
                    case 3: {
                        sum += 2;
                        break;
                    }
                }
            }
            // Z (win)
            else {
                switch(pOpp) {
                    case 1: {
                        sum += 8;
                        break;
                    }
                    case 2: {
                        sum += 9;
                        break;
                    }
                    case 3: {
                        sum += 7;
                        break;
                    }
                }
            }

        }

        System.out.println(sum);
    }


}
