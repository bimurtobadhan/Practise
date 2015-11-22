import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by bimurto on 21-Nov-15.
 */
public class Exam1 {
    int stage[][];// = new int[][];
    boolean isVisited[][];
    int row=0,column =0;
    public Exam1(){
        readInput();
        findMax();
    }

    private void findMax() {
        for(int i=0;i<row;i++){
            init();
            System.out.println(start(i, 0, i - 1, 0).value);
        }
    }

    private void init() {
        for(int i=0;i<row;i++){
            for(int j =0;j<column;j++){
                isVisited[i][j] = false;
            }
        }
    }

    private Data start(int row,int col,int prow,int pcol) {


        if(row >= this.row || col >= this.column)
            return new Data(0,false);
        if(stage[row][col] == -1 /*&& col != column-1*/)
            return new Data(0,false);
//        if(stage[row][col] == -1 && col == column-1)
//            return new Data(0,false);

        if(isVisited[row][col])
            return new Data(0,false);
        else
            isVisited[row][col] = true;

        Data a,b,c;

        if((row+1 == prow && col == pcol) || (row == this.row -1 && prow==0)) {
            a = new Data(0,false);
        }else if((row+1 >= this.row)){
            a = start(0, col, row, col);
            a.isTeleported = true;
        }
        else{
            a = start((row + 1), col, row, col);
            if(!a.isTeleported)
                a.value += stage[row][col];
        }

        if((row-1 == prow && col == pcol) || (prow == this.row -1 && row==0)) {
            b = new Data(0,false);
        }
        else if( (row-1 < 0)){
            b = start(this.row-1, col, row, col);
            b.isTeleported = true;
        }
        else {
            b = start((row - 1), col, row, col);
            if(!b.isTeleported)
                b.value += stage[row][col];
        }

        c = start(row, col+1, row,col);
        if(!c.isTeleported)
            c.value += stage[row][col];

        Data x = maxint(a,b,c);
        isVisited[row][col] = false;
        return x;
    }

    private Data maxint(Data a, Data b,Data c){
        if(a.value >= b.value && a.value >= c.value)
            return a;
        if(b.value >= a.value && b.value >= c.value)
            return b;
        if(c.value >= a.value && c.value >= b.value)
            return c;
        return null;
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
        isVisited = new boolean[row][column];
        for(int i=0;i<row;i++){
            for(int j =0;j<column;j++){
                stage[i][j] = Integer.parseInt(input.next());
                isVisited[i][j] = false;
            }
        }
    }

    public static void main(String args[]){
        new Exam1();
    }

    private class Data{
        int value;
        boolean isTeleported;
        public Data(int value, boolean isTeleported){
            this.value = value;
            this.isTeleported = isTeleported;
        }
    }
}
