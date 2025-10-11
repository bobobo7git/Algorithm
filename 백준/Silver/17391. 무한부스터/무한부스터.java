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
        int[][] dp = new int[N][M];
        final int INF = 300*300*300+1;

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                for (int r=i+1; r<=i+grid[i][j] && r<N; r++) {
                    dp[r][j] = Math.min(dp[r][j], dp[i][j]+1);
                }
                for (int c=j+1; c<=j+grid[i][j] && c<M; c++) {
                    dp[i][c] = Math.min(dp[i][c], dp[i][j]+1);
                }
            }
        }
        
        System.out.println(dp[N-1][M-1]);
    }
}

