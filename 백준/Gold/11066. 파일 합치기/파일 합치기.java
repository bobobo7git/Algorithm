import java.io.*;
import java.util.*;

public class Main {
    static int[] C;
    static int[] p;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            C = new int[N];
            p = new int[N+1];
            dp = new int[N][N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++) {
                C[i] = Integer.parseInt(st.nextToken());
                p[i+1] += p[i] + C[i];
            }

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    dp[i][j] = -1;
                }
            }
            sb.append(recur(0, N-1)).append('\n');
        }
        System.out.print(sb);
    }
    static int recur(int s, int e) {
        if (s == e) return 0;
        if (dp[s][e] != -1) return dp[s][e];

        int min = Integer.MAX_VALUE;
        int sum = p[e+1] - p[s];

        for (int i=s; i<e; i++) {
            min = Math.min(min, recur(s, i) + recur(i+1, e));

        }
        return dp[s][e] = min+sum;
    }
}
