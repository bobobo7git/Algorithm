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
        int sr = -1, sc = -1;
        int er1 = -1, ec1 = -1, er2 = -1, ec2 = -1;
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'S') {
                    sr = i; sc = j;
                }
                else if (grid[i][j] == 'C') {
                    if (er1 == -1) {
                        er1 = i; ec1 = j;
                    } else {
                        er2 = i; ec2 = j;
                    }
                }
            }
        }
        // 우하좌상
        final int[] dr = {0, 1, 0, -1};
        final int[] dc = {1, 0, -1, 0};
        int[][][][][] dist = new int[N][M][4][2][2];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                for (int k=0; k<4; k++) {
                    for (int l=0; l<2; l++) {
                        Arrays.fill(dist[i][j][k][l], Integer.MAX_VALUE);
                    }
                }
            }
        }
        Queue<int[]> q = new ArrayDeque<>();
        // 모든 방향 삽입
        for (int dir=0; dir<4; dir++) {
            q.offer(new int[]{sr, sc, dir, 0, 0});
            dist[sr][sc][dir][0][0] = 0;
        }
        int result = -1;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int f1 = now[3];
            int f2 = now[4];
            int cost = dist[now[0]][now[1]][now[2]][f1][f2];
            if (f1 == 1 && f2 == 1) {
                result = cost;
                break;
            }
            for (int dir=0; dir<4; dir++) {
                if (dir == now[2]) continue;
                int nr = now[0] + dr[dir];
                int nc = now[1] + dc[dir];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                int nf1 = nr == er1 && nc == ec1 ? 1 : f1;
                int nf2 = nr == er2 && nc == ec2 ? 1 : f2;
                if (dist[nr][nc][dir][nf1][nf2] <= cost+1 || grid[nr][nc] == '#') continue;
                dist[nr][nc][dir][nf1][nf2] = cost+1;
                q.offer(new int[] {nr, nc, dir, nf1, nf2});
            }
        }
        System.out.println(result);


    }
}
