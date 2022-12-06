import java.io.*;
import java.util.Arrays;

public class PuzzlePartTwo {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("input.txt"));

        int count = 0;
        int lengthSubSequence = 14;
        while(in.ready()) {

            char[] buff = new char[lengthSubSequence];
            int[] intBuff = new int[lengthSubSequence];

            int first = in.read();
            buff[0] = (char) first;
            in.mark(0);

            in.read(buff, 1, lengthSubSequence-1);

            Arrays.setAll(intBuff, i -> (int) buff[i]);
            if(Arrays.stream(intBuff).distinct().count() == lengthSubSequence) {
                count+=lengthSubSequence;
                break;
            }
            else {
                in.reset();
                count++;
            }
        }

        System.out.println(count);

    }

}