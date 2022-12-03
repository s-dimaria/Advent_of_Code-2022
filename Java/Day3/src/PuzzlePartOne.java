import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class PuzzlePartOne {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("input.txt"));
        int sum = 0;

        while (sc.hasNextLine()) {

            HashMap<Character, Integer> comp = new HashMap<Character, Integer>();

            String itemsBack = sc.nextLine();
            int length = itemsBack.length();

            int i = 0, j = length-1;
            while(j >= length/2) {

                while(i < length/2) {
                    comp.put(itemsBack.charAt(i),1);
                    i++;
                }

                if(comp.containsKey(itemsBack.charAt(j)))
                    comp.put(itemsBack.charAt(j), comp.get(itemsBack.charAt(j)) + 1);
                j--;
            }

            sum += itemShared(comp);
        }

        System.out.println(sum);
    }
    
    public static int itemShared(HashMap<Character,Integer> items) {

        // a - z 1 - 26
        // A - Z 27 - 52
        int lastMin = 96;
        int lastMax = 38;

        for(Character c : items.keySet()) {
            if (items.get(c) >= 2) {
                if ((int) c > lastMin) {
                    return (int) c - lastMin;
                }
                return (int) c - lastMax;
            }
        }
        return 0;

    }
}
