
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] grid;
    static Unit[][] pos;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Unit[] units = new Unit[K+1];
        for (int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Unit u = new Unit(r-1, c-1, d-1, i);
            units[i] = u;
        }

        pos = new Unit[N][N];
        for (int i=1; i<=K; i++) {
            Unit unit = units[i];
            pos[unit.r][unit.c] = unit;
        }

        Game game = Game.getInstance();
        game.init(units);
        while (!game.isOver) {
            game.run(false);
        }
        System.out.println(game.turns > 1000 ? -1 : game.turns);


    }

    static class Game {
        static Game instance;
        Unit[] units;
        int turns;
        boolean isOver;
        public Game() {}
        public static Game getInstance() {
            if (instance != null) return instance;
            return instance = new Game();
        }
        private void init(Unit[] units) {
            this.units = units;
            turns = 0;
            isOver = false;
        }
        public void run(boolean debug) {
            if (turns > 1000) {
                isOver = true;
                return;
            }

            for (int i=1; i<=K; i++) {
                if (debug) System.out.printf("---%d Unit turn---\n", i);

                Unit unit = units[i];
                Unit root = pos[unit.r][unit.c];



                int nr = unit.r + Unit.dr[unit.d];
                int nc = unit.c + Unit.dc[unit.d];
                // wall, blue
                if (outOfBound(nr, nc) || grid[nr][nc] == 2) {
                    unit.turn();
                    nr = unit.r + Unit.dr[unit.d];
                    nc = unit.c + Unit.dc[unit.d];
                    // dont move
                    if (outOfBound(nr, nc) || grid[nr][nc] == 2) continue;
                }
                
                boolean isRoot = root.n == unit.n;
                if (!isRoot) root.disconnect(unit);
                
                // red
                if (grid[nr][nc] == 1) {
                    Unit leaf = unit.getLeaf();
                    unit.reverse(null);
                    unit = leaf;
                }
                // --- map update
                // empty space
                if (pos[nr][nc] == null) {
                    pos[nr][nc] = unit;
                } else {
                    pos[nr][nc].getLeaf().connect(unit);
                }
                // --- prev map update
                if (isRoot) pos[unit.r][unit.c] = null;
                // position update
                unit.move(nr, nc);

                if (pos[nr][nc].getHeight() >= 4) {
                    isOver = true;
                    turns++;
                    return;
                }
                if (debug) printMap();

            }
            turns++;
        }


        private boolean outOfBound(int r, int c) {
            return r < 0 || c < 0 || r >= N || c >= N;
        }
        private void printMap() {
            for (int i=0; i<N; i++) {
                System.out.println(Arrays.toString(pos[i]));
            }
            System.out.println();
            for (int i=1; i<=K; i++) {
                Unit unit = units[i];
                StringBuilder sb = new StringBuilder(unit.toString());
                Unit next = unit.next;
                while (next != null) {
                    sb.append(next).append(' ');
                    next = next.next;
                }
                sb.append(' ').append(unit.getHeight());
                System.out.println(sb);
            }
        }

    }
    static class Unit {
        int r, c;
        int d;
        int n;
        Unit next;
        static final int[] dr = {0, 0, -1, 1};
        static final int[] dc = {1, -1, 0, 0};
        public Unit(int r, int c, int d, int n) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.n = n;
            next = null;
        }
        public void move(int r, int c) {
            this.r = r;
            this.c = c;
            if (next == null) return;
            next.move(r, c);
        }
        private void reverse(Unit prev) {
            if (this.next == null) {
                this.next = prev;
                return;
            }
            this.next.reverse(this);
            this.next = prev;
        }
        private Unit getLeaf() {
            if (this.next == null) return this;
            return this.next.getLeaf();
        }
        public void turn() {
            if (d == 0) d = 1;
            else if (d == 1) d = 0;
            else if (d == 2) d = 3;
            else if (d == 3) d = 2;
        }
        public int getHeight() {
            int h = 1;
            if (next == null) return h;
            h += next.getHeight();
            return h;
        }
        public void connect(Unit o) {
            if (o.n == this.n) return;
            this.next = o;
        }
        public void disconnect(Unit target) {
            if (target == null || this.next == null) return;
            if (this.next == target) this.next = null;
            else this.next.disconnect(target);
        }
        @Override
        public String toString() {
            char[] chars = {'>', '<', '^', 'v'};
            return n+" "+chars[d];
        }
    }
}
