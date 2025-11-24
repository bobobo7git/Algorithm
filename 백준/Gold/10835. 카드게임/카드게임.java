import java.io.*;
import java.util.*;
/*
 * 1. 왼쪽 카드만 버리기 / 둘 다 버리기
 * 2. 특정 경우(오른쪽이 작은 경우)에만 점수 획득
 * -> 지금 점수를 획득하는 것이 최적이라는 보장이 없음
 * 백트래킹?
 * 1) 오른쪽이 작은 경우
 *   - 오른쪽 버리고 점수, 왼쪽, 둘다 3가지
 * 2) 오른쪽이 큰 경우
 *   - 왼쪽, 둘다 2가지
 * 최소 2^2000 => TLE
 *
 * 순서가 보장되어있으므로 DP
 * 상태: 왼쪽 더미의 수, 오른쪽 더미의 수
 * -> 섞지 않으므로 언제나 카드 개수, 위치, 숫자가 같음
 * l, r = 각 더미 인덱스라고 하면
 * dp[l][r]: 왼쪽 더미의 인덱스가 l이고 오른쪽 더미 인덱스가 r일때 최대 점수
 * - 오른쪽이 작은 경우 (card[l] > card[r])
 * dp[l][r+1] = max(dp[l][r+1], dp[l][r] + card[r])
 * dp[l+1][r+1], dp[l+1][r] 갱신
 * - 오른쪽이 큰 경우 (card[l] <= card[r])
 * dp[l+1][r+1], dp[l+1][r] 갱신
 * */
public class Main {
    static int N;
    static int[][] cards;
    static int[][] dp;
    static final int L = 0, R = 1;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        cards = new int[2][N];

        for (int i=0; i<2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                cards[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N+1][N+1];
        // 최댓값이 0일 수 있으므로 -1로 채우기
        for (int i=0; i<=N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));

    }
    static int dfs(int l, int r) {
        if (l == N || r == N) return 0;
        if (dp[l][r] != -1) return dp[l][r];
        int ret = 0;
        // 오른쪽이 더 작은 경우
        if (cards[L][l] > cards[R][r]) {
            ret = Math.max(ret, dfs(l, r+1)+cards[R][r]);
        }
        ret = Math.max(ret, dfs(l+1, r+1));
        ret = Math.max(ret, dfs(l+1, r));

        return dp[l][r] = ret;
    }

}
