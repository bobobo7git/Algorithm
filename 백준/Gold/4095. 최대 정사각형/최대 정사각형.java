import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 || M == 0) break;

            int[][] grid = new int[N+1][M+1];
            for (int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=1; j<=M; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] dp = new int[N+1][M+1];
            int max = 0;
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=M; j++) {
                    if (grid[i][j] != 1) continue;
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;
                    max = Math.max(max, dp[i][j]);
                }
            }
            bw.write(max+"\n");
            
        }
        bw.flush();
        bw.close();
    }
}
