import java.util.*;
class Solution {
    static boolean[][][] visited;
    static List<List<Integer>> adjList;
    static int max;
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        visited = new boolean[18][18][18];  // i,s,w
        adjList = new ArrayList<>();
        max = 1;
        for (int i=0; i<=18; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        info[0] = -1;
        visited[0][1][0] = true;
        dfs(0, 1, 0, info);
        return answer = max;
    }
    static void dfs(int v, int s, int w, int[] info) {
        if (s <= w) return;
        max = Math.max(max, s);
        if (s == 17) return;        
        
        for (int next: adjList.get(v)) {
            // sheep
            if (info[next] == 0) {
                if (!visited[next][s+1][w]) {
                    visited[next][s+1][w] = true;
                    info[next] = -1;
                    dfs(next, s+1, w, info);
                    info[next] = 0;
                    visited[next][s+1][w] = false;
                }
            }
            // wolf
            else if (info[next] == 1) {
                if (!visited[next][s][w+1]) {
                    visited[next][s][w+1] = true;
                    info[next] = -1;
                    dfs(next, s, w+1, info);
                    info[next] = 1;
                    visited[next][s][w+1] = false;
                }
            }
            // empty
            else if (info[next] == -1) {
                if (!visited[next][s][w]) {
                    visited[next][s][w] = true;
                    dfs(next, s, w, info);
                    visited[next][s][w] = false;
                }
            }
        }
    }
}