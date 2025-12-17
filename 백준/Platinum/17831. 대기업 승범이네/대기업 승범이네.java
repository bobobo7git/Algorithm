import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> adjList;
    static int[] ability;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>();
        ability = new int[N+1];
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }
        // 2...N의 사수번호
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=2; i<=N; i++) {
            int p = Integer.parseInt(st.nextToken());
            // p -> i
            adjList.get(p).add(i);
        }
        // i번 직원의 실력
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            int g = Integer.parseInt(st.nextToken());
            ability[i] = g;
        }
        adjList.get(0).add(1);
        dp = new int[N+1][2];
        for (int i=0; i<=N; i++) {
            Arrays.fill(dp[i], -1);
        }
        int result = Math.max(dfs(1, 0), dfs(1, 1));
        System.out.println(result);
//        for (int i=0; i<=N; i++) {
//            System.out.println(i+" "+Arrays.toString(dp[i]));
//        }
    }
    static int dfs(int v, int linked) {
        if (dp[v][linked] != -1) return dp[v][linked];
        // 현재 노드와 다음 노드 중 하나를 연결하면
        // 다른 노드와는 연결할 수 없음
        // 현재 노드가 이미 연결되어있으면 다음 노드와 연결할 수 없음
        int ret = 0;
        int unlinkedAll = 0;
        List<Integer> list = adjList.get(v);
        for (int i=0; i<list.size(); i++) {
            int next = list.get(i);
            unlinkedAll += dfs(next, 0);
        }
        if (linked == 1) {
            return dp[v][linked] = unlinkedAll;
        }
        ret = unlinkedAll;
        for (int i=0; i<list.size(); i++) {
            int next = list.get(i);
            int val = ability[v]*ability[next] + dfs(next, 1);
            ret = Math.max(ret, unlinkedAll - dfs(next, 0) + val);
        }

        return dp[v][linked] = ret;
    }

}
