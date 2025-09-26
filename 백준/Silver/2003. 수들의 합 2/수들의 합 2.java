import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        int sum = 0;
        int l = 0, r = 0;
        // two pointer [l, r]
        while (l <= r && r < N) {
            sum += arr[r++];

            while (sum > M) {
                sum -= arr[l++];
            }
            if (sum == M) cnt++;
        }
        System.out.println(cnt);
    }
}

