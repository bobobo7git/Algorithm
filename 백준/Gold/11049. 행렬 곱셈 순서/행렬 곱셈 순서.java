import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] mat;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        mat = new int[N][2];
        dp = new int[N][N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            mat[i] = new int[] {r, c};
            for (int j=0; j<N; j++) {
                dp[i][j] = INF;
            }
        }
        System.out.println(topDown(0, N-1));
    }
    /*
    * 5 3
    * 3 2
    * 2 6
    * 1. (5x3 * 3x2) * 2x6 => [5*3*2], 5x2 * 2x6 => 5*3*2 + 5*2*6
    * 2. 5x3 * (3x2 * 2x6) => [3*2*6], 5x3 * 3x6 => 3*2*6 + 5*3*6
    *
    * 5 4
    * 4 3
    * 3 2
    * 2 1
    *
    * (5x4 * 4x3 * 3x2) * 2x1
    * - ((5x4 * 4x3) * 3x2) * 2x1 => [5*4*3], ((5x3 * 3x2) * 2x1)
    * -- [5*3*2], [5*2*1]
    * - (5x4 * (4x3 * 3x2)) * 2x1 => [4*3*2], (5x4 * 4x2) * 2x1
    * -- [5*4*2], [5*2*1]
    * 5x4 * (4x3 * 3x2 * 2x1)
    * - 5x4 * ((4x3 * 3x2) * 2x1) => [4*3*2], 5x4 * (4x2 * 2x1)
    * -- [4*2*1], [5*4*1]
    * - 5x4 * (4x3 * (3x2 * 2x1)) => [3*2*1], 5x4 * (4x3 * 3x1)
    * -- [4*3*1], [5*4*1]
    *
    * mat h1, h2, h3, ..., hn
    * (h1, h2, ... hn-1) hn
    * h1, (h2, ... hn-1 hn)
    * ...
    *
    * dp: i..j까지 행렬곱 연산 최솟값
    * */
    static int topDown(int i, int j) {
        if (dp[i][j] != INF) return dp[i][j];
        if (i == j) return 0;
        int min = INF;
        for (int k=i; k<j; k++) {
            min = Math.min(min, topDown(i, k) + topDown(k+1, j) + mat[i][0]*mat[k+1][0]*mat[j][1]);
        }

        return dp[i][j] = min;

    }
}
