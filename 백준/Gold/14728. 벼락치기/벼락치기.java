import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] test = new int[N+1][2];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            test[i] = new int[]{K, S};
        }
        /*
        * dp: 1~i번째 과목을 훑었을 때 j 시간을 들여 얻을 수 있는 최대 점수
        * */
        int[][] dp = new int[N+1][T+1];
        for (int i=1; i<=N; i++) {
            for (int j=0; j<=T; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                if (j >= test[i][0]) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-test[i][0]]+test[i][1]);
            }
        }

        System.out.println(dp[N][T]);
    }
}
