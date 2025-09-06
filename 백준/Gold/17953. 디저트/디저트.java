import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dessert = new int[M][N];
        int[][] dp = new int[M][N];

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                dessert[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0; i<M; i++) {
            dp[i][0] = dessert[i][0];
        }

        for (int j=1; j<N; j++) {
            for (int i=0; i<M; i++) {
                for (int k=0; k<M; k++) {
                    if (i == k) continue;
                    dp[i][j] = Math.max(dp[k][j - 1] + dessert[i][j], dp[i][j]);
                }
                // 연속해서 먹기
                dp[i][j] = Math.max(dp[i][j-1]+dessert[i][j]/2, dp[i][j]);
            }
        }
        
        int max = 0;
        for (int i=0; i<M; i++) {
            max = Math.max(dp[i][N-1], max);
        }
        System.out.println(max);
    }
}
