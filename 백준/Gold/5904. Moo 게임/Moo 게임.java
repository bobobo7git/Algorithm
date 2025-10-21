import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Integer> memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int k = 0;
        memo = new HashMap<>();
        memo.put(0, 3);
        while (L(k) < N) k++;
        
        divide(k, N);
    }
    static void divide(int k, int i) {
        // S(k)의 i번째를 찾기
        // L(k) 위에 있는가?
        if (k == 0) {
            if (i==1) System.out.println('m');
            else System.out.println('o');
            return;
        }
        int len = L(k-1);
        if (i <= len) {
            divide(k-1, i);
            return;
        }
        else if (i <= len + k+3) {
            if (i == len+1) {
                System.out.println('m');
                return;
            }
            System.out.println('o');
            return;
        }
        divide(k-1, i-len-(k+3));
    }
    static int L(int N) {
        if (memo.containsKey(N)) return memo.get(N);
        int result = 2*L(N-1) + N+3;
        memo.put(N, result);
        return result;
    }
}

