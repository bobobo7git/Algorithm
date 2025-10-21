import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] buckets = new int[1_000_001];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            // 양동이 위치는 전부 다르다.
            buckets[x] = g;
        }

        // N = 100,000, g=10,000 -> N*g = 1,000,000,000
        int sum = 0;
        // 슬라이딩 윈도우
        int l = 0;
        int r = 0;
        while (r <= 1_000_000 && r <= K*2+1) {
            sum += buckets[r++];
        }
        int max = sum;
        while (r <= 1_000_000) {
            sum += buckets[r++];
            sum -= buckets[l++];
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}

