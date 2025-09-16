import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringBuilder sb = new StringBuilder();
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            long[] dp = new long[N];
            for (int i=0; i<N; i++) {
                int n = Integer.parseInt(br.readLine());
                if (i > 0) dp[i] = Math.max(n, dp[i-1]+n);
                else dp[i] = n;
            }
            long max = -10001;
            for (int i=0; i<N; i++) {
                max = Math.max(max, dp[i]);
            }

            sb.append(max).append('\n');
        }
        System.out.print(sb);
    }
}
