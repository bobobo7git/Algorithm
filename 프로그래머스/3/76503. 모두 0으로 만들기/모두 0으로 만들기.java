import java.util.*;

class Solution {
    public long solution(int[] a, int[][] edges) {
        int n = a.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        long[] val = new long[n];
        for (int i = 0; i < n; i++) val[i] = a[i];
        long ans = 0;

        // 스택: [노드, 부모, 방문여부]
        Deque<long[]> stack = new ArrayDeque<>();
        stack.push(new long[]{0, -1, 0}); // root, parent=-1, visited=false
        visited[0] = true;

        while (!stack.isEmpty()) {
            long[] now = stack.pop();
            int v = (int) now[0];
            int p = (int) now[1];
            boolean done = now[2] == 1;

            if (!done) {
                // 나 자신을 "후처리"로 다시 넣기
                stack.push(new long[]{v, p, 1});
                // 자식 push
                for (int nxt : adj.get(v)) {
                    if (visited[nxt]) continue;
                    visited[nxt] = true;
                    stack.push(new long[]{nxt, v, 0});
                }
            } else {
                // 모든 자식 처리가 끝난 시점 (후위 순회)
                if (p != -1) {
                    val[p] += val[v];
                }
                ans += Math.abs(val[v]);
            }
        }

        return val[0] == 0 ? ans : -1;
    }
}
