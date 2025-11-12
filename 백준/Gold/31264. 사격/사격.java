import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] scores = new int[N+1];
        scores[N] = Integer.MAX_VALUE;
        for (int i=0; i<N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
        /*
        * si인 표적을 맞히면 실력도 si 증가
        * 항상 맞힐 수 있는 표적 중 가장 높은 표적을 맞힘
        * 동일 표적을 여러번 사격 가능
        * => 정렬해두고 순회하면서 맞힐 수 없는 표적을 만나면
        * 직전 표적을 가능할 때까지 계속 사격
        * */

        Arrays.sort(scores);
        int l = 0;
        int r = scores[N-1];

        while (l <= r) {
            int mid = (l+r) / 2;
            // 초기 사격점수 = 0
            long exp = mid;
            int cnt = 0;

            for (int i=0; i<=N; i++) {
                int s = scores[i];

                while (i > 0 && s > exp && exp >= scores[i-1] && cnt < M) {
                    exp += scores[i-1];
                    cnt++;
                }
            }

            if (exp-mid >= A) {
                r = mid-1;
            } else l = mid+1;
        }
        System.out.println(l);

    }

}