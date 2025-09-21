/*
* dp: (i, j)가 오른쪽 꼭지인 정사각형의 최대 길이
* 임의의 점 (i,j)에서 크기 2의 정사각형을 만드려면
* 좌, 위, 좌상대각에서 전부 1 크기의 정사각형을 만들 수 있어야 한다.
* 크기 3의 정사각형을 만드려면
* 좌, 위, 좌상대각에서 전부 2 크기의 정사각형을 만들 수 있어야 한다.
* 크기 k의 정사각형을 만드려면
* 좌, 위, 좌상대각에서 전부 k-1 크기의 정사각형을 만들 수 있어야 한다.
*
* 임의의 점 (i,j)에서 크기가 k인 정사각형을 만들 수 있다면
* 크기가 1...k인 모든 정사각형을 만들 수 있다.
* 좌, 위, 좌상대각을 각각 (i,j-1), (i-1,j), (i-1,j-1)이라고 하면
* 위 세 점에서 만들 수 있는 정사각형 한 변의 최솟값을 m이라고 할때
* 세 점에서 m 크기의 정사각형은 만들 수 있다.
* 따라서 (i,j)가 비어있다면 m+1 크기의 정사각형을 만들 수 있다.
* */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N+1][M+1];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N+1][M+1];
        int L = 0;
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (grid[i][j] > 0) continue;
                dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
                L = Math.max(L, dp[i][j]);
            }
        }
        System.out.println(L);
    }

    static int min(int... args) {
        int m = args[0];
        for (int i: args) {
            m = Math.min(m, i);
        }
        return m;
    }
}
