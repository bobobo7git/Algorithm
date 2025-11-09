import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] woks = new int[M+1];
        for (int i=1; i<=M; i++) {
            woks[i] = Integer.parseInt(st.nextToken());
        }
        /*
        * 같은 크기 웍이 여러개 가능
        * 한번에 두개까지 웍 사용 가능
        * 5그릇 = 4그릇 + 1그릇
        * 4그릇 = 1그릇 + 3그릇 or 1웍+3웍(=1)
        * 1그릇 = 1웍
        * 3그릇 = 3웍
        * 2개 웍을 사용하여 만들 수 있는 경우의 수를 일단 구한다.
        * */
        int[] dp = new int[N+1];
        Arrays.fill(dp, 11111);
        dp[0] = 0;
        Set<Integer> set = new HashSet<>();
        for (int i=1; i<=M; i++) {
            set.add(woks[i]);
            for (int j=i+1; j<=M; j++) {
                int s = woks[i]+woks[j];
                set.add(s);
            }
        }
        for (int i=1; i<=N; i++) {
            for (int w: set) {
                if (i-w >= 0 && dp[i-w] != 11111) dp[i] = Math.min(dp[i], dp[i-w]+1);
            }
        }
        System.out.println(dp[N] == 11111 ? -1 : dp[N]);

    }

}
