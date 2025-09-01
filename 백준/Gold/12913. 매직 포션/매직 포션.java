import java.io.*;
import java.util.*;

public class Main {
    static int[][] adj;
    static int N, K;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adj = new int[N][N];
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<N; j++) {
                adj[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(dijkstra());
    }
    static double dijkstra() {
        int INF = 99999;
        double[][] dist = new double[N][K+1];
        for (int i=0; i<N; i++) {
            for (int k=0; k<=K; k++) {
                dist[i][k] = INF;
            }
        }
        Queue<State> pq = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(o1.cost, o2.cost);
        });
        pq.offer(new State( 0, K, 0));
        for (int i=0; i<=K; i++) {
            dist[0][i] = 0;
        }

        while (!pq.isEmpty()) {
            State now = pq.poll();
            if (now.v == 1) return now.cost;
            // i번 정점으로 이동 v -> i
            for (int i=0; i<N; i++) {
                double g = now.cost + adj[now.v][i];
                if (g < dist[i][now.k]) {
                    dist[i][now.k] = g;
                    pq.offer(new State(i, now.k, g));
                }
                // 물약 사용
                g = now.cost + (double) adj[now.v][i] / 2;
                if (now.k > 0 && g < dist[i][now.k-1]) {
                    dist[i][now.k-1] = g;
                    pq.offer(new State(i, now.k-1, g));
                }
            }
        }

        return INF;
    }
    static class State {
        int v;
        int k;
        double cost;
        public State(int v, int k, double cost) {
            this.v = v;
            this.k = k;
            this.cost = cost;
        }
    }
}
