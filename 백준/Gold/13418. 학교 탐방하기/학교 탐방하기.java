import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        for (int i=0; i<=N; i++) {
            parents[i] = i;
        }

        Edge[] edges = new Edge[M+1];
        for (int i=0; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(u, v, c);
            edges[i] = edge;
        }

        int[] temp = Arrays.copyOf(parents, N+1);
        
        Arrays.sort(edges, new Comparator<Edge>(){
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.c - o2.c;
            }
        });
        
        int max = kruskal(N, edges);
        
        parents = temp;
        Arrays.sort(edges, new Comparator<Edge>(){
            @Override
            public int compare(Edge o1, Edge o2) {
                return o2.c - o1.c;
            }
        });
        
        int min = kruskal(N, edges);
        System.out.println(max-min);
    }
    static int kruskal(int N, Edge[] edges) {
        int sum = 0;
        int cnt = 0;
        int hill = 0;

        for (Edge edge: edges) {
            if (find(edge.u) == find(edge.v)) continue;
            union(edge.u, edge.v);
            cnt++;
            if (edge.c == 0) {
                hill++;
                sum = hill*hill;
            }
            if (cnt == N) return sum;
        }

        return sum;
    }
    static int find(int v) {
        if (parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x > y) parents[x] = y;
        else parents[y] = x;
    }
    static class Edge {
        int u, v;
        int c;
        public Edge(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }
}
