
import java.io.*;
import java.util.*;

public class Main {
    static int[] memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            memo = new int[N+1];

            if (N >= 1) memo[1] = 1;
            if (N >= 2) memo[2] = 2;
            if (N >= 3) memo[3] = 4;
            sb.append(f(N)).append('\n');
        }
        System.out.println(sb.toString());

    }
    static int f(int n) {
        if (n <= 0) return 0;
        if (memo[n] > 0) return memo[n];
        memo[n] = f(n-1) + f(n-2) + f(n-3);
        return memo[n];
    }
}
