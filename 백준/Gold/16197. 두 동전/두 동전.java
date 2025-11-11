import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] grid = new char[N][M];

        int[] start = new int[4];
        Arrays.fill(start, -1);
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'o') {
                    if (start[0] == -1) {
                        start[0] = i;
                        start[1] = j;
                    } else {
                        start[2] = i;
                        start[3] = j;
                    }
                }
            }
        }
        int[][][][] dist = new int[N][M][N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                for (int k=0; k<N; k++) {
                    Arrays.fill(dist[i][j][k], -1);
                }
            }
        }

        final int[] dr = {1, 0, -1, 0};
        final int[] dc = {0, 1, 0, -1};

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(start);
        dist[start[0]][start[1]][start[2]][start[3]] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int cost = dist[now[0]][now[1]][now[2]][now[3]];
            for (int i=0; i<4; i++) {
                int nr1 = now[0] + dr[i];
                int nc1 = now[1] + dc[i];
                int nr2 = now[2] + dr[i];
                int nc2 = now[3] + dc[i];
                if (oob(nr1, nc1, N, M) && oob(nr2, nc2, N, M)) continue;
                if (oob(nr1, nc1, N, M) || oob(nr2, nc2, N, M)) {
                    if (cost+1 > 10) {
                        System.out.println(-1);
                        return;
                    }
                    System.out.println(cost+1);
                    return;
                }
                if (grid[nr1][nc1] == '#') {
                    nr1 = now[0];
                    nc1 = now[1];
                }
                if (grid[nr2][nc2] == '#') {
                    nr2 = now[2];
                    nc2 = now[3];
                }
                if (dist[nr1][nc1][nr2][nc2] != -1) continue;
                dist[nr1][nc1][nr2][nc2] = cost+1;
                q.offer(new int[]{nr1, nc1, nr2, nc2});
            }
        }

        System.out.println(-1);
    }
    static boolean oob(int r, int c, int N, int M) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }

}
