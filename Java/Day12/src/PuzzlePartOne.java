import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PuzzlePartOne {

    final static int INF = 9999;

    static Node start, end;

    public static void main(String[] args) {


        char[][] map = createMap();
        ArrayList<Node> arr = new ArrayList<>();

        final int col = map[0].length;
        final int row = map.length;

        try {
            Scanner sc = new Scanner(new File("input.txt"));

            System.out.println("-----CREATE MAP------");

            while(sc.hasNext()) {
                for (int i = 0; i < row; i++) {
                    String temp = sc.next();
                    char[] arrLine = temp.toCharArray();
                    for (int j = 0; j < col; j++) {
                        if(arrLine[j] == 'S') {
                            start = new Node(i, j);
                            map[i][j] = 'a';
                        }
                        else if(arrLine[j] == 'E') {
                            end = new Node(i, j);
                            map[i][j] = 'z';
                        }
                        else
                            map[i][j] = arrLine[j];
                        arr.add(new Node(i,j));
                    }
                }
            }

            System.out.println("DONE!");

            System.out.println("---------------------\n");


            System.out.println("-----ADJACENCY MATRIX------");


            int[][] aM = adjacencyMatrix(map, arr);

            System.out.println("DONE!");
            System.out.println("---------------------\n");


            System.out.println("-----SHORTEST PATHS------");
            AllPairShortestPath a = new AllPairShortestPath();

            a.floydWarshall(arr,map,aM,start,end);
            System.out.println("---------------------\n");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

    private static int[][] adjacencyMatrix(char[][] cA, ArrayList<Node> arr) {
        int rows = cA.length;
        int cols = cA[0].length;
        int V = rows * cols;
        int[][] aM = new int[rows * cols][rows * cols];


        for (int i = 0; i < V; i++) {
            int i1 = arr.get(i).getX();
            int j1 = arr.get(i).getY();

            char c1 = cA[i1][j1];
            for (int j = 0; j < V; j++) {

                if( i == j ) {
                    aM[i][j] = 0;
                    continue;
                }

                int i2 = arr.get(j).getX();
                int j2 = arr.get(j).getY();
                char c2 = cA[i2][j2];

                if(c2 <= c1 + 1) {
                    if(((i1 == i2) && j1 >= j2 - 1 && j1 <= j2 + 1) ||
                            (j1 == j2 && i1 >= i2 - 1 && i1 <= i2 + 1)) {
                        aM[i][j] = 1;
                        continue;
                    }
                }
                aM[i][j] = INF;
            }
        }

        return aM;
    }

    private static char[][] createMap() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));

            int col = sc.nextLine().length();

            int row = 1;
            while(sc.hasNextLine()) {
                sc.nextLine();
                row++;
            }

            sc.close();
            return new char[row][col];

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
