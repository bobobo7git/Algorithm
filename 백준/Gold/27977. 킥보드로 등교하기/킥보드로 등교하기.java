
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] charge = new int[N+1];
        int prev = 0;
        int diff = 0;
        for (int i=0; i<N; i++) {
            charge[i] = Integer.parseInt(st.nextToken());
            diff = Math.max(diff, charge[i]-prev);
            prev = charge[i];
        }
        charge[N] = L;
        diff = Math.max(diff, L-charge[N-1]);
        int l = diff;
        int r = L;

        while (l <= r) {
            int mid = (l+r) / 2;
            // 현재 배터리 mid로 각 칸에서 가능한 멀리 이동
            int cur = 0;
            int cnt = 0;
            int p = 0;

            while (p <= N) {
                if (charge[p] - cur > mid) {
                    cur = charge[p-1];
                    cnt++;
                } else p++;
            }

            if (cnt <= K) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        System.out.println(l);

    }
}
