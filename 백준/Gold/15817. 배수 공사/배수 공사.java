import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] len = new int[N+1];
        int[] cnt = new int[N+1];
        // 길이가 짧은 것부터 주어지고, 길이는 유일함
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            len[i] = l;
            cnt[i] = c;
        }

        /*
        * 각 길이별로 수량이 정해져있음
        * 작은 파이프부터 연결하면서 x짜리 파이프를 연결
        * -> 파이프는 정렬되어있다.
        * dp[i][j]: i번째 파이프를 썼을 때 길이 j를 만들 수 있는 경우의 수
        * dp[i-k(<i)][j-len[k]
        * */
        int[][] dp = new int[N+1][x+1];
        dp[0][0] = 1;
        for (int i=1; i<=N; i++) {
            // i번 파이프를 써서 만들 수 있는 길이를 채워넣음
            for (int c=0; c<=cnt[i]; c++) {
                int l = len[i]*c;
                for (int j=l; j<=x; j++) {
                    dp[i][j] += dp[i-1][j-l];
                }
            }
        }

//        for (int i=0; i<=N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        System.out.println(dp[N][x]);

    }
}
