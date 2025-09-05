import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] grid;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        grid = new boolean[N][N];
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<N; j++) {
                grid[i][j] = line.charAt(j) == '1';
            }
        }
        System.out.println(recur(0, 0, N));
    }
    static String recur(int r, int c, int size) {
        int cnt = 0;
        for (int i=r; i<r+size; i++) {
            for (int j=c; j<c+size; j++) {
                if (grid[i][j]) cnt++;
            }
        }

        StringBuilder sb = new StringBuilder();

        if (cnt == size*size) return "1";
        if (cnt == 0) return "0";

        int qt = size/2;
        
        sb.append('(');
        sb.append(recur(r, c, qt));
        sb.append(recur(r, c+qt, qt));
        sb.append(recur(r+qt, c, qt));
        sb.append(recur(r+qt, c+qt, qt));
        sb.append(')');

        return sb.toString();
    }
}
