import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static List<List<Node>> adjList;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            adjList.get(u).add(new Node(v, g));
            adjList.get(v).add(new Node(u, g));

        }
        System.out.println(dijkstra());
    }
    static long dijkstra() {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return Long.compare(o1.g, o2.g);
        });
        final long INF = Long.MAX_VALUE;
        long[][] dp = new long[N+1][K+1];
        for (int i=0; i<=N; i++) {
            for (int k=0; k<=K; k++) {
                dp[i][k] = INF;
            }
        }
        pq.offer(new Node(1, 0));
        dp[1][0] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            // base case
            if (now.v == N) return dp[N][now.k];
            if (now.g > dp[now.v][now.k]) continue;
            for (Node next: adjList.get(now.v)) {
                // 1. 포장 안 하고 가는 경우
                if (dp[next.v][now.k] > dp[now.v][now.k] + next.g) {
                    dp[next.v][now.k] = dp[now.v][now.k] + next.g;
                    pq.offer(new Node(next.v, dp[next.v][now.k], now.k));
                }
                // 2. 포장
                if (now.k < K && dp[next.v][now.k+1] > dp[now.v][now.k]) {
                    dp[next.v][now.k+1] = dp[now.v][now.k];
                    pq.offer(new Node(next.v, dp[next.v][now.k+1], now.k+1));
                }
            }
        }

        return -1;
    }
    static class Node {
        int v;
        long g;
        int k;
        Node(int v, long g) {
            this.v = v;
            this.g = g;
            this.k = 0;
        }
        Node(int v, long g, int k) {
            this.v = v;
            this.g = g;
            this.k = k;
        }
    }
}

