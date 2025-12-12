import java.io.*;
import java.util.*;
/*
* [초기화]
* 물 덩어리 분리집합을 구한다.
* 동시에 물과 인접한 빙산을 큐에 저장한다.
* [반복]
* 백조1과 백조2가 같은 집합에 속할 때까지 반복한다.
* 시간을 1 늘린다.
* 저장된 빙산을 녹이면서 4방향을 탐색한다.
* 방향을 순회하면서 다른 물 덩어리를 만나면 union한다.
* 빙산을 만나면 큐에 저장한다.
* [종료]
* 시간을 출력한다.
* */
public class Main {
    static final char ICE = 'X', WATER = '.', DUCK = 'L';
    static int N, M;
    static char[][] grid;
    static int sr, sc, er, ec;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static int[] parents;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        sr = sc = er = ec = -1;
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == DUCK) {
                    grid[i][j] = WATER;
                    if (sr == -1 && sc == -1) {
                        sr = i; sc = j;
                    } else {
                        er = i; ec = j;
                    }
                }
            }
        }

        // 1. 처음 물 덩어리 집합을 구한다.
        int[][] visited = new int[N][M];
        Queue<int[]> melt = new ArrayDeque<>();
        int id = 1;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (grid[i][j] == WATER && visited[i][j] == 0) {
                    bfs(i, j, visited, melt, id);
                    id++;
                }

            }
        }
//        for (int x=0; x<N; x++) {
//            System.out.println(Arrays.toString(visited[x]));
//        }
//        System.out.println();

        // id만큼 분리집합을 생성
        parents = new int[id];
        for (int i=0; i<id; i++) {
            parents[i] = i;
        }
        // 시뮬레이션
        int time = 0;
        while (find(visited[sr][sc]) != find(visited[er][ec])) {
            time++;
            int cnt = melt.size();
            while (cnt--> 0) {
                int[] ice = melt.poll();
                int idx = ice[2];
                visited[ice[0]][ice[1]] = find(idx);
                for (int d=0; d<4; d++) {
                    int nr = ice[0] + dr[d];
                    int nc = ice[1] + dc[d];
                    if (oob(nr, nc) || visited[nr][nc] == -1) continue;
                    int px = find(idx);
                    int py = find(visited[nr][nc]);
                    // 다른 물을 만나는 경우
                    if (visited[nr][nc] != 0 && px != py) {
                        union(px, py);
                    }
                    // 빙산
                    else if (visited[nr][nc] == 0) {
                        melt.offer(new int[]{nr, nc, px});
                        visited[nr][nc] = -1;
                    }
                }
            }
//            System.out.println(time);
//            for (int x=0; x<N; x++) {
//                System.out.println(Arrays.toString(visited[x]));
//            }
//            System.out.println();
        }   // end simulation

//        for (int x=0; x<N; x++) {
//            System.out.println(Arrays.toString(visited[x]));
//        }
//        System.out.println();
        System.out.println(time);
    }
    static void bfs(int i, int j, int[][] visited, Queue<int[]> melt, int id) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        visited[i][j] = id;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int d=0; d<4; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                if (oob(nr, nc) || visited[nr][nc] != 0) continue;
                if (grid[nr][nc] == WATER) {
                    visited[nr][nc] = id;
                    q.offer(new int[]{nr, nc});
                } else if (grid[nr][nc] == ICE) {
                    melt.offer(new int[]{nr, nc, id});

                }
            }
        }

    }
    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    /*
    * py를 px로 변경
    * */
    static void union(int px, int py) {
        parents[py] = px;
    }

}
