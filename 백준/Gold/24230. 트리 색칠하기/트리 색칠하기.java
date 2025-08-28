import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> adjList;
    static int[] C;
    static int[] tree;
    static int cnt;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        C = new int[N+1];
        tree = new int[N+1];
        visited = new boolean[N+1];
        cnt = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            int c = Integer.parseInt(st.nextToken());
            adjList.add(new ArrayList<>());
            C[i] = c;
        }

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int u = Math.min(a, b);
            int v = Math.max(a, b);
            adjList.get(a).add(b);
            adjList.get(b).add(a);

        }
        visited[1] = true;
        dfs(1, 0);
        System.out.println(cnt);

    }
    static void dfs(int v, int c) {
        if (c != C[v]) {
            cnt++;
            c = C[v];
        }
        for (int next: adjList.get(v)) {
            if (visited[next]) continue;
            visited[next] = true;
            dfs(next, c);
        }
    }
}
