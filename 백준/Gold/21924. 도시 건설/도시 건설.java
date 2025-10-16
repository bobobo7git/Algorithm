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

        int[][] edges = new int[M][3];
        long sum = 0;
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            edges[i] = new int[]{u, v, g};
            sum += g;
        }
        parents = new int[N+1];
        for (int i=1; i<=N; i++) parents[i] = i;

        Arrays.sort(edges, (o1, o2) -> {
            return o1[2] - o2[2];
        });
        int cnt = 0;
        for (int i=0; i<M; i++) {
            if (cnt == N-1) break;
            int x = edges[i][0];
            int y = edges[i][1];
            if (find(x) == find(y)) continue;
            union(x, y);
            sum -= edges[i][2];
            cnt++;
        }
        
        
        System.out.println(sum = cnt == N-1 ? sum : -1);
    }
    static int find(int v) {
        if (v == parents[v]) return v;
        return parents[v] = find(parents[v]);
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x > y) parents[x] = y;
        else parents[y] = x;
    }

}

