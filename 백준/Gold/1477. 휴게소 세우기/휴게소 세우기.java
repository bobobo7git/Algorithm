import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        // 휴게소 위치
        // 0과 L은 주어지지 않음
        if (N > 0) st = new StringTokenizer(br.readLine());
        int[] pos = new int[N+1];
        for (int i=0; i<N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }
        pos[N] = L;
        Arrays.sort(pos);
        // 휴게소 사이의 거리 최대값
        int max = pos[0];
        for (int i=1; i<=N; i++) {
            max = Math.max(max, pos[i]-pos[i-1]);
        }

        // 휴게소는 중복될 수 없으므로 최솟값은 1
        int l = 1;
        int r = max;
        
        while (l <= r) {
            int mid = (l+r) / 2; // key = 휴게소 사이 거리의 최댓값
            int cnt = 0; // key를 만족하기 위해 추가적으로 필요한 휴게소
            // key값으로 다음 휴게소를 갈 수 있는지 체크
            int key = mid;
            int cur = 0;

            int i = 0;
            while (i <= N) {
                if (cur + key < pos[i]) {
                    cur += key;
                    cnt++;
                } else {
                    cur = pos[i++];
                }
            }

            // M개 더 세워서 가능한거면 더 줄여도 됨
            if (cnt <= M) r = mid-1;
            else l = mid+1;
        }
        System.out.println(l);
    }
}
