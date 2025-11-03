
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i=0; i<N; i++) {
            dp[i+1] = Math.min(dp[i+1], dp[i]+1);
            int next = i + i/2;
            if (next <= N) {
                dp[next] = Math.min(dp[next], dp[i]+1);
            }
        }

        String result = dp[N] <= K ? "minigimbob" : "water";
        System.out.println(result);
    }
}
