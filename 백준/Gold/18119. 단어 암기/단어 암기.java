import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] bits = new int[N];

        // O(N*L)
        for (int i=0; i<N; i++) {
            String word = br.readLine();
            int bit = 0;
            for (char c: word.toCharArray()) {
                int digit = c - 'a' + 1;
                bit |= (1 << digit);
            }
            bits[i] = bit;
        }


        int info = (1<<27) - 1;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int digit = c - 'a' + 1;
            if (oper == 1) {
                info ^= (1<<digit);
            } else {
                info |= (1<<digit);
            }
            int cnt = 0;
            for (int bit: bits) {
                if ((bit & info) == bit) cnt++;
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);

    }
}
