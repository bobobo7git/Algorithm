import java.io.*;
import java.util.*;

public class Main {
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T--> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int O = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());

            int[][] grid = new int[N+1][M+1];   // 값: 장애물 높이
            for (int i=0; i<O; i++) {
                st = new StringTokenizer(br.readLine());
                int or = Integer.parseInt(st.nextToken());
                int oc = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                grid[or][oc] = l;
            }

            /*
            * 같은 위치에 더 적은 힘을 들이는 걸 먼저 탐색
            * 다익스트라
            * */
            int[][] dist = new int[N+1][M+1];
            for (int i=0; i<=N; i++) {
                Arrays.fill(dist[i], 11111);
            }
            Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                return o1[2] - o2[2];
            });
            pq.offer(new int[]{sr, sc, 0});
            dist[sr][sc] = 0;
            boolean success = false;
            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                if (now[0] == er && now[1] == ec) {
                    success = true;
                    break;
                }
                if (dist[now[0]][now[1]] < now[2]) continue;
                int cur = grid[now[0]][now[1]];
                int hp = F - now[2];
                for (int i=0; i<4; i++) {
                    int nr = now[0] + dr[i];
                    int nc = now[1] + dc[i];
                    if (nr <= 0 || nc <= 0 || nr > N || nc > M) continue;
                    int cost = now[2] + 1;
                    // 지금까지 사용한 힘으로 이동이 불가능하거나 너무 높은 경우
                    if (cost > F || grid[nr][nc] - cur > hp) continue;
                    // 이미 더 적은 힘으로 지나온 경우
                    if (dist[nr][nc] <= cost) continue;
                    dist[nr][nc] = cost;
                    pq.offer(new int[]{nr, nc, cost});
                }
            }

            sb.append(success ? "잘했어!!" : "인성 문제있어??").append('\n');
        }
        System.out.print(sb);
    }
}
