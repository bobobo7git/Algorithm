import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        int bound = 0;
        int[] dp = new int[N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            arr[i] = new int[]{s, e, cnt};
            bound = Math.max(bound, e);
            if (i > 1) {
                dp[i] = Math.max(dp[i-2]+cnt, dp[i-1]);
            } else if (i == 1) {
                dp[i] = Math.max(dp[0], cnt);
            } else {
                dp[i] = cnt;
            }
        }
        System.out.println(dp[N-1]);

       /*
       * ---
       *   ---
       *    -----
       *       -----
       * */
    }
}

