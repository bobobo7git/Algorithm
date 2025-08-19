import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int[] dist = new int[N+2];  // i-1번째 정비소에서 i번째 정비소까지 거리
        int[] time = new int[N+2];  // i번째 정비소 정비시간

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N+1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[N+2];
        for (int i=1; i<=N+1; i++) {
            dp[i] = Integer.MAX_VALUE+1L;
        }

        int[] route = new int[N+2];
        for (int i=0; i<=N+1; i++) {
            // i번째 정비소에서 갈 수 있는 정비소들
            int d = 0;
            for (int j=i+1; j<=N+1; j++) {
                d += dist[j];
                if (d <= L) {
                    if (dp[j] > dp[i]+time[j]) {
                        dp[j] = dp[i]+time[j];
                        route[j] = i;
                    }

                } else break;
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        if (dp[N+1] < Integer.MAX_VALUE+1L) {
            int now = N+1;
            while (now > 0) {
                now = route[now];
                if (now == 0) break;
                stack.push(now);
            }
        }
        int cnt = stack.size();
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

//        String result = sb.reverse().toString().trim();
//        StringTokenizer resSt = new StringTokenizer(result);
//
        System.out.println(dp[N+1]);
        System.out.println(cnt);
        if (cnt > 0) {
            System.out.println(sb.toString());
        }
//        System.out.println(resSt.countTokens());
//        if (dp[N+1] != 0) {
//            System.out.println(result);
//        }


    }
}
