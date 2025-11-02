import java.io.*;
import java.util.*;

public class Main {
    static final Map<Character, Integer> c2d = Map.of('U', 0, 'D', 1, 'R', 2, 'L', 3);
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    static final int SCALE = 400;
    static int[][] grid = new int[SCALE+2][SCALE+2];
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        x*=2; y*=2;
        int N = Integer.parseInt(br.readLine());
        grid[y][x] = 1;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);
            int g = Integer.parseInt(st.nextToken());

            int dir = c2d.get(cmd);
            for (int k=0; k<g*2; k++) {
                x += dx[dir];
                y += dy[dir];
                grid[y][x] = 1;
            }

        }

//        for (int i=0; i<grid.length; i++) {
//            for (int j=0; j<grid.length; j++) {
//                if (grid[i][j] != 0) System.out.print(grid[i][j]);
//                else System.out.print('_');
//            }
//            System.out.println();
//        }
        bfs(0, 0, 1);

        int idx = 2;
        int minSize = 99999999;
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid.length; j++) {
                if (grid[i][j] == 0) {

                    int[] res = bfs(j, i, idx++);
                    if (isRectangle(j, i, res[0], res[1], res[2])) {

//                        System.out.printf("%d %d %d %d\n",j/2,i/2,(res[0]+1)/2,(res[1]+1)/2);
                        if (minSize > res[2]) {
                            minSize = res[2];
                            x1 = j/2;
                            x2 = (res[0]+1)/2;
                            y1 = i/2;
                            y2 = (res[1]+1)/2;
                        }
                    }

                }
            }
        }
        if (minSize == 99999999) {
            System.out.println(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(x1).append(' ').append(y1).append('\n').append(x2).append(' ').append(y2);
        System.out.println(sb);


    }
    static int[] bfs(int x, int y, int idx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, idx});
        grid[y][x] = idx;
        int maxX = x;
        int maxY = y;
        int sum = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            sum++;
            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid.length) continue;
                if (grid[ny][nx] != 0) continue;
                grid[ny][nx] = idx;
                q.offer(new int[]{nx, ny, idx});
                maxX = Math.max(maxX, nx);
                maxY = Math.max(maxY, ny);
            }
        }

        return new int[]{maxX, maxY, sum};
    }
    static boolean isRectangle(int sx, int sy, int ex, int ey, int sum) {
        int w = ex - sx + 1;
        int h = ey - sy + 1;
        return sum == w*h;

    }
//    static boolean isRectangle(int sx, int sy, int maxX, int maxY) {
//        int idx = grid[sy][sx];
//        for (int i=sy; i<=maxY; i++) {
//            for (int j=sx; j<=maxX; j++) {
//                if (grid[i][j] != idx) return false;
//            }
//        }
//        return true;
//    }

}
