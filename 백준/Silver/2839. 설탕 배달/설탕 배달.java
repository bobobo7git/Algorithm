import java.io.*;
import java.util.*;

public class Main {
    static int[] memo;
    static final int INF = 2000;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        memo = new int[N+1];
        for (int i=0; i<=N; i++) memo[i] = INF;
        memo[0] = 0;
        if (dp(N) != INF) System.out.println(dp(N));
        else System.out.println(-1);
    }
    static int dp(int n) {
        if (n < 0) return INF;
        if (memo[n] != INF) return memo[n];

        return memo[n] = Math.min(memo[n], Math.min(dp(n-5), dp(n-3))+1);

    }
}

