import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<List<Edge>> adjList = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            if (u >= v) continue;
            adjList.get(u).add(new Edge(u, v, g));
        }
        // i번 정점으로 j번 이동했을 때 최대 점수
        int[][] dp = new int[N+1][M+1];
        for (int i=1; i<=N; i++) {
            for (int j=0; j<=M; j++) {
                dp[i][j] = -1;
            }
        }
        dp[1][1] = 0;
        for (int i=1; i<=N; i++) {
            for (int j=1; j<M; j++) {
                if (dp[i][j] == -1) continue;
                for (Edge next: adjList.get(i)) {
                    dp[next.v][j+1] = Math.max(dp[next.v][j+1], dp[i][j] + next.g);
                }
            }
        }
        int max = 0;
        for (int i=1; i<=M; i++) {
            max = Math.max(dp[N][i], max);
        }
        System.out.println(max);
    }
    static class Edge {
        int u, v;
        int g;
        Edge(int u, int v, int g) {
            this.u = u;
            this.v = v;
            this.g = g;
        }
    }
}
