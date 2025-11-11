import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        /*
        * 기존 선택 숫자의 2배 이상
        * -> 무조건 오름차순 -> DP
        * * n=4 ,m=10
        * 1 2 4 8
        * 1 2 4 9
        * 1 2 4 10
        * 1 2 5 10
        * n<=10, m<=2,000
        * dp: i번째에 j를 고르는 경우의 수
        * dp[4][8] += dp[3][1...4]
        * dp[4][9] += dp[3][1...4]
        * dp[4][10] += dp[3][1...5]
        * */
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            long[][] dp = new long[N+1][M+1];
            // 첫번째로 고를 수 있는 수는 1~m 1가지
            for (int j=1; j<=M; j++) {
                dp[1][j] = 1L;
            }
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=M; j++) {
                    for (int k=1; k<=j/2; k++) {
                        dp[i][j] += dp[i-1][k];
                    }
                }
            }
            long sum = 0;
            for (int j=1; j<=M; j++) {
                sum += dp[N][j];
            }
            sb.append(sum).append('\n');
        }
        System.out.print(sb);
    }

}