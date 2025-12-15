import java.io.*;
import java.util.*;
/*
* 1 -- 2 -- 3 -- 4 -- 5
*      ㄴ-- 6 -- 7
* (1) -- 2 -- (3) -- 4 -- (5)
*        ㄴ-- (6) -- 7           1 3 5 6 -> 9000
* (1) -- 2 -- (3) -- 4 -- (5)
*        ㄴ-- 6 -- (7)           1 3 5 7 -> 14000
* (1) -- 2 -- 3 -- (4) -- 5
*        ㄴ-- (6) -- 7           1 4 6 -> 4000
* (1) -- 2 -- 3 -- (4) -- 5
*        ㄴ-- 6 -- (7)           1 4 7 -> 9000
* 1 -- (2) -- 3 -- (4) -- 5
*       ㄴ-- 6 -- (7)            2 4 7 -> 11000
* 1 -- (2) -- 3 -- 4 -- (5)
*       ㄴ-- 6 -- (7)            2 5 7 -> 12000
*
*
*  */
public class Main {
    static int N;
    static int[] popul;
    static List<List<Integer>> adjList;
    static boolean[] visited;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        popul = new int[N+1];
        adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        dp = new int[N+1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            popul[i] = Integer.parseInt(st.nextToken());
            adjList.add(new ArrayList<>());
        }

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));


    }
    /*
    * dp[v][1]: 현재 마을을 우수 마을로 선정할 때 서브트리의 최대 합
    * dp[v][0]: 현재 마을을 우수 마을로 선정하지 않을 때 서브트리의 최대 합
    * v -> i..j에 대하여
    * dp[v][1] += dp[i..j][0]
    * dp[v][0] += max(dp[i..j][1], dp[i..j][1]
    * */
    static void dfs(int v) {
        dp[v][1] = popul[v];

        for (int next: adjList.get(v)) {
            if (visited[next]) continue;
            visited[next] = true;
            dfs(next);
            dp[v][1] += dp[next][0];
            dp[v][0] += Math.max(dp[next][1], dp[next][0]);
        }

    }

}
