import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int l = 0, r = 0;
            long sum = 0;
            for (;r<M; r++) {
                sum += arr[r];
            }
            if (N == M) {
                if (sum < K) sb.append(1).append('\n');
                else sb.append(0).append('\n');
                continue;
            }
            int cnt = 0;
            while (l < N) {
                if (sum < K) cnt++;

                sum += arr[r];
                sum -= arr[l++];
                r = (r+1) % N;
            }

            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}

