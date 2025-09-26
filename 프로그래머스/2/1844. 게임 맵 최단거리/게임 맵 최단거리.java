import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int N = maps.length;
        int M = maps[0].length;
        final int[] dr = {1, 0, -1, 0};
        final int[] dc = {0, 1, 0, -1};
        
        int[][] dist = new int[N][M];
        Position start = new Position(0, 0);
        Queue<Position> q = new ArrayDeque<>();
        q.offer(start);
        dist[0][0] = 1;
        
        while (!q.isEmpty()) {
            Position now = q.poll();
            for (int i=0; i<4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (maps[nr][nc] == 0 || dist[nr][nc] != 0) continue;
                dist[nr][nc] = dist[now.r][now.c] + 1;
                q.offer(new Position(nr, nc));
            }
        }
        return answer = dist[N-1][M-1] == 0 ? -1 : dist[N-1][M-1];
    }
    static class Position {
        int r, c;
        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}