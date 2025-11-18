import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] broken = new int[N+1];
        for (int i=0; i<B; i++) {
            int b = Integer.parseInt(br.readLine());
            broken[b] = 1;
        }
        /*
        * K개 연속하게 위치해야 함
        * N = 100,000 -> 하나씩 치우기 X
        * 슬라이딩 윈도우
        * 윈도우에서 부서진 신호등 갯수세기 O(1)
        * 윈도우마다 수리해야할 신호등 수 갱신
        * O(N)
        * */
        int l = 1;
        int r = 1;
        // 윈도우 초기화
        int cnt = 0;
        while (r <= K) {
            if (broken[r++] == 1) cnt++;
        }

        int min = cnt;
        while (r <= N) {
            if (broken[r++] == 1) cnt++;
            if (broken[l++] == 1) cnt--;

            min = Math.min(min, cnt);
        }
        System.out.println(min);

    }

}