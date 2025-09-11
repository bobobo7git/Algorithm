import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 10000;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] day = new int[N+1];
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int date = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            day[date] = type;
        }

        int[][][] dp = new int[101][4][3];
        if (day[1] == 0) {
            dp[1][1][1] = 1;
            dp[1][2][1] = 1;
            dp[1][3][1] = 1;
        } else {
            dp[1][day[1]][1] = 1;
        }

        for (int i=1; i<=N; i++) {
            // fix
            int f = day[i];
            if (f != 0) {
                // 오늘 처음 먹기
                 for (int j=1; j<=3; j++) {
                     for (int k=1; k<3; k++) {
                         if (f != j) dp[i][f][1] = (dp[i][f][1] + dp[i-1][j][k]) % MOD;
                     }
                 }
                 // 연속 먹기
                 dp[i][f][2] = dp[i-1][f][1];
                 continue;
            }
            // 오늘 처음 먹기
            for (int j=1; j<=3; j++) {
                for (int ej=1; ej<=3; ej++) {
                    for (int k=1; k<3; k++) {
                        if (j != ej) dp[i][j][1] = (dp[i][j][1] + dp[i-1][ej][k]) % MOD;
                    }
                }
            }
            // 연속 먹기
            for (int j=1; j<=3; j++) {
                dp[i][j][2] = dp[i-1][j][1];
            }

        }
        int sum = 0;
        for (int j=1; j<=3; j++) {
            for (int k=0; k<3; k++) {
                sum = (sum + dp[N][j][k]) % MOD;
            }
        }
        /*

         */
        System.out.println(sum);
    }
}
