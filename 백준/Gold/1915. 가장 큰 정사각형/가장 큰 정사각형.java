import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];
        int max = 0;
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                grid[i][j] = line.charAt(j) - '0';

                if (i>0 && j>0 && grid[i][j] == 1) {
                    grid[i][j] = Math.min(grid[i-1][j-1], Math.min(grid[i-1][j], grid[i][j-1])) + 1;
                }
                max = Math.max(max, grid[i][j]);
            }
        }
        System.out.println(max * max);
    }
}
