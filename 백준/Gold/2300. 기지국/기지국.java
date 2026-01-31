import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Point[] points;
    static int[] dp;
    static final int INF = 2_222_222;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        dp = new int[N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
            dp[i] = INF;
        }

        Arrays.sort(points, (o1, o2) -> {
            return o1.x - o2.x;
        });
        System.out.println(recur(0) == INF ? 2 : recur(0));
    }
    /*
    * i번째 좌표를 통신폭 cover로 커버하고 i..n번째 좌표를 모두 커버하는 최소 비용
    * i..j번째 좌표를 커버할 수 있는 최소 w를 찾고 j..N번째 좌표의 최소비용과 더함
    * */
    static int recur(int i) {
        if (i >= N) return 0;
        if (dp[i] != INF) return dp[i];
        int min = INF;
        int maxHeight = 0;
        for (int j=i; j<N; j++) {
            // 다음 좌표를 포함하는 통신폭
            // 가로는 i..j번째 좌표의 거리
            // x좌표가 같은 경우 최소 너비는 2
            int width = points[j].x - points[i].x;
            width = Math.max(width, 2);
            // 세로는 i..j번째 좌표 중 0에서 가장 먼 y * 2
            maxHeight = Math.max(maxHeight, Math.abs(points[j].y));
            int cover = Math.max(width, maxHeight*2);
            min = Math.min(min, cover + recur(j+1));
        }

        return dp[i] = min;
    }
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // to be erased
//        @Override
//        public String toString() {
//            return "("+x+", "+y+")";
//        }
    }
}
