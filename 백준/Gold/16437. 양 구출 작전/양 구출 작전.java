import java.io.*;
import java.util.*;

public class Main {
    static boolean[] isSheep;
    static List<List<Integer>> adjList;
    static boolean[] visited;
    static int[] amount;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        isSheep = new boolean[N+1];
        visited = new boolean[N+1];
        amount = new int[N+1];
        adjList = new ArrayList<>();

        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i=2; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            int cnt = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            isSheep[i] = type == 'S';
            amount[i] = type == 'S' ? cnt : -cnt;
            adjList.get(i).add(v);
            adjList.get(v).add(i);
        }
        visited[1] = true;
        System.out.println(dfs(1));
    }
    static long dfs(int v) {
        long ret = amount[v];
        for (int next: adjList.get(v)) {
            if (visited[next]) continue;
            visited[next] = true;
            ret += Math.max(0, dfs(next));
        }

        return ret;
    }
}

