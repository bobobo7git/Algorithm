
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        char[][] grid = new char[N][N];
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<N; j++) {
                grid[i][j] = line.charAt(j);

            }
        }
        /*
        * 012
        * 3.4
        * 567
        * */
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        Queue<int[]> q = new ArrayDeque<>();
        game: while (true) {
            boolean isOver = true;
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (grid[i][j] >= '0' && grid[i][j] <= '9') {
                        for (int k=0; k<8; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                            if (grid[nr][nc] == '#') q.offer(new int[]{nr, nc});
                        }
                        // 지뢰라고 확정
                        if (q.size() <= grid[i][j]-'0') {
                            while (!q.isEmpty()) {
                                int[] b = q.poll();
                                grid[b[0]][b[1]] = '*';
                                for (int k=0; k<8; k++) {
                                    int nr = b[0] + dr[k];
                                    int nc = b[1] + dc[k];
                                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                                    if (grid[nr][nc] > '0' && grid[nr][nc] <= '9') {
                                        grid[nr][nc]--;
                                        isOver = false;
                                    }
                                }
                            }
                        }
                        // 0이면 빈칸
                        else if (grid[i][j] == '0') {
                            while (!q.isEmpty()) {
                                int[] e = q.poll();
                                grid[e[0]][e[1]] = '.';
                                isOver = false;
                            }
                        }
//                        for (int x=0; x<N; x++) {
//                            System.out.println(Arrays.toString(grid[x]));
//                        }
//                        System.out.println();
                    }

                }
                if (isOver) break game;
            }

        }
//        for (int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(grid[i]));
//        }
//        System.out.println();
        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (grid[i][j] == '*' || grid[i][j] == '#') {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);

    }
}
