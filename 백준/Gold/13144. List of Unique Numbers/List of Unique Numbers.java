import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        /*
        * 1 2 3 4 5
        * 1 2 3에서 4로 연결하면
        * 2 3 4 / 3 4 / 4 가 추가됨
        * 1 2 3 4에서 5로 연결하면
        * 2 3 4 5 / 3 4 5 / 4 5 / 5가 추가됨
        *
        */
        boolean[] has = new boolean[100001];
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int l = 0, r = 0;
        has[arr[0]] = true;
        long cnt = 1;
        while (l <= r && r < N) {
            if (r < N-1 && !has[arr[r+1]]) {
                has[arr[++r]] = true;
                cnt += r-l+1;
            }
            else if (l == r) {
                l++; r++;
                if (l != N) cnt++;
            }
            else {
                has[arr[l++]] = false;
            }
        }
        System.out.println(cnt);
    }

}

