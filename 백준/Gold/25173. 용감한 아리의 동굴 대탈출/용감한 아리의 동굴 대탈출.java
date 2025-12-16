import java.io.*;
import java.util.*;

public class Main {
    static Obj[][] grid;
    static int N, M;
    static final int[] dr = {-1, 0, 1, 0};  // U R D L
    static final int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new Obj[N][M];
        Player player = null;
        Boss boss = null;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                int n = Integer.parseInt(st.nextToken());
                Obj obj = Obj.Builder.create(n);
                if (obj == null) continue;
                obj.stat.r = i;
                obj.stat.c = j;
                grid[i][j] = obj;
                if (obj instanceof Player) player = (Player) obj;
                else if (obj instanceof Boss) boss = (Boss) obj;
            }
        }
        // 능력치
        st = new StringTokenizer(br.readLine());
        player.stat.hp = Integer.parseInt(st.nextToken());
        player.stat.atk = Integer.parseInt(st.nextToken());
        boss.stat.hp = Integer.parseInt(st.nextToken());
        boss.stat.atk = Integer.parseInt(st.nextToken());

//        for (int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(grid[i]));
//        }

        // 최초 입력시 아리와 보스는 인접해있고 보스의 방향=아리의 방향
        for (int i=0; i<4; i++) {
            int nr = player.stat.r + dr[i];
            int nc = player.stat.c + dc[i];
            if (nr == boss.stat.r && nc == boss.stat.c) {
                player.stat.dir = (i+2) % 4;
                boss.stat.dir = (i+2) % 4;
            }
        }

        boolean game = true;
        while (game) {
//            System.out.println("##### START ######");
//            System.out.println("HP: "+player.stat.hp+" "+boss.stat.hp);
//            System.out.println("DIR: "+player.stat.dir+" "+boss.stat.dir);
//            for (int i=0; i<N; i++) {
//                System.out.println(Arrays.toString(grid[i]));
//            }
//            System.out.println();
            // 1. 아리의 공격
            player.attack(boss);
//            System.out.println("HP: "+player.stat.hp+" "+boss.stat.hp);
            if (!boss.isAlive()) break;
            // 2. 아리의 이동
            int pr = player.stat.r;
            int pc = player.stat.c;

            player.move();
            if (!player.isAlive()) break;
//            System.out.println("##### PLAYER MOVE ######");
//            System.out.println("HP: "+player.stat.hp+" "+boss.stat.hp);
//            System.out.println("DIR: "+player.stat.dir+" "+boss.stat.dir);
//            for (int i=0; i<N; i++) {
//                System.out.println(Arrays.toString(grid[i]));
//            }
//            System.out.println();
            // 3. 보스의 공격
            Obj stone = boss.search();
            if (stone != null) {
                int sr = stone.stat.r;
                int sc = stone.stat.c;
                stone.stat.atk = boss.stat.atk;
                stone.move();
                if (stone.stat.atk > 0) {
                    stone.attack(player);
                }
                stone.stat.r = sr;
                stone.stat.c = sc;
            }
            if (!player.isAlive()) break;
            // 4. 보스의 이동
            if (player.stat.r == pr && player.stat.c == pc) continue;
            int bsr = boss.stat.r;
            int bsc = boss.stat.c;
            boss.stat.r = pr;
            boss.stat.c = pc;
            boss.stat.dir = player.stat.dir;
            grid[boss.stat.r][boss.stat.c] = boss;
            grid[bsr][bsc] = null;
//            System.out.println("##### BOSS MOVE ######");
//            System.out.println("DIR: "+player.stat.dir+" "+boss.stat.dir);
//            for (int i=0; i<N; i++) {
//                System.out.println(Arrays.toString(grid[i]));
//            }
//            System.out.println();

        }
        System.out.println(player.isAlive() ? "VICTORY!" : "CAVELIFE...");

    }
    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
    static abstract class Obj {
        Status stat;
        abstract void move();
        void attack(Obj o) {
            o.stat.hp = Math.max(0, o.stat.hp - this.stat.atk);
        }
        void attack(Obj o, int amount) {
            o.stat.hp = Math.max(0, o.stat.hp - amount);
        }
        boolean isAlive() {
            return this.stat.hp > 0;
        }
        @Override
        public String toString() {
            return this.stat.type+"";
        }

        static class Status {
            int r, c;
            int dir;
            int hp, atk;
            int type;
            Status(int type) {
                this.type = type;
            }
            Status(int dir, int hp, int atk) {
                this.dir = dir;
                this.hp = hp;
                this.atk = atk;
            }
        }
        static final int BLANK = 0;
        static final int STONE = 1;
        static final int PLAYER = 2;
        static final int BOSS = 3;

        static class Builder {
            static Obj create(int type) {
                Status stat = new Status(type);
                Obj obj = switch (type) {
                    case STONE -> new Stone();
                    case PLAYER -> new Player();
                    case BOSS -> new Boss();
                    default -> null;
                };

                if (obj != null) obj.stat = stat;
                return obj;
            }
        }
    }

    static class Player extends Obj {
        void move() {
            int d = stat.dir;
            int i=0;
            int r = stat.r;
            int c = stat.c;
            for (; i<4; i++) {
                int nr = stat.r + dr[d];
                int nc = stat.c + dc[d];
                if (oob(nr, nc) || grid[nr][nc] != null) {
                    d = (d+1) % 4;
                }
                else break;
            }

            if (i == 4) {
                stat.hp -= 4;
                return;
            }

            stat.dir = d;
            stat.r += dr[stat.dir];
            stat.c += dc[stat.dir];
            stat.hp -= i;

            grid[r][c] = null;
            grid[stat.r][stat.c] = this;
        }
    }
    static class Boss extends Obj {
        void move() {

        }

        Obj search() {
            int d = stat.dir;
            int r = stat.r;
            int c = stat.c;
            // l만큼 앞으로 전진 + 회전 + l만큼 앞으로 전진 + 회전
            int l = 1;
            int cnt = 1;
            while (cnt < N*M) {
                for (int i=0; i<2; i++) {
                    for (int j=0; j<l; j++) {
                        r += dr[d];
                        c += dc[d];
                        if (oob(r, c)) continue;
                        if (grid[r][c] instanceof Stone) return grid[r][c];
                        cnt++;
                    }
                    d = (d+1) % 4;
                }
                l++;
            }
            return null;
        }
    }
    static class Stone extends Obj {
        void move() {
            // bfs
            Queue<int[]> q = new ArrayDeque<>();
            int[][] visited = new int[N][M];
            q.offer(new int[]{stat.r, stat.c});
            visited[stat.r][stat.c] = 1;
            while (!q.isEmpty()) {
                int[] now = q.poll();
                if (grid[now[0]][now[1]] instanceof Player) {
                    stat.atk -= visited[now[0]][now[1]] - 1;
//                    System.out.println("POS: "+stat.r+" "+stat.c);
//                    System.out.println("ATT: " + stat.atk);
                    return;
                }
                for (int i=0; i<4; i++) {
                    int nr = now[0] + dr[i];
                    int nc = now[1] + dc[i];
                    if (oob(nr, nc) || visited[now[0]][now[1]] + 1 > stat.atk) continue;
                    if (visited[nr][nc] != 0) continue;
                    Obj obj = grid[nr][nc];
                    if (obj == null || obj instanceof Player) {
                        q.offer(new int[]{nr, nc});
                        visited[nr][nc] = visited[now[0]][now[1]] + 1;
                    }
                }
            }
            stat.atk = -1;
        }
    }

}


