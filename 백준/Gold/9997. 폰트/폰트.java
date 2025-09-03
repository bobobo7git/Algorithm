import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dict;
    static int cnt;
    static final int check = (1<<26) - 1;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        dict = new int[N];
        cnt = 0;
        for (int i=0; i<N; i++) {
            char[] word = br.readLine().toCharArray();
            for (char c: word) {
                dict[i] |= 1 << (c - 'a');
            }
        }

        comb(-1, 0);
        System.out.println(cnt);

    }
    static void comb(int idx, int mask) {
        if ((mask & check) == check) {
            cnt++;
            // return;
        }
        for (int i=idx+1; i<N; i++) {
            comb(i, mask | dict[i]);
        }
    }
}
