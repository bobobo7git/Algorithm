import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static final int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static final int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                char c = line.charAt(j);
                grid[i][j] = c == '.' ? 0 : c - '0';
            }
        }
        System.out.println(simulate());
    }
    static int simulate() {
        /*
         * H x W = 1,000,000
         * 매 파도마다 격자 다 조회는 x
         * 무너질 예정인 애들만 큐에 넣고
         * 뺄 때 8방 카운트를 1씩 빼줌
         * */
        Queue<int[]> q = new ArrayDeque<>();
        // 8방향 0의 개수를 저장
        int[][] counts = new int[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                // 0일 경우에만 체크
                if (grid[i][j] != 0) continue;
                counts[i][j] = -1;
                for (int k=0; k<8; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (oob(nr, nc) || grid[nr][nc] == 0 || counts[nr][nc] == -1) continue;
                    counts[nr][nc]++;
                    if (counts[nr][nc] >= grid[nr][nc]) {
                        q.offer(new int[]{nr, nc});
                        counts[nr][nc] = -1;
                    }
                }
            }
        }
//        for (int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(counts[i]));
//        }
//        System.out.println();
        int inning = 0;
        while (true) {
            int cnt = q.size();
            if (cnt == 0) break;
            inning++;
            while (cnt-- > 0) {
                int[] now = q.poll();
                grid[now[0]][now[1]] = 0;
                for (int i=0; i<8; i++) {
                    int nr = now[0] + dr[i];
                    int nc = now[1] + dc[i];
                    if (oob(nr, nc) || grid[nr][nc] == 0 || counts[nr][nc] == -1) continue;
                    counts[nr][nc]++;
                    if (counts[nr][nc] >= grid[nr][nc]) {
                        q.offer(new int[]{nr, nc});
                        counts[nr][nc] = -1;
                    }
                }
            }
//            for (int i=0; i<N; i++) {
//                System.out.println(Arrays.toString(grid[i]));
//            }
//            System.out.println();
        }
        return inning;
    }
    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }

}
