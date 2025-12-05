import java.util.*;
class Solution {
    static int[][] adjArr;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        adjArr = new int[N+1][N+1];
        for (int i=0; i<=N; i++) {
            Arrays.fill(adjArr[i], K+1);
        }
        for (int[] r: road) {
            adjArr[r[0]][r[1]] = Math.min(adjArr[r[0]][r[1]], r[2]);
            adjArr[r[1]][r[0]] = Math.min(adjArr[r[1]][r[0]], r[2]);
        }
        
        // dijkstra
        Queue<int[]> pq = new PriorityQueue<>((o1, o2)-> {
           return o1[1] - o2[1];
        });
        int[] dist = new int[N+1];
        Arrays.fill(dist, K+1);
        
        pq.offer(new int[]{1, 0});
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int v = now[0];
            if (now[1] > dist[v]) continue;
            for (int i=1; i<=N; i++) {
                if (adjArr[v][i] == K+1) continue;
                
                int ncost = now[1] + adjArr[v][i];
                if (dist[i] > ncost) {
                    dist[i] = ncost;
                    pq.offer(new int[]{i, ncost});
                }
                
            }
        } // end while
        for (int i=1; i<=N; i++) {
            if (dist[i] <= K) answer++;
        }
        

        return answer;
    }
}