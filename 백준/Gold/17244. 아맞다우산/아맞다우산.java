import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int S;
    static char[][] grid;
    public static void main(String[] args) throws IOException {
        State start = input();
        System.out.println(bfs(start));
    }
    static State input() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grid = new char[N][M];

        char stuff = '0';
        State ret = null;
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                char c = line.charAt(j);
                if (c == 'X') c = stuff++;
                if (c == 'S') {
                    c = '.';
                    ret = new State(i, j, 0);
                }
                grid[i][j] = c;
            }
        }
        S = stuff - '0';

        return ret;
    }
    static int bfs(State start) {
        int fin = (1 << S)-1;
        Queue<State> q = new ArrayDeque<>();
        int[][][] visited = new int[fin+1][N][M];
        final int INF = 1<<20;
        for (int d=0; d<=fin; d++) {
            for (int i=0; i<N; i++) {
                Arrays.fill(visited[d][i], INF);
            }
        }
        q.offer(start);
        visited[0][start.r][start.c] = 0;

        while (!q.isEmpty()) {
            State now = q.poll();
            for (int i=0; i<4; i++) {
                int nr = now.r + State.dr[i];
                int nc = now.c + State.dc[i];
                if (grid[nr][nc] == '#') continue;
                if (grid[nr][nc] == '.') {
                    if (visited[now.stuff][nr][nc] <= visited[now.stuff][now.r][now.c]+1) continue;
                    q.offer(new State(nr, nc, now.stuff));
                    visited[now.stuff][nr][nc] = visited[now.stuff][now.r][now.c]+1;
                }
                else if (grid[nr][nc] == 'E' && now.stuff == fin) {
                    return visited[now.stuff][now.r][now.c]+1;
                }
                // stuff
                else if (grid[nr][nc] != 'E') {
                    int s = grid[nr][nc] - '0';
                    int next = now.obtain(s);
                    if (visited[next][nr][nc] <= visited[now.stuff][now.r][now.c]+1) continue;
                    q.offer(new State(nr, nc, next));
                    visited[next][nr][nc] = visited[now.stuff][now.r][now.c]+1;
                }
            }
        }

        return -1;
    }

    static class State {
        int r, c;
        int stuff;
        static final int[] dr = {1, 0, -1, 0};
        static final int[] dc = {0, 1, 0, -1};
        State(int r, int c, int stuff) {
            this.r = r;
            this.c = c;
            this.stuff = stuff;
        }
        boolean has(int stuff) {
            return (this.stuff & (1 << stuff)) == (1 << stuff);
        }
        int obtain(int stuff) {
            return this.stuff | (1 << stuff);
        }
    }
}
