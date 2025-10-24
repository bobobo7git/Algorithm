import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int l = 0;
        int r = 0;
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            r += arr[i];
            l = Math.max(l, arr[i]);
        }
        while (l <= r) {
            int mid = (l+r) / 2;
            int sum = 0;
            int cnt = 1;
            int max = 0;

            int gCnt = 0;
            int gIdx = 0;
            for (int i=0; i<N; i++) {
                if (sum + arr[i] > mid) {
                    if (cnt > M) break;
                    sum = arr[i];
                    cnt++;
                } else {
                    sum += arr[i];
                }
                max = Math.max(max, sum);
            }
            // 너무 잘게 쪼개지면 값을 느슨하게 바꿔줌
            // 최댓값 제한을 올려줌
            if (cnt > M) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(l).append('\n');

        int sum = 0;
        int cnt = 0;
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<N; i++) {
            if (sum + arr[i] <= l) {
                sum += arr[i];
                cnt++;
            }
            else {
                sum = arr[i];
                list.add(cnt);
                cnt = 1;
            }
        }
        list.add(cnt);
        while (list.size() < M) {
            for (int i = 0; i< list.size(); i++) {
                if (list.get(i) > 1) {
                    int val = list.get(i);
                    list.set(i, val-1);
                    list.add(i, 1);
                    break;
                }
            }
        }

        for (int g: list) {
            sb.append(g).append(' ');
        }

        System.out.println(sb);

    }
}
