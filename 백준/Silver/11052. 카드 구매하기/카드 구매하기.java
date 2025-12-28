import java.io.*;
import java.util.*;
/*
* N개 = max(p1 + pn-1, p2 + pn-2, ... , pn/2 + pn/2)
* max(p1 + p2 + pn-3, p1 + p3 + pn-4,...)
* max(p1 + p2 + p3 + pn-6 ...)
* dp[n] = dp[1..i] + dp[n-1 ... n-i]
* */
public class Main {
    static int N;
    static int[] P;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        P = new int[N+1];
        dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve(N));

    }
    static int solve(int n) {
        if (dp[n] != 0) return dp[n];

        int ret = P[n];
        for (int i=1; i<n; i++) {
            ret = Math.max(ret, solve(n-i) + P[i]);
        }
        return dp[n] = ret;
    }

}
