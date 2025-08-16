class Solution {
    static int maxN, maxM;
    static int ans;
    static boolean[][][] memo;
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        maxN = n;
        maxM = m;
        ans = 121;
        memo = new boolean[info.length+1][121][121];
        
        dfs(0, 0, 0, 0, info);
        
        return ans == 121 ? -1 : ans;
    }
    static void dfs(int i, int j, int a, int b, int[][] info) {

        if (a >= maxN || b >= maxM) {
            // memo[i][a][b] = true;
            return;
        }
        
        if (i == info.length && j == info.length) {
            ans = Math.min(ans, a);
            // memo[i][a][b] = true;
            return;
        }
        if (memo[i][a][b]) return;
        memo[i][a][b] = true;
        
        dfs(i+1, j+1, a+info[i][0], b, info);
        dfs(i+1, j+1, a, b+info[i][1], info);

    }
}