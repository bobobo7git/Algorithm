import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[T+1][3][W+1];
        for (int t=1; t<=T; t++) {
            int pos = Integer.parseInt(br.readLine());
            int prev = pos == 1 ? 2 : 1;
            if (t == 1) {
                if (pos == 1) dp[t][pos][0] = 1;
                if (pos == 2) dp[t][pos][1] = 1;
                continue;
            }
            // 현재 자두를 먹으러 오기
            // 움직여서 오기, 안움직이고 오기
            for (int w=0; w<=Math.min(t, W); w++) {
                dp[t][pos][w] = dp[t-1][pos][w] + 1;
                if (w > 0) dp[t][pos][w] = Math.max(dp[t][pos][w], dp[t-1][prev][w-1]+1);
            }
            // 안먹기 갱신
            for (int w=0; w<=Math.min(t, W); w++) {
                dp[t][prev][w] = dp[t-1][prev][w];
                if (w > 0) dp[t][prev][w] = Math.max(dp[t][prev][w], dp[t-1][pos][w-1]);
            }
        }
        int max = 0;
        for (int w=0; w<=W; w++) {
            for (int p=1; p<=2; p++) {
                max = Math.max(max, dp[T][p][w]);
            }
        }
        System.out.println(max);
    }
}

