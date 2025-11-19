import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] grid;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        while (true) {
            if (!move()) break;
            cnt++;
        }
        System.out.println(cnt);

    }
    static boolean move() {
        final int[] dr = {1, 0, -1, 0};
        final int[] dc = {0, 1, 0, -1};
        int[][] id = new int[N][N];
        Map<Integer, int[]> map = new HashMap<>();
        int counter = 1;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                id[i][j] = counter;
                map.put(counter, new int[]{i, j});
                counter++;
            }
        }
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i=0; i<=N*N; i++) adjList.add(new ArrayList<>());
        boolean canGo = false;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                for (int k=0; k<4; k++) {
                    int ni = i + dr[k];
                    int nj = j + dc[k];
                    if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                    int diff = Math.abs(grid[i][j]-grid[ni][nj]);
                    if (diff >= L && diff <= R) {
                        canGo = true;
                        adjList.get(id[i][j]).add(id[ni][nj]);
                    }
                }
            }
        }
        // bfs
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N*N+1];
        for (int i=1; i<=N*N; i++) {
            if (visited[i]) continue;
            List<int[]> list = new ArrayList<>();
            int sum = 0;
            visited[i] = true;
            q.offer(i);
            while (!q.isEmpty()) {
                int now = q.poll();
                int[] vtx = map.get(now);
                int ppl = grid[vtx[0]][vtx[1]];
                sum += ppl;
                list.add(vtx);
                for (int next: adjList.get(now)) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    q.offer(next);
                }
            }
            int res = sum / list.size();
            for (int[] v: list) {
                grid[v[0]][v[1]] = res;
            }
        }

        return canGo;
    }

}
