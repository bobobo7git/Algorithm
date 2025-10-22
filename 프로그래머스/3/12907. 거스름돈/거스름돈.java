class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        int[] dp = new int[n+1];
        final int MOD = 1_000_000_007;
        // m원 만드는 경우의 수
        dp[0] = 1;
        for (int i=0; i<money.length; i++) {
            for (int j=money[i]; j<=n; j++) {
                dp[j] = (dp[j] + dp[j-money[i]]) % MOD;
            }
        }
        

        return answer = dp[n];
    }
}