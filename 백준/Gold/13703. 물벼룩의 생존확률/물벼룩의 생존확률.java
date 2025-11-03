import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static long[][] memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        K =Integer.parseInt(st.nextToken());
        N =Integer.parseInt(st.nextToken());

        /*
        * dp[i][j] = i초에 수면으로부터 j센티미터 아래의 물벼룩의 수
        * i <= N, j<= K+N
        * */
        memo = new long[N+1][N+K+1];
        // 0초에는 가능한 경우가 없으므로 0
        for (int i=1; i<=N; i++) {
            for (int j=0; j<=N+K; j++) {
                memo[i][j] = -1;
            }
        }
        // 0초에는 K센티미터에만 존재함
        memo[0][K] = 1;
        long sum = 0;
        for (int j=1; j<=N+K; j++) {
            sum += dp(N, j);
        }
        System.out.println(sum);
    }
    static long dp(int i, int j) {
        if (j <= 0 || j > N+K) return 0;   // 0센티미터면 사망, 최대 N+K
        if (memo[i][j] != -1) return memo[i][j];
        long ret = 0;
        ret += dp(i-1, j-1) + dp(i-1, j+1);

        return memo[i][j] = ret;
    }
}

