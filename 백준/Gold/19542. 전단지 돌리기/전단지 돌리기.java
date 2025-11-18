import java.io.*;
import java.util.*;

public class Main {
    static int D;
    static List<List<Integer>> adjList;
    static boolean[] visited;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        visited = new boolean[N+1];
        dist = new int[N+1];
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        visited[S] = true;
        dfs(S);

        int nodes = 0;
        for (int i=1; i<=N; i++) {
            if (dist[i] >= D) nodes++;
        }
        
        System.out.println(Math.max((nodes-1) * 2, 0));
    }
    /*
    * 현재 정점의 서브트리에서 리프까지의 최대 거리
    * 이게 D를 넘으면 현재 정점을 와야함
    * 각 자식의 최대깊이
    * */
    static int dfs(int v) {
        int ret = 0;
        for (int next: adjList.get(v)) {
            if (visited[next]) continue;
            visited[next] = true;
            ret = Math.max(ret, dfs(next)+1);
        }
        return dist[v] = ret;
    }


}