import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solve(N, C, arr));
    }
    static int solve(int N, int C, int[] arr) {
        Arrays.sort(arr);

        int l = 0;
        int r = 1000000000;

        while (l <= r) {
            int mid = (l+r) / 2;
            // 인접한 공유기 사이 거리가 mid로 C개 선택 가능한지 검사
            int cnt = 1;
            int now = arr[0];
            for (int i=1; i<N; i++) {
                // 후보보다 적은 거리면 다음 집으로 넘어감
                if (arr[i] - now < mid) continue;
                // 크면 가능함
                cnt++;
                now = arr[i];
            }
            if (cnt >= C) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return r;

    }


}
