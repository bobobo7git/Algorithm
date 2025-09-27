import java.io.*;
import java.util.*;

public class Main {
    static int[][][] memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        memo = new int[61][61][61];
        int[] start = new int[3];
        for (int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            start[i] = n;
        }
        for (int i=0; i<=60; i++) {
            for (int j=0; j<=60; j++) {
                for (int k=0; k<=60; k++) {
                    memo[i][j][k] = 999;
                }
            }
        }
        memo[0][0][0] = 0;
        System.out.println(dp(start[0], start[1], start[2]));
    }
    static int dp(int a, int b, int c) {
        a = Math.max(a, 0);
        b = Math.max(b, 0);
        c = Math.max(c, 0);
        if (memo[a][b][c] < 999) return memo[a][b][c];

        memo[a][b][c] = Math.min(dp(a-9, b-3, c-1), memo[a][b][c]);
        memo[a][b][c] = Math.min(dp(a-9, b-1, c-3), memo[a][b][c]);
        memo[a][b][c] = Math.min(dp(a-3, b-9, c-1), memo[a][b][c]);
        memo[a][b][c] = Math.min(dp(a-3, b-1, c-9), memo[a][b][c]);
        memo[a][b][c] = Math.min(dp(a-1, b-9, c-3), memo[a][b][c]);
        memo[a][b][c] = Math.min(dp(a-1, b-3, c-9), memo[a][b][c]);

        return memo[a][b][c] += 1;
    }
}
