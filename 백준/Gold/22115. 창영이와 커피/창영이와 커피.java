import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] coffee = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            coffee[i] = Integer.parseInt(st.nextToken());
        }
        // dp = 1~i번째 커피를 사용했을 때 K를 만드는 최소의 커피 수
        int[][] dp = new int[N+1][K+1];
        final int INF = 111111;
        for (int i=0; i<=N; i++) {
            for (int j=1; j<=K; j++) {
                dp[i][j] = INF;
            }
        }
        
        
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=K; j++) {
                // 현재 커피를 안마시기
                dp[i][j] = dp[i-1][j];
                // 현재 커피 마시기
                if (j-coffee[i] >= 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j-coffee[i]]+1);
            }
        }
        System.out.println(dp[N][K] == INF ? -1: dp[N][K]);
        
    }
}