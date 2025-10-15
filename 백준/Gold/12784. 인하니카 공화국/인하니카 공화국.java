import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<int[]>> adjList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            visited = new boolean[N+1];
            adjList = new ArrayList<>();
            for (int i=0; i<=N; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());

                adjList.get(u).add(new int[] {v, g});
                adjList.get(v).add(new int[] {u, g});
            }

            visited[1] = true;
            sb.append(dfs(1)).append('\n');
        }
        System.out.print(sb);
    }
    static int dfs(int v) {
        int sum = 0;
        for (int[] child: adjList.get(v)) {
            if (visited[child[0]]) continue;
            visited[child[0]] = true;
            // 각 서브트리에 대해서 서브트리와 간선 끊기 vs 서브트리의 모든 서브트리와 간선 끊기
            int res = Math.min(child[1], dfs(child[0]));
            res = res == 0 ? child[1] : res;
            sum += res;
        }
        return sum;
    }
}

