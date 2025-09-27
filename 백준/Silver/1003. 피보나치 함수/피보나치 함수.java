import java.io.*;
import java.util.*;
public class Main {
    static long[][] memo;
    public static void main(String... args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            memo = new long[N+1][2];
            for (int i=0; i<=N; i++) {
                memo[i][0] = -1;
                memo[i][1] = -1;
            }

            sb.append(f(N)[0]).append(' ').append(f(N)[1]).append('\n');
        }
        System.out.print(sb);
    }
    static long[] f(int n) {
        if (n <= 0) return new long[] {1, 0};
        if (n == 1) return new long[] {0, 1};

        if (memo[n][0] != -1 && memo[n][1] != -1) return memo[n];
        memo[n][0] += f(n-1)[0] + f(n-2)[0] + 1;
        memo[n][1] += f(n-1)[1] + f(n-2)[1] + 1;
        return memo[n];
    }
}
