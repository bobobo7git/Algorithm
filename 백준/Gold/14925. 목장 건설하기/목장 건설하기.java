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
                grid[i][j] += Integer.parseInt(st.nextToken());
                // 2차원 누적합
                // 0,0부터 i,j까지 합
                grid[i][j] += grid[i-1][j];
                grid[i][j] += grid[i][j-1];
                grid[i][j] -= grid[i-1][j-1];
            }
        }
        int L = 0;
        int R = Math.min(N, M);
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                int l = L;
                int r = R;
                while (l <= r) {
                    int mid = (l+r) / 2;
                    // 변이 너무 길면 줄이기
                    if (i-mid < 0 || j-mid < 0) {
                        r = mid-1;
                    }
                    // 정사각형 가능한지 체크
                    else {
                        int rec = grid[i][j] - grid[i-mid][j] - grid[i][j-mid] + grid[i-mid][j-mid];
                        if (rec == 0) {
                            l = mid+1;
                        } else {
                            r = mid-1;
                        }
                    }
                }
                L = Math.max(L, r);

            }
        }
        System.out.println(L);

    }
}

