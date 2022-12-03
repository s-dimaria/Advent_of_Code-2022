import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PuzzlePartTwo {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("input.txt"));
        int sum = 0;

        while(sc.hasNextLine()) {

            String elfOne = sc.nextLine();
            String elfTwo = sc.nextLine();
            String elfThree = sc.nextLine();

            int length = 0;

            // Find string of less length
            if(elfOne.length() < elfTwo.length()) {
                if (elfOne.length() < elfThree.length())
                    length = elfOne.length();
                else length = elfThree.length();
            }
            else if (elfTwo.length() < elfThree.length())
                length = elfTwo.length();
            else length = elfThree.length();


            for (int i = 0; i < length ; i++) {
                if(length == elfOne.length()) {
                    if(elfTwo.contains(String.valueOf(elfOne.charAt(i))) && elfThree.contains(String.valueOf(elfOne.charAt(i)))) {
                        sum += itemShared(elfOne.charAt(i));
                        break;
                    }
                }
                else if(length == elfTwo.length()) {
                    if(elfOne.contains(String.valueOf(elfTwo.charAt(i))) && elfThree.contains(String.valueOf(elfTwo.charAt(i)))) {
                        sum += itemShared(elfTwo.charAt(i));
                        break;
                    }
                }
                else {
                    if(elfOne.contains(String.valueOf(elfThree.charAt(i))) && elfTwo.contains(String.valueOf(elfThree.charAt(i)))) {
                        sum += itemShared(elfThree.charAt(i));
                        break;
                    }
                }
            }
        }

        System.out.println(sum);

    }

    public static int itemShared(char c) {

        // a - z 1 - 26
        // A - Z 27 - 52
        int lastMin = 96;
        int lastMax = 38;

        if((int)c > lastMin) {
            return (int)c - lastMin;
        }
        return (int)c - lastMax;

    }
}
