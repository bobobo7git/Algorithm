import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int cnt = 0;
    static int[][] grid;
    static int[][] dp;
    static List<Integer> targets;
    static boolean[] visited;
    static Map<Integer, int[]> id2pos;
    static int[][] pos2id;
    static final int INF = 1_000_001;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        dp = new int[6][6];
        targets = new ArrayList<>();
        id2pos = new HashMap<>();
        pos2id = new int[N][N];
        int num = 1;

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                int x = Integer.parseInt(st.nextToken());
                grid[i][j] = x;
                if (x == 0) {
                    targets.add(num);
                    id2pos.put(num++, new int[]{i, j});
                } else if (x == -1) {
                    id2pos.put(0, new int[]{i, j});
                }
            }
        }
        visited = new boolean[Math.max(1, num)];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        System.out.println(perm(stack, 0));


    }
    static int perm(Deque<Integer> stack, int depth) {
        int ret = INF;
        if (depth == targets.size()) {
            ret = 0;
            stack.push(0);


            int[] arr = new int[stack.size()];
            for (int i=arr.length-1; i>=0; i--) {
                arr[i] = stack.pop();
                stack.offer(arr[i]);
            }
            for (int i=1; i<arr.length; i++) {
                ret += dijkstra(arr[i-1], arr[i]);
            }


            stack.pop();
            return ret;
        }
        for (int t: targets) {
            if (visited[t]) continue;
            visited[t] = true;
            stack.push(t);
            ret = Math.min(ret, perm(stack,depth+1));
            stack.pop();
            visited[t] = false;
        }
        return ret;
    }
    static int dijkstra(int s, int e) {
        if (dp[s][e] > 0) return dp[s][e];
        // 5! = 120가지
        // N*N = 1,000,000
        int[] start = {id2pos.get(s)[0], id2pos.get(s)[1], 0};
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[2] - o2[2];
        });
        pq.offer(start);
        int[][] dist = new int[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                dist[i][j] = INF;
            }
        }
        dist[start[0]][start[1]] = 0;
        int[] end = id2pos.get(e);
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int r = now[0];
            int c = now[1];
            int g = now[2];
            // terminate
            if (r == end[0] && c == end[1]) {
                break;
            }
            if (g > dist[r][c]) continue;
            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (oob(nr, nc)) continue;
                int ng = g + Math.max(grid[nr][nc], 0);
                // 톨게이트, 탈영병
                if (dist[nr][nc] > ng) {
                    dist[nr][nc] = ng;
                    pq.offer(new int[]{nr, nc, ng});
                }


            }
        }
        dp[e][s] = dist[end[0]][end[1]];
        return dp[s][e] = dist[end[0]][end[1]];
    }
    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}
