import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        final int INF = Integer.MAX_VALUE;
        final int[] dr = {0, 1, 1, 1};
        final int[] dc = {1, 1, 0, -1};
        StringBuilder sb = new StringBuilder();
        int t = 0;
        while (true) {
            t++;
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            int[][] graph = new int[N][3];

            for (int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<3; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] dp = new int[N][3];
            for (int i=0; i<N; i++) {
                for (int j=0; j<3; j++) {
                    dp[i][j] = INF;
                }
            }
            dp[0][1] = graph[0][1];
            for (int i=0; i<N; i++) {
                for (int j=0; j<3; j++) {
                    if (i == 0 && j ==0) continue;
                    for (int d=0; d<4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (outOfBound(nr, nc, N)) continue;
                        dp[nr][nc] = Math.min(dp[nr][nc], dp[i][j] + graph[nr][nc]);
                    }
                }
            }
            sb.append(t).append(". ").append(dp[N-1][1]).append('\n');
        }
        System.out.print(sb);
    }
    static boolean outOfBound(int r, int c, int N) {
        return r < 0 || c < 0 || r >= N || c >= 3;
    }
}

