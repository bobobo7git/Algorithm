import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int SIZE = 300;
        boolean[][] grid = new boolean[SIZE+1][SIZE+1];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            grid[y][x] = true;
        }
        int[][] dp = new int[SIZE+1][SIZE+1];

        for (int i=0; i<=SIZE; i++) {
            for (int j=0; j<=SIZE; j++) {
                // 오른쪽
                if (j<SIZE && grid[i][j+1]) {
                    int candy = Math.max(0, M - (i+j+1));
                    dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j] + candy);
                } else if (j<SIZE) {
                    dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j]);
                }
                // 위쪽
                if (i<SIZE && grid[i+1][j]) {
                    int candy = Math.max(0, M - (i+j+1));
                    dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + candy);
                } else if (i<SIZE) {
                    dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
                }
            }
        }
        int max = 0;
        for (int i=0; i<=SIZE; i++) {
            for (int j=0; j<=SIZE; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(max);
        

    }
}