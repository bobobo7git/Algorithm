import java.io.*;
import java.util.*;

public class Main {
    static final Map<Character, Integer> c2d = Map.of('U', 0, 'D', 1, 'R', 2, 'L', 3);
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    static final int SCALE = 800; // 여유 있는 크기 확보
    static int[][] grid = new int[SCALE + 2][SCALE + 2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        x *= 2;
        y *= 2;

        int N = Integer.parseInt(br.readLine());
        grid[y][x] = 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);
            int g = Integer.parseInt(st.nextToken());

            int dir = c2d.get(cmd);
            for (int k = 0; k < g * 2; k++) {
                x += dx[dir];
                y += dy[dir];
                grid[y][x] = 1;
            }
        }

        // 외부 영역을 미리 채워서 제외
        bfs(0, 0, 1);

        int idx = 2;
        int minSize = Integer.MAX_VALUE;
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    int[] res = bfs(j, i, idx++);
                    int sx = j, sy = i, ex = res[0], ey = res[1], sum = res[2];
                    int width = ex - sx + 1;
                    int height = ey - sy + 1;

                    if (sum == width * height) {
                        if (sum < minSize) {
                            minSize = sum;
                            x1 = sx / 2;
                            y1 = sy / 2;
                            x2 = (ex + 1) / 2;
                            y2 = (ey + 1) / 2;
                        }
                    }
                }
            }
        }

        if (minSize == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }

        System.out.println(x1 + " " + y1);
        System.out.println(x2 + " " + y2);
    }

    static int[] bfs(int x, int y, int idx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        grid[y][x] = idx;

        int maxX = x, maxY = y;
        int sum = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            sum++;

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];
                if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid.length) continue;
                if (grid[ny][nx] != 0) continue;
                grid[ny][nx] = idx;
                q.offer(new int[]{nx, ny});
                maxX = Math.max(maxX, nx);
                maxY = Math.max(maxY, ny);
            }
        }

        return new int[]{maxX, maxY, sum};
    }
}
