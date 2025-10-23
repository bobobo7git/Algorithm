import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        /*
        * 쓰레기가 있는 칸을 가장 적게 -> 옆을 지나가는 칸을 가장 적게
        * 가중치를 같이 계산하면 섞임
        * 따로따로 저장해서 들고다녀야 함
        * 쓰레기를 g 인접을 t라고 하면
        * g=1, t=4 > g=2, t=1
        * 같은 칸에 서로다른 g t가 도착하면?
        * .S..g..
        * .......
        * .g..g..
        * .......
        * ......F
        * */
        // r c 쓰레기 쓰레기 인접
        int[] start = new int[4];
        char[][] grid = new char[N][M];

        final int[] dr = {1, 0, -1, 0};
        final int[] dc = {0, 1, 0, -1};

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'S') start = new int[]{i, j, 0, 0};

            }
        }
        // 쓰레기 인접구역 표시
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (grid[i][j] == 'g') {
                    for (int k=0; k<4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                        if (grid[nr][nc] == '.') grid[nr][nc] = 't';
                    }
                }
            }
        }

        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] != o2[2]) return o1[2] - o2[2];
            return o1[3] - o2[3];
        });

        int[][][] dist = new int[N][M][2];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                dist[i][j][0] = 65536;
                dist[i][j][1] = 65536;
            }
        }
        pq.offer(start);
        dist[start[0]][start[1]][0] = 0;
        dist[start[0]][start[1]][1] = 0;
        int[] result = new int[2];
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            if (grid[now[0]][now[1]] == 'F') {
                result[0] = now[2];
                result[1] = now[3];
                break;
            }
            if (now[2] > dist[now[0]][now[1]][0]) continue;
            if (now[3] > dist[now[0]][now[1]][1]) continue;

            for (int i=0; i<4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (grid[nr][nc] == 'g') {
                    if (dist[nr][nc][0] > now[2]+1) {
                        dist[nr][nc][0] = now[2]+1;
                        dist[nr][nc][1] = now[3];
                        pq.offer(new int[]{nr, nc, now[2]+1, now[3]});
                    }
                } else if (grid[nr][nc] == 't') {
                    if (dist[nr][nc][1] > now[3]+1) {
                        dist[nr][nc][1] = now[3]+1;
                        dist[nr][nc][0] = now[2];
                        pq.offer(new int[]{nr, nc, now[2], now[3]+1});
                    }
                } else {
                    if (dist[nr][nc][0] > now[2] && dist[nr][nc][1] > now[3]) {
                        dist[nr][nc][0] = now[2];
                        dist[nr][nc][1] = now[3];
                        pq.offer(new int[]{nr, nc, now[2], now[3]});
                    }
                }
            }
        }
//        for (int i=0; i<N; i++) {
//            for (int j=0; j<M; j++) {
//                System.out.print(Arrays.toString(dist[i][j])+" ");
//            }
//            System.out.println();
//        }
        System.out.println(result[0]+" "+result[1]);


    }
}

