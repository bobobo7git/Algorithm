import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        /*
        모든 점 bfs(N*M)
        모든 열에 대해서 수행하면 O(N*M*M) = 500*500*500 = 125,000,000
        석유들 덩어리로 bfs -> O(NM)
        열마다 석유들 번호로 갯수 확인 O(NM)
        -> O(NM+NM)
        */
        int N = land.length;
        int M = land[0].length;
        int[][] group = new int[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        final int[] dr = {1, 0, -1, 0};
        final int[] dc = {0, 1, 0, -1};
        int idx = 1;
        // 갯수 테이블
        int[] cnts = new int[N*M+1];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (land[i][j] == 1 && group[i][j] == 0) {
                    // bfs
                    q.offer(new int[]{i, j});
                    group[i][j] = idx;
                    while (!q.isEmpty()) {
                        int[] now = q.poll();
                        cnts[idx]++;
                        for (int k=0; k<4; k++) {
                            int nr = now[0]+dr[k];
                            int nc = now[1]+dc[k];
                            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                                continue;
                            if (land[nr][nc] != 1 || group[nr][nc] != 0)
                                continue;
                            group[nr][nc] = idx;
                            q.offer(new int[]{nr, nc});
                            
                        }
                    }
                    // -- bfs 종료
                    idx++;   
                }
            }
            
        }
        // 각 열마다 시추
        for (int c=0; c<M; c++) {
            Set<Integer> vis = new HashSet<>();
            int cnt = 0;
            for (int r=0; r<N; r++) {
                if (group[r][c] != 0) vis.add(group[r][c]);
            }
            for (int n: vis) {
                cnt += cnts[n];
            }
            answer = Math.max(cnt, answer);
        }
        return answer;
    }
}