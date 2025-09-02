import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (grid[i][j] == 0) {
                    int sum = 1;
                    set.clear();
                    for (int d=0; d<4; d++) {
                        int nr = i + Shape.dr[d];
                        int nc = j + Shape.dc[d];
                        if (oob(nr, nc) || grid[nr][nc] == 0) continue;
                        set.add(grid[nr][nc]);
                    }
                    for (Integer id: set) {
                        sum += Shape.id2size.get(id);
                    }
                    max = Math.max(max, sum);
                }
            }
        }
        
        System.out.println(max);
    }
    static void bfs() {
        Queue<Shape> q = new ArrayDeque<>();

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new Shape(i, j, Shape.ID));
                    Shape.id2size.put(Shape.ID, 0);
                    grid[i][j] = Shape.ID++;
                    while (!q.isEmpty()) {
                        Shape now = q.poll();
                        Shape.id2size.put(now.id, Shape.id2size.get(now.id)+1);
                        for (int d=0; d<4; d++) {
                            int nr = now.r + Shape.dr[d];
                            int nc = now.c + Shape.dc[d];
                            if (oob(nr, nc) || grid[nr][nc] != 1) continue;
                            q.offer(new Shape(nr, nc, now.id));
                            grid[nr][nc] = now.id;
                        }
                    }

                }
            }
        }

    }
    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
    static class Shape {
        int r, c;
        int id;
        static int ID = 2;
        static Map<Integer, Integer> id2size = new HashMap<>();
        static final int[] dr = {1, 0, -1, 0};
        static final int[] dc = {0, 1, 0, -1};
        public Shape(int r, int c, int id) {
            this.r = r;
            this.c = c;
            this.id = id;
        }

    }
}
