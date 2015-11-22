import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by bimurto on 10-Nov-15.
 */
public class Main {
    int stage[][];// = new int[][];
    int row=0,column =0;
    public Main(){
        readInput();
        findMax();
    }

    private void findMax() {
        for(int i=0;i<row;i++){
            System.out.println(start(i, 0, i - 1, 0));
        }
    }

    private int start(int row,int col,int prow,int pcol) {
        if(row >= this.row || col >= this.column)
            return 0;
        if(stage[row][col] == -1 && col != column-1)
            return -1;
        if(stage[row][col] == -1 && col == column-1)
            return -1;
        int a,b,c;
        if((row+1 == prow && col == pcol) || (row+1 >= this.row) ) {
            a = 0;
        }else
            a = stage[row][col] + start((row + 1), col, row, col);
        if((row-1 == prow && col == pcol) || (row-1 < 0)) {
            b = 0;
        }
        else {
            b = stage[row][col] + start((row - 1), col, row, col);
        }
        c = stage[row][col] + start(row, col+1, row,col);
        int x = maxint(a,b,c);
        return x;
    }

    private int maxint(int a, int b,int c){
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
        for(int i=0;i<row;i++){
            for(int j =0;j<column;j++){
                stage[i][j] = Integer.parseInt(input.next());
            }
        }
    }

    public static void main(String args[]){
        new Main();
    }
}
