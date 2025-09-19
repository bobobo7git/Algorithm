import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] ind;
    static List<List<Site>> adjList;
    static int[] route;
    public static void main(String[] args) throws IOException {
        input();
        System.out.println(topology());
        showRoute();
    }
    static void showRoute() {
        Deque<Integer> stack = new ArrayDeque<>();
        int now = route[1];
        stack.push(1);

        while (now != 1) {
            stack.push(now);
            now = route[now];
        }
        stack.push(now);
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop()).append(' ');
        
        System.out.println(sb.toString().trim());
    }
    static int topology() {
        int result = 0;
        int[] dist = new int[N+1];
        route = new int[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);

        while (!q.isEmpty()) {
            Integer now = q.poll();

            if (now == 1 && dist[now] > 0) return dist[now];

            for (Site next: adjList.get(now)) {
                if (dist[next.v] < dist[now]+next.g) {
                    dist[next.v] = dist[now]+next.g;
                    route[next.v] = now;
                }

                if (--ind[next.v] == 0) {
                    q.offer(next.v);
                }

            }
        }
        return result;
    }
    static void input() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }
        ind = new int[N+1];

        for (int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            // u -> v 단방향
            adjList.get(u).add(new Site(v, g));
            ind[v]++;
        }
    }
    static class Site {
        int v;
        int g;
        Site(int v, int g) {
            this.v = v;
            this.g = g;
        }
    }
}
