import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static int score;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice();
        Queue<Position> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        for (int i=0; i<K; i++) {
            dice.move();
            q.clear();
            for (int r=0; r<N; r++) {
                for (int c=0; c<M; c++) {
                    visited[r][c] = false;
                }
            }
            // score
            int number = grid[dice.r][dice.c];
            visited[dice.r][dice.c] = true;
            q.offer(new Position(dice.r, dice.c));
            while (!q.isEmpty()) {
                Position now = q.poll();
                score += grid[now.r][now.c];
                for (int d=0; d<4; d++) {
                    int nr = now.r + Dice.dr[d];
                    int nc = now.c + Dice.dc[d];
                    if (outOfBound(nr, nc)) continue;
                    if (visited[nr][nc] || grid[nr][nc] != number) continue;

                    q.offer(new Position(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            dice.changeDir();
        }

        System.out.println(score);
    }
    static boolean outOfBound(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
    static class Position {
        int r, c;
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Dice {
        int t, b;
        int n,e,s,w;
        int dir; // 0123: eswn
        int r, c;
        static final int[] dr = {0, 1, 0, -1};
        static final int[] dc = {1, 0, -1, 0};
        public Dice() {
            this.t = 1;
            this.n = 2;
            this.e = 3;
            this.w = 4;
            this.s = 5;
            this.b = 6;
            this.dir = 0;
            this.r = 0;
            this.c = 0;
        }

        public void move() {
            if (outOfBound()) dir = (dir+2) % 4;

            if (dir == 0) {
                moveEast();
            } else if (dir == 1) {
                moveSouth();
            } else if (dir == 2) {
                moveWest();
            } else if (dir == 3) {
                moveNorth();
            }

            movePosition();
        }
        public boolean outOfBound() {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            return nr < 0 || nc < 0 || nr >= N || nc >= M;
        }
        public void moveEast() {
            int t, b, n, e, s, w;
            t = this.t;
            b = this.b;
            n = this.n;
            e = this.e;
            s = this.s;
            w = this.w;

            this.b = e;
            this.e = t;
            this.t = w;
            this.w = b;
        }
        public void moveWest() {
            int t, b, n, e, s, w;
            t = this.t;
            b = this.b;
            n = this.n;
            e = this.e;
            s = this.s;
            w = this.w;

            this.b = w;
            this.e = b;
            this.t = e;
            this.w = t;
        }
        public void moveNorth() {
            int t, b, n, e, s, w;
            t = this.t;
            b = this.b;
            n = this.n;
            e = this.e;
            s = this.s;
            w = this.w;

            this.b = n;
            this.n = t;
            this.t = s;
            this.s = b;
        }
        public void moveSouth() {
            int t, b, n, e, s, w;
            t = this.t;
            b = this.b;
            n = this.n;
            e = this.e;
            s = this.s;
            w = this.w;

            this.b = s;
            this.n = b;
            this.t = n;
            this.s = t;
        }
        public void movePosition() {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            this.r = nr;
            this.c = nc;
        }

        public void changeDir() {
            int A = b;
            int B = grid[r][c];
            if (A > B) {
                dir = (dir+1) % 4;
            } else if (A < B) {
                dir = (dir + 3) % 4;
            } else return;
        }

    }
}
