import java.io.*;
import java.util.*;

public class Main {
    static int W, H;
    static char[][] grid;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) {
                break;
            }

            grid = new char[H][W];
            int dirtIdx = '0';
            Robot start = null;

            for (int i=0; i<H; i++) {
                String line = br.readLine();
                for (int j=0; j<W; j++) {
                    char c = line.charAt(j);
                    grid[i][j] = c;
                    if (c == '*') {
                        grid[i][j] = (char) dirtIdx++;
                    } else if (c == 'o') {
                        start = new Robot(i, j, 0, 0);
                    }
                }
            }

            Robot.req = (1 << (dirtIdx - '0'))-1;
            visited = new boolean[1023][H][W];

            int result = bfs(start);
            sb.append(result).append('\n');
        }
        System.out.println(sb.toString());
    }
    static int bfs(Robot start) {
        Queue<Robot> q = new ArrayDeque<>();
        q.offer(start);
        visited[0][start.r][start.c] = true;

        while (!q.isEmpty()) {
            Robot now = q.poll();
            // base condition
            if (now.allCleaned()) {
                return now.cnt;
            }

            for (int i=0; i<4; i++) {
                int nr = now.r + Robot.dr[i];
                int nc = now.c + Robot.dc[i];
                if (outOfBound(nr, nc) || grid[nr][nc] == 'x') continue;

                Robot next = new Robot(nr, nc, now.cnt+1, now.cleaned);
                if (grid[nr][nc] >= '0' && grid[nr][nc] <= '9') {
                    next.clean(grid[nr][nc] - '0');
                }

                if (visited[next.cleaned][nr][nc]) continue;
                q.offer(next);
                visited[next.cleaned][nr][nc] = true;
            }
        }
        return -1;
    }
    static boolean outOfBound(int r, int c) {
        return r < 0 || c < 0 || r >= H || c >= W;
    }
    static class Robot {
        int r, c;
        int cnt;
        int cleaned;
        static int req;
        static final int[] dr = {1, 0, -1, 0};
        static final int[] dc = {0, 1, 0, -1};

        public Robot(int r, int c, int cnt, int cleaned) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.cleaned = cleaned;
        }
        public void clean(int idx) {
            if (isCleaned(idx)) return;
            this.cleaned |= (1<<idx);
        }
        private boolean isCleaned(int idx) {
            return (cleaned & (1<<idx)) == 1;
        }
        public boolean allCleaned() {
            return (cleaned & req) == req;
        }
    }
    static class Dirt {
        int r, c;
        int i;
        public Dirt(int r, int c, int i) {
            this.r = r;
            this.c = c;
            this.i = i;
        }
    }
}
