import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[][] grid = new int[N+1][N+1];
        // (i, j)까지 왔을 때 마지막으로 마신 우유가 k일때 최대 우유의 수
        int[][][] dp = new int[N+1][N+1][3];

        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 0) dp[i][j][0] = 1;
            }
        }
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                // 3가지 우유를 탐색
                for (int m=0; m<3; m++) {
                    // 현재 우유
                    if (m == grid[i][j]) {
                        if (dp[i-1][j][(m+2)%3] > 0) dp[i][j][m] = Math.max(dp[i][j][m], dp[i-1][j][(m+2)%3]+1);
                        if (dp[i][j-1][(m+2)%3] > 0) dp[i][j][m] = Math.max(dp[i][j][m], dp[i][j-1][(m+2)%3]+1);
                    } else {
                        dp[i][j][m] = Math.max(dp[i-1][j][m], dp[i][j-1][m]);
                    }
                }


            }
        }
        int max = 0;
        for (int m=0; m<3; m++) {
            max = Math.max(max, dp[N][N][m]);
        }
//        for (int i=0; i<=N; i++) {
//            for (int j=0; j<=N; j++) {
//                System.out.print(Arrays.toString(dp[i][j])+" ");
//            }
//            System.out.println();
//        }
        System.out.println(max);
    }
}

