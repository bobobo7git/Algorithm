import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> adjList = new ArrayList<>();
        parents = new int[N+1];
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
            parents[i] = i;
        }

        for (int i=0; i<N-2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int pu = find(u);
            int pv = find(v);
            if (pu != pv) {
                union(pu, pv);
            }
        }

//        for (int i=1; i<=N; i++) {
//            if (parents[i] == i) {
//                // bfs
//                Queue<Integer> q = new ArrayDeque<>();
//                q.offer(i);
//                while (!q.isEmpty()) {
//                    int x = q.poll();
//                    int px = find(x);
//                    for (int next: adjList.get(x)) {
//                        int py = find(next);
//                        if (px == py) continue;
//                        union(px, py);
//                        q.offer(next);
//                    }
//                }
//            }
//        }
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<N; i++) {
            if (find(i) != find(i+1)) {
                sb.append(i).append(' ').append(i+1);
                break;
            }
        }
        System.out.println(sb);
    }
    static int find(int v) {
        if (parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }
    static void union(int x, int y) {
        if (x > y) parents[x] = y;
        else parents[y] = x;
    }
}

