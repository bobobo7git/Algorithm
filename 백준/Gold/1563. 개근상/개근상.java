import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);


        int N = Integer.parseInt(br.readLine());
        /*
        * 출 O 지 L 결 A
        * 오늘 출석하면 지금까지 출결에 관계 x
        * 오늘 지각은 지금까지 지각이 0 또는 1
        * 지금까지 지각횟수
        * 오늘 결석은 _AA A_A __A
        * 전날, 전전날 결석 여부
        * dp: i번째날 지각이 j번, 결석이 연속 k번
        * dp[i][j][k]
        * 오늘 출석하기
        * dp[i][0][0] = dp[i-1][0][0-2]
        * dp[i][1][0] = dp[i-1][1][0-2]
        * 오늘 지각하기
        * dp[i][1][0] = dp[i-1][0][0-2]
        * 오늘 결석하기
        * dp[i][0][1] = dp[i-1][0][0]
        * dp[i][0][2] = dp[i-1][0][1]
        * dp[i][1][1] = dp[i-1][1][0]
        * dp[i][1][2] = dp[i-1][1][1]
         */
        // oo oa ol lo la ao al aa
        int[][][] dp = new int[1001][2][3];
        dp[1][0][0] = 1;
        dp[1][0][1] = 1;
        dp[1][0][2] = 0;
        dp[1][1][0] = 1;
        dp[1][1][1] = 0;
        dp[1][1][2] = 0;
        for (int i=2; i<=N; i++) {
            // 오늘 출석
            dp[i][0][0] = sum(i, 0, 0, 0, 2, dp);
            dp[i][1][0] = sum(i, 1, 1, 0, 2, dp);
            // 오늘 지각
            dp[i][1][0] = (dp[i][1][0] + sum(i, 0, 0, 0, 2, dp)) % MOD;
            // 오늘 결석
            dp[i][0][1] = dp[i-1][0][0];
            dp[i][0][2] = dp[i-1][0][1];
            dp[i][1][1] = dp[i-1][1][0];
            dp[i][1][2] = dp[i-1][1][1];
        }
        int sum = 0;
        for (int j=0; j<2; j++) {
            for (int k=0; k<3; k++) {
                sum = (sum + dp[N][j][k]) % MOD;
            }
        }
        System.out.println(sum % MOD);
    }
    static int sum(int i, int js, int je, int ks, int ke, int[][][] dp) {
        int sum = 0;
        for (int j=js; j<=je; j++) {
            for (int k=ks; k<=ke; k++) {
                sum += dp[i-1][j][k];
            }
        }
        return sum % MOD;
    }

}
