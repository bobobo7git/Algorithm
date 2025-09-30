import java.io.*;
import java.util.*;

public class Main {
    static int[] memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        memo = new int[N+1];
        System.out.println(pizza(N));
    }
    static int pizza(int n) {
        if (n == 1) return 0;
        if (memo[n] > 0) return memo[n];
        memo[n] += n/2 * (n-n/2);

        return memo[n] += pizza(n/2) + pizza(n - n/2);
    }
}

