import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static Tile[][] map;
    static int[] dr = {0, -1, 0, 1};   // w n e s
    static int[] dc = {-1, 0, 1, 0};   // w n e s
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        map = new Tile[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[N][M];
        int id = 0;
        int max = 0;
        List<Integer> cnts = new ArrayList<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (!visited[i][j]) {
                    int cnt = bfs(id++, i, j, visited);
                    cnts.add(cnt);
                    max = Math.max(cnt, max);
                }
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                map[i][j].n = cnts.get(map[i][j].id);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnts.size()).append('\n');
        sb.append(max).append('\n');
        sb.append(findMaxByAdjacentRoomIndex()).append('\n');
        System.out.print(sb);

    }
    static int bfs(int id, int sr, int sc, boolean[][] visited) {
        Queue<Position> q = new ArrayDeque<>();
        q.offer(new Position(sr, sc));

        visited[sr][sc] = true;
        map[sr][sc] = new Tile(id, 0);

        int cnt = 0;
        while (!q.isEmpty()) {
            Position now = q.poll();
            cnt ++;

            int wallInfo = grid[now.r][now.c];
            Tile tile = map[now.r][now.c];
            for (int i=0; i<4; i++) {
                if ((wallInfo & (1<<i)) != (1<<i)) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];
                    if (visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    q.offer(new Position(nr, nc));
                    map[nr][nc] = new Tile(tile.id, tile.n);
                }
            }
        }
        return cnt;
    }
    static int findMaxByAdjacentRoomIndex() {
        int max = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                for (int k=0; k<4; k++) {
                    int ni = i + dr[k];
                    int nj = j + dc[k];
                    if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                    if (map[ni][nj].id != map[i][j].id) {
                        max = Math.max(max, map[i][j].n + map[ni][nj].n);
                    }
                }
            }
        }
        return max;
    }

    static class Position {
        int r, c;
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class Tile {
        int id;
        int n;
        public Tile(int id, int n) {
            this.id = id;
            this.n = n;
        }
//        @Override
//        public String toString() {
//            return String.format("(%d, %d)", id, n);
//        }
    }
}

