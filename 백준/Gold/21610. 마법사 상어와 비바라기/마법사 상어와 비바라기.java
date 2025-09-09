import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] grid;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // init
        Queue<Cloud> q = new ArrayDeque<>();
        q.offer(new Cloud(N-2, 0));
        q.offer(new Cloud(N-2, 1));
        q.offer(new Cloud(N-1, 0));
        q.offer(new Cloud(N-1, 1));
        boolean[][] visited;

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            visited = new boolean[N][N];
            int cnt = q.size();

            while (cnt-- > 0) {
                Cloud cloud = q.poll();
                Position next = cloud.move(d, s);
                grid[next.r][next.c]++;

                visited[next.r][next.c] = true;
                q.offer(cloud);
            }
            while (!q.isEmpty()) {
                Cloud cloud = q.poll();
                grid[cloud.pos.r][cloud.pos.c] += cloud.magic(grid);
            }

            for (int r=0; r<N; r++) {
                for (int c=0; c<N; c++) {
                    if (grid[r][c] >= 2 && !visited[r][c]) {
                        q.offer(new Cloud(r, c));
                        grid[r][c] -= 2;
                    }
                }
            }
        }
        int sum = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                sum += grid[i][j];
            }
        }
        System.out.println(sum);

    }
    static class Position {
        int r, c;
        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
//        @Override
//        public String toString() {
//            return "("+r+", "+c+")";
//        }
    }
    static class Cloud {
        Position pos;
        static final int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
        static final int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

        Cloud(int r, int c) {
            this.pos = new Position(r, c);
        }
//        @Override
//        public String toString(){
//            return pos.toString();
//        }

        Position move(int d, int s) {
            int nr = (pos.r + dr[d]*s) % N;
            int nc = (pos.c + dc[d]*s) % N;
            pos.r = nr < 0 ? N + nr : nr;
            pos.c = nc < 0 ? N + nc : nc;

            return pos;
        }

        int magic(int[][] grid) {
            int cnt = 0;
            for (int i=2; i<=8; i+=2) {
                int nr = pos.r + dr[i];
                int nc = pos.c + dc[i];
                if (oob(nr, nc)) continue;
                if (grid[nr][nc] > 0) cnt++;
            }
            return cnt;
        }
        private boolean oob(int r, int c) {
            return r < 0 || c < 0 || r >= N || c >= N;
        }
    }
}

