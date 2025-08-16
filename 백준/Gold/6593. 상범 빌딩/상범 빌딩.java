import java.io.*;
import java.util.*;

public class Main {
    static int L, R, C;
    static char[][][] map;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];
            Position start = new Position();
            Position end = new Position();
            for (int h=0; h<L; h++) {
                for (int r=0; r<R; r++) {
                    String row = br.readLine();
                    for (int c=0; c<C; c++) {
                        map[h][r][c] = row.charAt(c);
                        if (map[h][r][c] == 'S') start = new Position(h, r, c, 0);
                        if (map[h][r][c] == 'E') end = new Position(h, r, c, 0);

                    }
                }
                br.readLine();
            }
            int time = bfs(start, end);
            if (time > 0) sb
                    .append("Escaped in ")
                    .append(time)
                    .append(" minute(s).")
                    .append('\n');
            else sb.append("Trapped!").append('\n');
        }
        System.out.print(sb);

    }
    static int bfs(Position start, Position end) {
        int t = 0;
        Queue<Position> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[L][R][C];
        q.offer(start);
        visited[start.h][start.r][start.c] = true;

        while (!q.isEmpty()) {
            Position now = q.poll();
            if (now.equals(end)) return now.t;
            for (int i=0; i<6; i++) {
                int nh = now.h + Position.dh[i];
                int nr = now.r + Position.dr[i];
                int nc = now.c + Position.dc[i];
                if (outOfBound(nh, nr, nc)) continue;
                if (map[nh][nr][nc] == '#' || visited[nh][nr][nc]) continue;
                visited[nh][nr][nc] = true;
                q.offer(new Position(nh, nr, nc, now.t+1));
            }
        }
        return 0;
    }
    static boolean outOfBound(int h, int r, int c) {
        return h < 0 || r < 0 || c < 0 || h >= L || r >= R || c >= C;
    }
    static class Position {
        int h, r, c;
        int t;
        static final int[] dh = {1, -1, 0, 0, 0, 0};
        static final int[] dr = {0, 0, 1, 0, -1, 0};
        static final int[] dc = {0, 0, 0, 1, 0, -1};
        public Position() {}
        public Position(int h, int r, int c, int t) {
            this.h = h;
            this.r = r;
            this.c = c;
            this.t = t;
        }
        @Override
        public boolean equals(Object o) {
            if (! (o instanceof Position)) return false;
            Position p = (Position) o;
            return (h == p.h && r == p.r && c == p.c);
        }
    }
}
