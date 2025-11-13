import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[][] grid = new int[501][501];
        // 위험
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int x=Math.min(x1, x2); x<=Math.max(x1, x2); x++) {
                for (int y=Math.min(y1, y2); y<=Math.max(y1, y2); y++) {
                    grid[x][y] = 1;
                }
            }
        }
        // 죽음
        int M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int x=Math.min(x1, x2); x<=Math.max(x1, x2); x++) {
                for (int y=Math.min(y1, y2); y<=Math.max(y1, y2); y++) {
                    grid[x][y] = 2;
                }
            }
        }
        final int[] dx = {1, 0, -1, 0};
        final int[] dy = {0, 1, 0, -1};
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[501][501];
        visited[0][0] = true;
        dq.offer(new int[]{0, 0, 0});

        while (!dq.isEmpty()) {
            int[] now = dq.poll();
            int hp = now[2];
            if (now[0] == 500 && now[1] == 500) {
                System.out.println(hp);
                return;
            }
            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx > 500 || ny > 500) continue;
                if (grid[nx][ny] == 2) continue;
                if (grid[nx][ny] == 0) {
                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    dq.offerFirst(new int[]{nx, ny, hp});
                } else if (grid[nx][ny] == 1) {
                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    dq.offerLast(new int[]{nx, ny, hp+1});
                }
            }
        }
        System.out.println(-1);

    }

}