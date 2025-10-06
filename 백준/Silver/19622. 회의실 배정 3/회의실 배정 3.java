import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        int bound = 0;
        int[][] dp = new int[N][2];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            arr[i] = new int[]{s, e, cnt};
            bound = Math.max(bound, e);
            if (i > 1) {
                dp[i][0] = dp[i-1][1];
                dp[i][1] = Math.max(dp[i-1][0], dp[i-2][1]+cnt);
                dp[i][1] = Math.max(dp[i][1], dp[i-2][0]+cnt);
            } else if (i == 1) {
                dp[i][0] = dp[i-1][1];
                dp[i][1] = cnt;
            } else {
                dp[i][1] = cnt;
            }
        }
        System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));

       /*
       * ---
       *   ---
       *    -----
       *       -----
       * */
    }
}

