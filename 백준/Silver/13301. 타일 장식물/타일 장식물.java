import java.io.*;
import java.util.*;

public class Main {
    static long[] memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        memo = new long[81];
        memo[1] = 1;
        memo[2] = 1;
        System.out.println(fibo(N) * 4 + fibo(N-1)*2);
    }
    static long fibo(int n) {
        if (n == 1 || n == 2) return 1;
        if (n <= 0) return 0;
        if (memo[n] != 0) return memo[n];
        return memo[n] = fibo(n-1) + fibo(n-2);
    }
}
