import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parents;
    static boolean[] cycle;
    static int[] size;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        cycle = new boolean[N+1];
        size = new int[N+1];

        Queue<Tree> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.size != o2.size) return o2.size - o1.size;
            return o1.root - o2.root;
        });
        for (int i=1; i<=N; i++) {
            parents[i] = i;
            size[i] = 1;
            pq.offer(new Tree(i, 1));
        }
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            if (q == 1) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                u = find(u);
                v = find(v);

                if (u == v) {
                    cycle[u] = true;
                } else {
                    if (cycle[u] || cycle[v]) {
                        cycle[u] = true;
                        cycle[v] = true;
                    } else {
                        union(u, v);
                        if (u < v) pq.offer(new Tree(u, size[u]));
                        else pq.offer(new Tree(v, size[v]));
                    }
                }

            } else {
                while (!pq.isEmpty()) {
                    Tree tree = pq.poll();
                    int root = find(tree.root);
                    if (cycle[root]) continue;
                    cycle[root] = true;
                    sb.append(root).append('\n');
                    break;
                }

            }
        }
        System.out.print(sb);

    }

    static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }
    static boolean union(int x, int y) {
        if (x == y) return false;

        if (x > y) {
            parents[x] = y;
            size[y] += size[x];
        }
        else {
            parents[y] = x;
            size[x] += size[y];
        }

        return true;
    }
    static class Tree {
        int root;
        int size;
        Tree(int root, int size) {
            this.root = root;
            this.size = size;
        }
        @Override
        public String toString() {
            return "("+root+", "+size+")";
        }
    }
}
