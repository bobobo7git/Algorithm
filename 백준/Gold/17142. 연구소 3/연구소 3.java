import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static List<Virus> virusList;
    static final int INF = 2555;
    public static void main(String[] args) throws IOException {
        input();
        Deque<Virus> stack = new ArrayDeque<>();
        System.out.println(combination(0, stack));
    }
    static int combination(int idx, Deque<Virus> stack) {
        if (stack.size() == M) {
            return bfs(stack);
        }

        int t = INF;

        for (int i=idx; i<virusList.size(); i++) {
            stack.push(virusList.get(i));
            int res = combination(i+1, stack);
            if (res >= 0) t = Math.min(t, res);
            stack.pop();
        }

        return t == INF ? -1 : t;
    }
    static int bfs(Deque<Virus> stack) {
        Queue<Virus> q = new ArrayDeque<>();
        int[][] times = new int[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (grid[i][j] == 1) times[i][j] = -1;
                else times[i][j] = INF;
            }
        }
        for (Virus v: virusList) {
            times[v.r][v.c] = -2;
        }

        for (Virus v: stack) {
            q.offer(v);
            times[v.r][v.c] = 0;
        }

        while (!q.isEmpty()) {
            Virus now = q.poll();
            for (int i=0; i<4; i++) {
                int nr = now.r + Virus.dr[i];
                int nc = now.c + Virus.dc[i];
                if (outOfBound(nr, nc) || grid[nr][nc] == 1) continue;
                if (times[nr][nc] == INF || times[nr][nc] == -2)  {

                    times[nr][nc] = times[now.r][now.c] + 1;
                    q.offer(new Virus(nr, nc));
                }
            }
        }
        for (Virus v: virusList) {
            times[v.r][v.c] = 0;
        }
        
        int max = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (times[i][j] == INF) {
                    return -1;
                }
                max = Math.max(max, times[i][j]);
            }
        }
        return max;
    }
    static boolean outOfBound(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
    static void render(int[][] map) {
        for (int i=0; i<N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
    static void input() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][N];

        virusList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                int n = Integer.parseInt(st.nextToken());
                grid[i][j] = n;
                if (n == 2) virusList.add(new Virus(i, j));
            }
        }
    }

    static class Virus {
        int r, c;
        static final int[] dr = {1, 0, -1, 0};
        static final int[] dc = {0, 1, 0, -1};
        public Virus(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
