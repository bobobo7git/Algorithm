import java.io.*;
import java.util.*;

public class Main {
    static int[] pos;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // i번째 자르는 위치
        pos = new int[M+2];
        pos[0] = 0;
        pos[M+1] = N;
        // i번째 위치에서 j번째 위치까지 자르는 비용 최솟값
        dp = new long[M+2][M+2];
        for (int i=0; i<M+2; i++) {
            for (int j=0; j<M+2; j++) {
                if (i>=j) continue;
                dp[i][j] = Long.MAX_VALUE;
            }
        }
        for (int i=1; i<=M; i++) {
            pos[i] = Integer.parseInt(st.nextToken());

        }


        Arrays.sort(pos);
        System.out.println(slice(0, M+1));

    }
    // l번째 위치부터 r번째 위치까지 자르기
    static long slice(int l, int r) {
        if (dp[l][r] != Long.MAX_VALUE) return dp[l][r];
        long min = Long.MAX_VALUE;

        for (int x=0; x<r; x++) {
            if (pos[l] < pos[x] && pos[x] < pos[r]) {
                long ret = pos[r]-pos[l];
                ret += slice(l, x);
                ret += slice(x, r);
                min = Math.min(min, ret);
            }
        }
        if (min == Long.MAX_VALUE) return dp[l][r] = 0;

        return dp[l][r] = min;

    }
}

