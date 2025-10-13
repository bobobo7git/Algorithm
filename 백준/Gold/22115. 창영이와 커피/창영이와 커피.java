import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] coffee;
    static int[][] dp;
    static final int INF = 1111111;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coffee = new int[N+1];
        dp = new int[N+1][K+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            coffee[i] = Integer.parseInt(st.nextToken());
        }
        // i번째 커피를 볼때 용량 j를 채우는 최소 커피 수
        for (int i=0; i<=N; i++) {
            for (int j=0; j<=K; j++) {
                dp[i][j] = -1;
            }
        }

        int result = topDown(N, K);
        System.out.println(result == INF ? -1 : result);
    }
    static int topDown(int i, int cap) {
        if (cap == 0) return 0;
        if (i == 0) return INF;
        if (dp[i][cap] != -1) return dp[i][cap];

        if (cap >= coffee[i]) return dp[i][cap] = Math.min(topDown(i-1, cap), topDown(i-1, cap-coffee[i])+1);
        return dp[i][cap] = topDown(i-1, cap);
    }
}
