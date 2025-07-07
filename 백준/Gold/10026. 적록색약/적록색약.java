import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] grid;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        grid = new char[N][N];
        visited = new boolean[N][N];

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<N; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        StringBuilder sb = new StringBuilder();
        Queue<Node> q = new ArrayDeque<>();
        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (visited[i][j]) continue;
                cnt++;
                q.offer(new Node(i, j, grid[i][j]));
                visited[i][j] = true;
                while (!q.isEmpty()) {
                    Node now = q.poll();
                    for (int d=0; d<4; d++) {
                        int nr = now.r + Node.dr[d];
                        int nc = now.c + Node.dc[d];
                        if (outOfBound(nr, nc)) continue;
                        if (visited[nr][nc] || grid[nr][nc] != now.color) continue;
                        visited[nr][nc] = true;
                        q.offer(new Node(nr, nc, grid[nr][nc]));
                    }
                }

            }
        }
        int cnt2 = 0;
        visited = new boolean[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (visited[i][j]) continue;
                cnt2++;
                q.offer(new Node(i, j, grid[i][j]));
                visited[i][j] = true;
                while (!q.isEmpty()) {
                    Node now = q.poll();
                    for (int d=0; d<4; d++) {
                        int nr = now.r + Node.dr[d];
                        int nc = now.c + Node.dc[d];
                        if (outOfBound(nr, nc)) continue;
                        if (visited[nr][nc]) continue;
                        if (weakSight(now, nr, nc)) {
                            visited[nr][nc] = true;
                            q.offer(new Node(nr, nc, grid[nr][nc]));
                        }
                    }
                }

            }
        }
        sb.append(cnt).append(' ').append(cnt2);
        System.out.println(sb.toString());

    }
    static boolean outOfBound(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
    static boolean weakSight(Node now, int r, int c) {
        return (now.color == 'R' && grid[r][c] == 'G')
                || (now.color == 'G' && grid[r][c] == 'R')
                || now.color == grid[r][c];
    }
    static class Node {
        int r, c;
        char color;
        static final int[] dr = {1, 0, -1, 0};
        static final int[] dc = {0, 1, 0, -1};
        public Node(int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
}
