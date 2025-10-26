class Solution {
    static final int INF = 1000000;
    static int N;
    static int[][] dp;
    static int[][] min;
    public int solution(String arr[]) {
        int answer = -1;
        N = arr.length;
        dp = new int[N+1][N+1];
        min = new int[N+1][N+1];
        for (int i=0; i<=N; i++) {
            for (int j=0; j<=N; j++) {
                dp[i][j] = -INF;
                min[i][j] = INF;
            }
        }
        
        // cal(0, N, arr);
        // for (int i=0; i<=N; i++) {
        //     System.out.println(java.util.Arrays.toString(dp[i]));
        // }
        return answer = calMax(0, N, arr);
    }
    // [i, ..., j] 계산 결과의 최댓값
    static int calMax(int i, int j, String[] arr) {
        if (dp[i][j] != -INF) return dp[i][j];
        if (i+1 == j) {
            return dp[i][j] = Integer.parseInt(arr[i]);
        }
        
        int ret = -INF;
        for (int k=i+1; k<j; k++) {
            if (arr[k].equals("+")) {
                ret = Math.max(ret, calMax(i, k, arr)+calMax(k+1, j, arr));
            } else if (arr[k].equals("-")) {
                ret = Math.max(ret, calMax(i, k, arr)-calMin(k+1, j, arr));
            }
        }
        
        return dp[i][j] = ret;
    }
    static int calMin(int i, int j, String[] arr) {
        if (min[i][j] != INF) return min[i][j];
        if (i+1 == j) {
            return min[i][j] = Integer.parseInt(arr[i]);
        }
        
        int ret = INF;
        for (int k=i+1; k<j; k++) {
            if (arr[k].equals("+")) {
                ret = Math.min(ret, calMin(i, k, arr)+calMin(k+1, j, arr));
            } else if (arr[k].equals("-")) {
                ret = Math.min(ret, calMin(i, k, arr)-calMax(k+1, j, arr));
            }
        }
        
        return min[i][j] = ret;
    }
}