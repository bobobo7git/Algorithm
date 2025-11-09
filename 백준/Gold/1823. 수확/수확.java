
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] v;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        v = new int[N];
        for (int i=0; i<N; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(topDown(0, N-1));
    }
    static int topDown(int l, int r) {
        int k = l + (N-r);
        if (k > N) return 0;
        
        if (dp[l][r] != 0) return dp[l][r];
        return dp[l][r] = Math.max(topDown(l+1, r)+v[l]*k, topDown(l, r-1)+v[r]*k);

    }
}