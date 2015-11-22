import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by bimurto on 10-Nov-15.
 */
public class Exam1_2 {
    int stage[][];// = new int[][];
    int A[][];
    int B[][];
    int C[][];
    int row=0,column =0;
    boolean isVisited[][];

    Vector <Integer> v = new Vector<>();
    public Exam1_2(){
        readInput();
        findMax();
    }

    private void findMax() {
        for(int i=0;i<row;i++){
            int a = start(i, 0, i - 1, 0);
            System.out.println(a);
            v.add(a);
        }

//        for(int i=0;i<column;i++){
//            if(isVisited[0][i] == true) {
//                int a = start(0, i, -1, i);
//                System.out.println(a);
//                v.add(a);
//            }
//        }
//
//        for(int i=0;i<column;i++){
//            if(isVisited[row-1][i] == true) {
//                int a = start(row-1, i, row, i);
//                System.out.println(a);
//                v.add(a);
//            }
//        }
    }

    private int start(int row,int col,int prow,int pcol) {

        if(row >= this.row || col >= this.column)
            return 0;
        if(stage[row][col] == -1 && col != column-1)
            return 0;
        if(stage[row][col] == -1 && col == column-1)
            return 0;

       // isVisited[row][col] = true;
        int a,b,c;
        if((row+1 == prow && col == pcol) || (row+1 >= this.row) ) {
            a = 0;
            if(row+1 >= this.row){
                isVisited[0][col] = true;
            }

        }else {
            if(A[row][col] == -1) {
                int q = start((row + 1), col, row, col);
                a = stage[row][col] + q;
                A[row][col] = a;
            }else
                a = A[row][col];
        }

        if((row-1 == prow && col == pcol) || (row-1 < 0)) {
            b = 0;
            if(row-1 < 0){
                isVisited[this.row-1][col] = true;
            }
        }
        else {
            if(B[row][col] == -1) {
                int q = start((row - 1), col, row, col);
                b = stage[row][col] + q;
                B[row][col] = b;
            }
            else b = B[row][col];
        }

        if(C[row][col] == -1) {
            int q = start(row, col + 1, row, col);
            c = stage[row][col] + q;
            C[row][col] = c;
        }
        else
            c = C[row][col];
        int x = maxint(a,b,c);
        return x;
    }

    private int maxint(int a, int b,int c){
        if(a==0 && b==0 && c==0)
            return -1;
        if(a >= b && a >= c)
            return a;
        if(b >= a && b >= c)
            return b;
        if(c >= a && c >= b)
            return c;
        return 0;
    }

    private void readInput() {
        Scanner input = null;
        try {
            input = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        row = Integer.parseInt(input.next());
        column = Integer.parseInt(input.next());
        System.out.println(row+" "+column);
        stage = new int[row][column];
        A = new int[row][column];
        B = new int[row][column];
        C = new int[row][column];
        isVisited = new boolean[row][column];
        for(int i=0;i<row;i++){
            for(int j =0;j<column;j++){
                stage[i][j] = Integer.parseInt(input.next());
                A[i][j] = -1;
                B[i][j] = -1;
                C[i][j] = -1;
                isVisited[i][j] = false;
            }
        }
    }

    public static void main(String args[]){
        new Exam1_2();
    }
}
