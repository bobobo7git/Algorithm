import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // prefix sum
        int[][] pa = new int[R][C];
        int[][] pb = new int[R][C];

        for (int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++) {
                String val = st.nextToken();

                if (val.charAt(0) == 'A') pa[i][j] = Integer.parseInt(val.substring(1));
                else pb[i][j] = Integer.parseInt(val.substring(1));

                if (i > 0) {
                    pa[i][j] += pa[i-1][j];
                    pb[i][j] += pb[i-1][j];
                }
            }
        }

        long[][] dp = new long[R][C];
        dp[0][0] = pa[R-1][0] - pa[0][0];
        // - \ |

        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                // -> 아래 a 합 더하기, 위 b합 더하기
                if (j < C-1) {
                    dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j] + pa[R-1][j+1]-pa[i][j+1]);
                    if (i > 0) dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j] + pa[R-1][j+1]-pa[i][j+1] + pb[i-1][j+1]);
                }
                // \ 아래 a합 더하기 위 b합 더하기
                if (j < C-1 && i < R-1) {
                    dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + pa[R-1][j+1]-pa[i+1][j+1] + pb[i][j+1]);
                }
                // | a 값 빼기
                if (i < R-1) {
                    dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] - (pa[i+1][j] - pa[i][j]));
                    dp[i+1][j] = Math.max(0, dp[i+1][j]);
                }
            }
        }

        System.out.println(dp[R-1][C-1]);

    }
}
