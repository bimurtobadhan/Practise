import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by bimurto on 21-Nov-15.
 */
public class Exam2 {

    int n,m;
    Vector <Integer> v[];
    boolean isFestiveCity[];
    int dist[];

    public Exam2(){
        readInput();
    }

    private void readInput() {
        Scanner input;
        try {
            input = new Scanner(new File("in.txt"));
            n = input.nextInt();
            m = input.nextInt();

            v = new Vector[n+1];
            isFestiveCity = new boolean[n+1];
            dist = new int[n+1];
            for(int i=0;i<=n;i++){
                v[i] = new Vector();
                isFestiveCity[i] = false;
            }

            isFestiveCity[1] = true;
            for(int i=0;i<n-1;i++){
                int a = input.nextInt();
                int b = input.nextInt();
                v[a].add(b);
                v[b].add(a);
            }

            for(int i=0;i<m;i++){
                int c = input.nextInt();
                int d = input.nextInt();
                if(c == 1){
                    isFestiveCity[d] = true;
                }else{
                    System.out.println(BFS(d));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private int BFS(int d) {
        boolean visited [] = new boolean[n+1];
        if(isFestiveCity[d])
            return 0;
        Queue <Integer>  queue = new LinkedList<>();
        queue.add(d);
        dist[d] = 0;
        while(!queue.isEmpty()){
            int a = queue.remove();
            for(int i=0;i<v[a].size();i++){
                int city = v[a].elementAt(i);
                if(!visited[city]){
                    visited[city] = true;
                    dist[city] = dist[a]+1;
                    if(isFestiveCity[city])
                        return dist[city];
                    queue.add(city);
                }
            }
        }
        return 0;
    }

    public static void main(String args[]){
        new Exam2();
    }
}
