import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        /*
        N*N, N=25
        4방향으로 N^2 => 4^2500
        덜 꺾는 게 항상 최소의 비용도 아님
        다익스트라?
        */
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[3] - o2[3];
        });
        int N = board.length;
        // DRUL
        final int[] dr = {1, 0, -1, 0};
        final int[] dc = {0, 1, 0, -1};
        final int INF = 500*N*N;
        int[][][] dist = new int[N][N][4];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }
        
        // r, c, 방향, 비용
        pq.offer(new int[]{0, 0, 0, 0});
        pq.offer(new int[]{0, 0, 1, 0});
        dist[0][0][0] = 0;
        dist[0][0][1] = 0;
        answer = INF;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int r = now[0];
            int c = now[1];
            int d = now[2];
            int g = now[3];
            
            if (r == N-1 && c == N-1) {
                answer = Math.min(answer, g);
            }
            if (dist[r][c][d] < g) continue;
            for (int k=0; k<4; k++) {
                int nr = r+dr[k];
                int nc = c+dc[k];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (board[nr][nc] == 1) continue;
                // 같은 방향
                if (d==k) {
                    if (dist[nr][nc][k] > g+100) {
                        dist[nr][nc][k] = g+100;
                        pq.offer(new int[]{nr, nc, k, g+100});
                    }
                }
                // 다른 방향
                else {
                    if (dist[nr][nc][k] > g+600) {
                        dist[nr][nc][k] = g+600;
                        pq.offer(new int[]{nr, nc, k, g+600});
                    }
                }
            }
        }
        
        return answer;
    }
}