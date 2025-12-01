import java.util.*;
import java.io.*;

public class Main {
    static List<List<int[]>> adjList;
    static boolean[] visited;
    static int V, E;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        visited = new boolean[V+1];
        for (int i=0; i<=V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            adjList.get(u).add(new int[]{v, g});
        }
        int min = Integer.MAX_VALUE;
        for (int i=1; i<=V; i++) {
            int res = dijkstra(i);
            min = Math.min(min, res);
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
    static int dijkstra(int start) {
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new int[]{start, 0});
//        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if (now[0] == start && now[1] > 0) {
                return now[1];
            }
            if (now[1] > dist[now[0]]) continue;

            for (int[] next: adjList.get(now[0])) {
                int ncost = now[1] + next[1];
                if (dist[next[0]] > ncost) {
                    dist[next[0]] = ncost;
                    pq.offer(new int[]{next[0], ncost});
                }
            }
        }

        return Integer.MAX_VALUE;
    }

}
