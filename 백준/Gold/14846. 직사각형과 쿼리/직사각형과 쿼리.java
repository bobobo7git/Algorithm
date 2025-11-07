import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][N+1];
        int[][][] dp = new int[N+1][N+1][11];
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                int n = Integer.parseInt(st.nextToken());
                for (int k=1; k<=10; k++) {
                    dp[i][j][k] += dp[i-1][j][k];
                    dp[i][j][k] += dp[i][j-1][k];
                    dp[i][j][k] -= dp[i-1][j-1][k];
                }
                dp[i][j][n]++;
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int cnt = 0;
            for (int k=1; k<=10; k++) {
                int n = dp[x2][y2][k] - dp[x2][y1-1][k] - dp[x1-1][y2][k] + dp[x1-1][y1-1][k];
                if (n > 0) cnt++;
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
}
