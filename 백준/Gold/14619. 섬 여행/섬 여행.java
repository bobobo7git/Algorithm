
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] heights;
    static final int INF = 10001;
    static List<List<Integer>> adjList;
    static int[][] memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        heights = new int[N+1];
        memo = new int[N+1][501];

        adjList = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
            for (int j=0; j<=500; j++) {
                memo[i][j] = INF;
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int result = dfs(start, K);
            if (result != INF) sb.append(result);
            else sb.append(-1);
            sb.append('\n');

        }
        System.out.print(sb);
    }
    static int dfs(int v, int k) {
        if (k == 0) {
            memo[v][k] = Math.min(memo[v][k], heights[v]);
            return memo[v][k];
        }
        if (memo[v][k] != INF) return memo[v][k];

        int result = memo[v][k];
        for (int next: adjList.get(v)) {
            int temp = dfs(next, k-1);
            result = Math.min(temp, result);
        }
        return memo[v][k] = result;
    }
}

