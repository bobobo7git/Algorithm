import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        char[][][] grid = new char[9][8][8];
        for (int i=0; i<8; i++) {
            String line = br.readLine();
            for (int j=0; j<8; j++) {
                grid[0][i][j] = line.charAt(j);
            }
        }
        // 시간 t의 grid 상태
        for (int t=1; t<=8; t++) {
            for (int j=0; j<8; j++) {
                grid[t][0][j] = '.';
            }
            for (int i=7; i>0; i--) {
                for (int j=0; j<8; j++) {
                    grid[t][i][j] = grid[t-1][i-1][j];
                }
            }
        }

        Node start = new Node(7, 0, 0);
        boolean[][][] visited = new boolean[9][8][8];

        Queue<Node> q = new ArrayDeque<>();
        q.offer(start);
        int ans = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.r == 0 && now.c == 7) {
                ans = 1;
                break;
            }
            if (grid[now.t][now.r][now.c] == '#') continue;

            for (int i=0; i<9; i++) {
                int nr = now.r + Node.dr[i];
                int nc = now.c + Node.dc[i];
                int nt = Math.min(8, now.t+1);
                if (oob(nr, nc)) continue;
                if (grid[now.t][nr][nc] == '#' || visited[nt][nr][nc]) continue;
                q.offer(new Node(nr, nc, nt));
                visited[nt][nr][nc] = true;
            }
        }
        System.out.println(ans);




    }
    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= 8 || c >= 8;
    }
    static class Node {
        int r, c;
        int t;
        // 12시부터 clockwise + 대기
        static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
        static final int[] dc = {0, 1, 1, 1, 0, -1, -1, -1, 0};
        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = Math.min(t, 8);
        }
    }
}
