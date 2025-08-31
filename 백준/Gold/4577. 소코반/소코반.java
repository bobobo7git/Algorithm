import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if (R == 0 && C == 0) break;

            char[][] grid = new char[R][C];
            for (int i=0; i<R; i++) {
                String line = br.readLine();
                for (int j=0; j<C; j++) {
                    grid[i][j] = line.charAt(j);
                }
            }

            String cmd = br.readLine();
            Game game = Game.getInstance();
            game.init(grid, cmd);
            game.run();
            game.render();
        }
    }
    static class Game {
        public static Game instance;

        char[][] grid;
        char[][] map;
        // URDL
        static final int[] dr = {-1, 0, 1, 0};
        static final int[] dc = {0, 1, 0, -1};
        int R, C;
        int TARGETS;
        int targetCnt;
        Player player;
        char[] cmd;

        StringBuilder sb;
        static int ROUND;

        private Game() {}
        public static Game getInstance() {
            if (instance == null) {
                instance = new Game();
                ROUND = 0;
            }
            ROUND++;
            return instance;
        }
        public void init(char[][] grid, String cmd) {
            this.grid = grid;
            R = grid.length;
            C = grid[0].length;
            map = new char[R][C];
            targetCnt = 0;
            TARGETS = 0;

            sb = new StringBuilder();
            sb.append("Game ").append(ROUND).append(": ");

            for (int i=0; i<R; i++) {
                for (int j=0; j<C; j++) {
                    map[i][j] = grid[i][j];
                    if (map[i][j] == 'w') {
                        map[i][j] = '.';
                        grid[i][j] = '.';
                        player = new Player(i, j);
                    }
                    else if (map[i][j] == 'W') {
                        map[i][j] = '+';
                        grid[i][j] = '+';
                        player = new Player(i, j);
                    }
                    else if (map[i][j] == 'b') {
                        grid[i][j] = '.';
                        TARGETS++;
                    }
                    else if (map[i][j] == 'B') {
                        targetCnt++;
                        TARGETS++;
                        map[i][j] = 'b';
                        grid[i][j] = '+';
                    }

                }
            }

            this.cmd = cmd.toCharArray();
        }

        public void run() {
            for (char c: cmd) {
                int dir = cmd2dir(c);

                int nr = player.r + dr[dir];
                int nc = player.c + dc[dir];
                if (oob(nr, nc)) continue;

                char obj = map[nr][nc];

                if (obj != 'b' && obj != '#') {
                    player.move(nr, nc);
                } else if (obj == 'b') {
                    int boxR = nr + dr[dir];
                    int boxC = nc + dc[dir];


                    if (map[boxR][boxC] == 'b' || map[boxR][boxC] == '#') continue;
                    map[nr][nc] = grid[nr][nc];
                    map[boxR][boxC] = 'b';
                    player.move(nr, nc);
                    if (grid[nr][nc] == '+' && grid[boxR][boxC] != '+') targetCnt--;
                    else if (grid[nr][nc] != '+' && grid[boxR][boxC] == '+') targetCnt++;

                }
                if (targetCnt == TARGETS) break;
            }
        }
        private boolean oob(int r, int c) {
            return r < 0 || c < 0 || r >= R || c >= C;
        }
        private void render() {
            if (targetCnt != TARGETS) sb.append("in");
            sb.append("complete").append('\n');

            for (int i=0; i<R; i++) {
                for (int j=0; j<C; j++) {
                    char obj = map[i][j];
                    if (grid[i][j] == '+') {
                        if (obj == 'b') obj = 'B';
                        if (player.r == i && player.c == j) obj = 'W';
                    } else if (player.r == i && player.c == j) obj = 'w';
                    sb.append(obj);
                }
                sb.append('\n');
            }
            System.out.print(sb);
        }
        private int cmd2dir(char c) {
            switch (c) {
                case 'U': return 0;
                case 'R': return 1;
                case 'D': return 2;
                case 'L': return 3;
            }
            return -1;
        }

    }
    static class Player {
        int r, c;
        public Player(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public void move(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
