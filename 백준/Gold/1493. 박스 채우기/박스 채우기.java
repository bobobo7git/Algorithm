import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        int N = Integer.parseInt(br.readLine());
        // 0: size, 1: cnt
        int[][] cubes = new int[N][2];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            // n이 증가하는 순서로 주어짐
            int n = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            cubes[i] = new int[]{n, cnt};
        }
        Arrays.sort(cubes, (o1, o2) -> {
            return o2[0] - o1[0];
        });

        System.out.println(divide(width, length, height, cubes));

    }
    static int divide(int w, int l, int h, int[][] cubes) {
        if (w == 0 || l == 0 || h == 0) return 0;
        // 가장 큰 큐브부터 넣어보기
        int min = Math.min(w, Math.min(l, h));
        int a = 0; // 결정된 큐브의 변

        for (int i=0; i<cubes.length; i++) {
            if (cubes[i][1] == 0) continue;
            if (1 << cubes[i][0] <= min) {
                a = 1 << cubes[i][0];
                cubes[i][1]--;
                break;
            }
        }
        // 큐브를 결정할 수 없다면 종료
        if (a == 0) return -1;
        // 가장 큰 큐브를 소모했으니 이 값부터 출발
        int ret = 1;

        int f = divide(a, a, h-a, cubes);
        int s = divide(w-a, a, h, cubes);
        int t = divide(w, l-a, h, cubes);
        if (f == -1 || s == -1 || t == -1) return -1;
        return ret + f + s + t;
    }

}

