import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // two pointer [l, r]
        int l = 0;
        int r = 0;
        int[] cnt = new int[100001];
        int k = 1;
        int max = 0;
        while (l <= r && r < N) {
            cnt[arr[r]]++;

            while (cnt[arr[r]] > K) {
                cnt[arr[l++]]--;
            }

            max = Math.max(max, r-l+1);
            r++;
        }
        System.out.println(max);

    }
}
