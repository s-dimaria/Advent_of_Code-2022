import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.Integer.parseInt;

public class PuzzlePartOne {

    public static ArrayList<Deque> load(Scanner sc) {

        ArrayList<Deque> cargo = new ArrayList<Deque>();
        String s = sc.nextLine();

        int dots = 0;
        int j = 0;
        for(int i = 0; i < s.length(); i++){
            if(j == 4) {
                dots += 1;
                j=0;
            }
            j++;
        }

        for(int k = 0; k < (s.length()-dots)/3 ; k++ ) {
            Deque<String> queue = new LinkedList<>();
            cargo.add(k,queue);
        }

        while(!s.equals("")) {
            String tempS = s.replace(" ", "");
            createMap(s, tempS, cargo);
            s = sc.nextLine();
        }

        return cargo;

    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("input.txt"));
        ArrayList<Deque> cargo = load(sc);

        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            int crates = 0;
            int from = 0;
            int to = 0;

            s = s.replaceAll("move", "");
            s = s.replaceAll("from", "");
            s = s.replaceAll("to", "");

            StringTokenizer strTok = new StringTokenizer(s," ");
            crates = parseInt(strTok.nextToken());
            from = parseInt(strTok.nextToken());
            to = parseInt(strTok.nextToken());

            cratesToMove(crates,from,to,cargo);

        }

        String result = "";
        for(int i = 0; i < cargo.size(); i++) {
            result = result.concat((String) cargo.get(i).pop());
        }

        System.out.println(result);

    }

    public static void cratesToMove(int quantity, int from, int to, ArrayList<Deque> cargo) {

        for(int i = 0; i < quantity; i++) {
            String obj = (String) cargo.get(from-1).pop();
            cargo.get(to-1).addFirst(obj);
        }

    }

    public static int createMap(String s,String tempS, ArrayList<Deque> cargo) {

        StringTokenizer tk = new StringTokenizer(tempS,"[]");

        for(int i = 0; i < s.length(); i++) {
            switch (i) {
                case 2: {
                    if (s.charAt(i) == ']')
                        cargo.get(0).add(tk.nextToken());
                    break;
                }
                case 6: {
                    if (s.charAt(i) == ']')
                        cargo.get(1).add(tk.nextToken());
                    break;
                }
                case 10: {
                    if (s.charAt(i) == ']')
                        cargo.get(2).add(tk.nextToken());
                    break;
                }
                case 14: {
                    if (s.charAt(i) == ']')
                        cargo.get(3).add(tk.nextToken());
                    break;
                }
                case 18: {
                    if (s.charAt(i) == ']')
                        cargo.get(4).add(tk.nextToken());
                    break;
                }
                case 22: {
                    if (s.charAt(i) == ']')
                        cargo.get(5).add(tk.nextToken());
                    break;
                }
                case 26: {
                    if (s.charAt(i) == ']')
                        cargo.get(6).add(tk.nextToken());
                    break;
                }
                case 30: {
                    if (s.charAt(i) == ']')
                        cargo.get(7).add(tk.nextToken());
                    break;
                }
                case 34: {
                    if (s.charAt(i) == ']')
                        cargo.get(8).add(tk.nextToken());
                    break;
                }
            }
        }

        return 0;
    }
}
