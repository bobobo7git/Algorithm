class Solution {
    static boolean[] visited;
    static int max = 0;
    static int N;
    static int[][] arr;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        N = dungeons.length;
        arr = dungeons;
        visited = new boolean[N];
        
        dfs(k, 0);
        return answer = max;
    }
    static void dfs(int cur, int depth) {
        max = Math.max(max, depth);
        for (int i=0; i<N; i++) {
            if (visited[i] || arr[i][0] > cur) continue;
            visited[i] = true;
            dfs(cur-arr[i][1], depth+1);
            visited[i] = false;
        }
    }
}