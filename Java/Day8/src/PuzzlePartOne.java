import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class PuzzlePartOne {

    static int MAX_ROW = 0;
    static int MAX_COL = 0;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("input.txt"));

        MAX_ROW = findDimensionRowMatrix();
        MAX_COL = findDimensionColMatrix();
        int[][] mat = new int [MAX_COL][MAX_ROW];

        int col = 0;
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            for(int i = 0; i < line.length(); i++)
                mat[col][i] = parseInt(String.valueOf(line.charAt(i)));

            col +=1;
        }

        System.out.println("----MAP OF TREE----");
        for(int i = 0; i < MAX_COL; i++){
            for(int j = 0; j < MAX_ROW; j++){
                System.out.print(mat[i][j]);
            }
            System.out.println("");
        }

        System.out.println("\n----NUMBER OF VISIBLE TREE----");
        int count = 0;
        for(int i = 0; i < MAX_COL; i++) {
            for (int j = 0; j < MAX_ROW; j++) {
                count += countVisibleTrees(mat, i, j);
            }
        }

        System.out.println(count);

    }

    private static int countVisibleTrees(int mat[][], int p_row, int p_col) {

        /** Count all of the trees around the edge of the grid */
        if(p_row == 0 && p_col == 0)
            return 1;
        if(p_row > 0 && p_col == 0)
            return 1;
        if(p_row == 0 && p_col > 0)
            return 1;
        if(p_row > 0 && p_col == MAX_COL-1)
            return 1;
        if(p_row == MAX_ROW-1 && p_col > 0)
            return 1;

        return checkVisibleTree(mat, p_row, p_col);
    }

    private static int checkVisibleTree(int mat[][], int p_row, int p_col) {

        boolean right = true;
        boolean left = true;
        boolean up = true;
        boolean down = true;

        for(int i = p_row+1; i < MAX_ROW; i++) {
            if(mat[p_col][p_row] <= mat[p_col][i]) {
                right = false;
                break;
            }

        }

        for(int i = p_row-1; i >= 0; i--) {
            if(mat[p_col][p_row] <= mat[p_col][i]) {
                left = false;
                break;
            }
        }

        for(int i = p_col+1; i < MAX_COL; i++) {
            if(mat[p_col][p_row] <= mat[i][p_row]) {
                down = false;
                break;
            }
        }

        for(int i = p_col-1; i >= 0 ; i--) {
            if(mat[p_col][p_row] <= mat[i][p_row]) {
                up = false;
                break;
            }
        }

        if(left || right || down || up)
            return 1;
        return 0;

    }

    private static int findDimensionRowMatrix() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        return sc.nextLine().length();
    }

    private static int findDimensionColMatrix() throws FileNotFoundException {

        Scanner sc = new Scanner(new File("input.txt"));
        int col = 0;
        while(sc.hasNextLine()) {
            sc.nextLine();
            col++;
        }

        return col;

    }
}
