import java.io.*;
import java.util.*;

public class Main {
    static int[][] papers;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        papers = new int[N][2];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            papers[i] = new int[] {a, b};
        }
        /*
        * 1. 그리디가 가능한가?
        * 1) 가로 또는 세로로 정렬
        * - (20, 3), (5, 5), (4, 4), (2, 2)
        * - 이러면 (20, 3)을 선택하지 않는 게 최선
        * 2) 넓이가 넓은 순서로 정렬
        * - 마찬가지
        * 2. DP
        * dp = 현재 선택한 색종이 위로 쌓을 수 있는 색종이의 최대 장수
        * 마찬가지로 위 예시에서 선택하지 않는 게 최선인 경우가 있음
        * 이전에 선택했던 색종이에 관계없이 더 깊이 가려면
        * 어차피 현재 색종이보다 작아야 함
        * 메모이제이션
        * memo[i] = i번째 색종이가 맨 밑에있을 때 최대 색종이 장수
        * 만약 i번째 색종이 위에 j번 색종이를 올릴 수 있다면
        * dp[i] = dp[j]+1
        * */
        dp = new int[N];
        int ans = 0;
        for (int i=0; i<N; i++) {
            ans = Math.max(ans, dfs(i)+1);
        }
        System.out.println(ans);
    }
    static int dfs(int i) {
        if (dp[i] != 0) return dp[i];

        int ret = 0;
        for (int j=0; j<papers.length; j++) {
            if (i==j) continue;
            if (papers[i][0] >= papers[j][0] && papers[i][1] >= papers[j][1]) {
                ret = Math.max(ret, dfs(j)+1);
            }
            // 90도 돌리기
            if (papers[i][0] >= papers[j][1] && papers[i][1] >= papers[j][0]) {
                ret = Math.max(ret, dfs(j)+1);
            }
        }
        return dp[i] = ret;
    }
}

