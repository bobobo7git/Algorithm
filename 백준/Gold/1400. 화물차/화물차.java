import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            char[][] grid = new char[N][M];
            int sr = 0, sc = 0;
            int crossCnt = 0;
            Cross[] crossArr = new Cross[10];

            for (int i=0; i<N; i++) {
                String line = br.readLine();
                for (int j=0; j<M; j++) {
                    grid[i][j] = line.charAt(j);
                    if (grid[i][j] == 'A') {
                        sr = i;
                        sc = j;
                    } else if (isDigit(grid[i][j])) {
                        crossCnt++;
                    }
                }
            }
            for (int i=0; i<crossCnt; i++) {
                st = new StringTokenizer(br.readLine());
                int id = Integer.parseInt(st.nextToken());
                char dir = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
//                crossList.add(new Cross(dir, a, b));
                crossArr[id] = new Cross(dir, a, b);
            }
            br.readLine();

            int res = dijkstra(sr, sc, grid, crossArr);
            if (res > 0) sb.append(res);
            else sb.append("impossible");
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static int dijkstra(int sr, int sc, char[][] grid, Cross[] crossArr) {
        int N = grid.length;
        int M = grid[0].length;
        int[][] dist = new int[N][M];
        final int INF = 999999;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                dist[i][j] = INF;
            }
        }

        Queue<Car> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.t - o2.t;
        });
        pq.offer(new Car(sr, sc, 0));
        dist[sr][sc] = 0;

        while (!pq.isEmpty()) {
            Car now = pq.poll();
            if (grid[now.r][now.c] == 'B') return now.t;

            for (int i=0; i<4; i++) {
                int nr = now.r + Car.dr[i];
                int nc = now.c + Car.dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || grid[nr][nc] == '.') continue;
                int nt = now.t;

                if (grid[nr][nc] == '#' || grid[nr][nc] == 'B') nt++;
                else if (isDigit(grid[nr][nc])) {
                    Cross cross = crossArr[grid[nr][nc] - '0'];
                    nt = cross.pass(i<2, now.t);
                }
                if (dist[nr][nc] <= nt) continue;

                pq.offer(new Car(nr, nc, nt));
                dist[nr][nc] = nt;
            }

        }
        return -1;
    }
    static boolean isDigit(char c) {
        int d = c - '0';
        return d >= 0 && d <= 9;
    }
    static class Car {
        int r, c;
        int t;
        static final int[] dr = {0, 0, 1, -1};
        static final int[] dc = {1, -1, 0, 0};
        public Car(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }
    static class Cross {
        char dir;
        int a, b;
        public Cross(char dir, int a, int b) {
            this.dir = dir;
            this.a = a;
            this.b = b;
        }
        /*
        ew: 동서 여부
        시간 t 이후 ew방향이 녹색불이 켜지는 최소 시간
        */
        public int pass(boolean ew, int time) {
            int p = time % (a+b);
            if (dir == '-') {
                if (ew) {
                    if (p < a) return time+1;
                    return time + (a+b) - p + 1;
                } else {
                    if (p >= a) return time+1;
                    return time + (a-p) + 1;
                }
            } else if (dir == '|') {
                if (!ew) {
                    if (p < b) return time+1;
                    return time + (a+b) - p + 1;
                } else {
                    if (p >= b) return time+1;
                    return time + (b-p) + 1;
                }
            }
            return -1;
        }
    }
}

