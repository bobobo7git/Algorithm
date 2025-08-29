import java.io.*;
import java.util.*;

public class Main {
    static int[][][] cube;
    static int[][][] build;
    static int[][] temp;
    static boolean[] selected;
    static Deque<Integer> stack;
    static boolean[][][] visited;
    static final int[] dz = {0, 0, 0, 0, -1, 1};
    static final int[] dr = {-1, 0, 1, 0, 0, 0};
    static final int[] dc = {0, 1, 0, -1, 0, 0};
    static final int INF = 125;
    static int min;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        cube = new int[5][5][5];
        build = new int[5][5][5];
        temp = new int[5][5];
        selected = new boolean[5];
        stack = new ArrayDeque<>();
        visited = new boolean[5][5][5];
        min = INF;

        for (int i=0; i<5; i++) {
            for (int r=0; r<5; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c=0; c<5; c++) {
                    cube[i][r][c] = Integer.parseInt(st.nextToken());
                }
            }
        }

        for (int i=0; i<5; i++) {
            selected[i] = true;
            stack.push(i);
            selectBoardOrder(i);
            stack.pop();
            selected[i] = false;
        }

        min = min == INF ? -1 : min;
        System.out.println(min);


    }
    static void selectBoardOrder(int z) {

        int h = stack.size()-1;
        int id = stack.peek();

        copyBoard(build[h], cube[id]);
        for (int d=0; d<4; d++) {
            rotateBoard(build[h], temp);
            copyBoard(build[h], temp);
            if (stack.size() == 5) {
                min = Math.min(min, bfs());
            }
            for (int i=0; i<5; i++) {
                if (selected[i]) continue;
                selected[i] = true;
                stack.push(i);
                selectBoardOrder(i);
                stack.pop();
                selected[i] = false;
            }
        }
    }

    static void rotateBoard(int[][] board, int[][] temp) {
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                temp[i][j] = board[4-j][i];
            }
        }

    }
    static void copyBoard(int[][] board, int[][] temp) {
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }
    static int bfs() {
        if (build[0][0][0] == 0) return INF;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0, 0});
        visited = new boolean[5][5][5];
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (isOver(now[0], now[1], now[2])) return now[3];
            for (int d=0; d<6; d++) {
                int nz = now[0] + dz[d];
                int nr = now[1] + dr[d];
                int nc = now[2] + dc[d];
                if (outOfBound(nz, nr, nc)) continue;
                if (visited[nz][nr][nc] || build[nz][nr][nc] == 0) continue;

                visited[nz][nr][nc] = true;
                q.offer(new int[]{nz, nr, nc, now[3]+1});
            }
        }
        return INF;
    }
    static boolean isOver(int z, int r, int c) {
        return z == 4 && r == 4 && c == 4;
    }
    static boolean outOfBound(int z, int r, int c) {
        return z < 0 || r < 0 || c < 0 || z >= 5 || r >= 5 || c >= 5;
    }
}
