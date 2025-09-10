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
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        // 같은 수 가능
        int l = 0;
        int r = 0;
        long min = arr[N-1] - arr[0];
        while (l <= r && r < N) {
            int diff = arr[r] - arr[l];
            if (diff >= M) {
                if (diff <= min) min = diff;
                l++;
            } else r++;
        }
        System.out.println(min);
    }
}

