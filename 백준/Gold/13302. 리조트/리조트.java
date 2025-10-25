import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        boolean[] cant = new boolean[N+1];
        int M = Integer.parseInt(st.nextToken());
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<M; i++) {
                cant[Integer.parseInt(st.nextToken())] = true;
            }
        }

        /*
        * dp[i][j]
        * i번째 날짜에 쿠폰이 j장 있고 가능한 날짜에 전부 참여했을 때 지불한 최소비용
        * N <= 100 -> 최대 37,000 * 100 -> int
        * 쿠폰의 최대 개수는 전부 5일짜리만 구매할 경우 최대 20일*2 = 40
        * */
        int K = 40; // 쿠폰의 최대 개수
        int[][] dp = new int[N+1][K+1];
        final int INF = 9999999;
        // 0번째 날은 0원
        for (int i=0; i<=N; i++) {
            for (int j=0; j<=K; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0;
        final int[] price = {10_000, 25_000, 37_000};

        // 내일부터 1일, 3일, 5일 이용할 이용권 쇼핑
        // 오늘까지 지불한 비용으로 누적계산
        for (int day=0; day<N; day++) {
            // 구매할 수 없으면 패스
            if (cant[day+1]) {
                for (int coupon=0; coupon<=K; coupon++) {
                    dp[day+1][coupon] = dp[day][coupon];
                }
                continue;
            }

            // 1일권
            if (day+1 <= N) {
                for (int coupon=0; coupon<=K; coupon++) {
                    dp[day+1][coupon] = Math.min(dp[day+1][coupon], dp[day][coupon]+price[0]);
                }
            }
            // 3일권
            // 하루 남기고 3일권 사는 것도 가능
            for (int nextDay=day+1; nextDay<=day+3 && nextDay<=N; nextDay++) {
                for (int coupon=0; coupon<K; coupon++) {
                    dp[nextDay][coupon+1] = Math.min(dp[nextDay][coupon+1], dp[day][coupon]+price[1]);
                }
            }
            // 5일권
            for (int nextDay=day+1; nextDay<=day+5 && nextDay<=N; nextDay++) {
                for (int coupon=0; coupon<K-1; coupon++) {
                    dp[nextDay][coupon+2] = Math.min(dp[nextDay][coupon+2], dp[day][coupon]+price[2]);
                }
            }
            // 쿠폰
            if (day+1 <= N) {
                for (int coupon=3; coupon<=K; coupon++) {
                    if (dp[day][coupon] == INF) continue;
                    dp[day+1][coupon-3] = Math.min(dp[day+1][coupon-3], dp[day][coupon]);
                }
            }
        }

        int min = INF;
        for (int i=0; i<=K; i++) {
            min = Math.min(min, dp[N][i]);
        }
        System.out.println(min);

    }
}

