import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] grid;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static final Map<Integer, Character> dir = Map.of(
            0, 'S',
            1, 'E',
            2, 'N',
            3, 'W'
    );
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            grid = new char[N+2][M+2];
            int[] start = new int[] {-1, -1, -1, -1};
            for (int i=1; i<=N; i++) {
                String line = br.readLine();
                for (int j=1; j<=M; j++) {
                    grid[i][j] = line.charAt(j-1);
                    if (grid[i][j] == 'P') {
                        if (start[0] == -1) {
                            start[0] = i;
                            start[1] = j;
                        } else {
                            start[2] = i;
                            start[3] = j;
                        }
                        grid[i][j] = '.';
                    }
                }
            }

//            sb.append(bfs(start)).append('\n');
            bw.write(bfs(start));
            bw.newLine();
        }   // end while
//        System.out.print(sb);
        bw.flush();
        bw.close();
    }
    static String bfs(int[] start) {
        Queue<State> q = new ArrayDeque<>();
        int[][][][] dist = new int[N+2][M+2][N+2][M+2];
        q.offer(new State(start));
        dist[start[0]][start[1]][start[2]][start[3]] = 1;

        // r1, c1, r2, c2에 도달하기 전의 좌표 + 방향
        // 50^4 * 5 =
//        int[][][][][] route = new int[N+2][M+2][N+2][M+2][5];
        int er = -1, ec = -1;
        while (!q.isEmpty()) {
            State pop = q.poll();
            int[] now = pop.pos;
            int r1 = now[0];
            int c1 = now[1];
            int r2 = now[2];
            int c2 = now[3];
            int cost = dist[r1][c1][r2][c2];

            if (r1 == r2 && c1 == c2) {
                er = r1; ec = c1;
                return dist[er][ec][er][ec]-1+" "+pop.route;
//                break;
            }

            for (int i=0; i<4; i++) {
                int nr1 = r1 + dr[i];
                int nc1 = c1 + dc[i];
                int nr2 = r2 + dr[i];
                int nc2 = c2 + dc[i];

                // 화면 끝으로 이동할 때 반대편에 유령이 없는 경우만 이동
                if (nr1 == 0) {
                    if (grid[N][nc1] == '.') {
                        nr1 = N;
                    } else {
                        if (grid[N][nc1] == 'G') continue;
                        nr1 = now[0];
                    }
                } else if (nr1 == N+1) {
                    if (grid[1][nc1] == '.') {
                        nr1 = 1;
                    } else {
                        if (grid[1][nc1] == 'G') continue;
                        nr1 = now[0];
                    }
                } else if (grid[nr1][nc1] != '.') {
                    if (grid[nr1][nc1] == 'G') continue;
                    nr1 = now[0];
                }

                if (nc1 == 0) {
                    if (grid[nr1][M] == '.') {
                        nc1 = M;
                    } else {
                        if (grid[nr1][M] == 'G') continue;
                        nc1 = now[1];
                    }
                } else if (nc1 == M+1) {
                    if (grid[nr1][1] == '.') {
                        nc1 = 1;
                    } else {
                        if (grid[nr1][1] == 'G') continue;
                        nc1 = now[1];
                    }
                } else if (grid[nr1][nc1] != '.') {
                    if (grid[nr1][nc1] == 'G') continue;
                    nc1 = now[1];
                }

                if (nr2 == 0) {
                    if (grid[N][nc2] == '.') {
                        nr2 = N;
                    } else {
                        if (grid[N][nc2] == 'G') continue;
                        nr2 = now[2];
                    }
                } else if (nr2 == N+1) {
                    if (grid[1][nc2] == '.') {
                        nr2 = 1;
                    } else {
                        if (grid[1][nc2] == 'G') continue;
                        nr2 = now[2];
                    }
                } else if (grid[nr2][nc2] != '.') {
                    if (grid[nr2][nc2] == 'G') continue;
                    nr2 = now[2];
                }

                if (nc2 == 0) {
                    if (grid[nr2][M] == '.') {
                        nc2 = M;
                    } else {
                        if (grid[nr2][M] == 'G') continue;
                        nc2 = now[3];
                    }
                } else if (nc2 == M+1) {
                    if (grid[nr2][1] == '.') {
                        nc2 = 1;
                    } else {
                        if (grid[nr2][1] == 'G') continue;
                        nc2 = now[3];
                    }
                } else if (grid[nr2][nc2] != '.') {
                    if (grid[nr2][nc2] == 'G') continue;
                    nc2 = now[3];
                }

                if (dist[nr1][nc1][nr2][nc2] != 0) continue;
                dist[nr1][nc1][nr2][nc2] = cost+1;
                q.offer(new State(new int[] {nr1, nc1, nr2, nc2}, pop.route+dir.get(i)));
//                route[nr1][nc1][nr2][nc2] = new int[]{r1, c1, r2, c2, i};
            }

        }
        StringBuilder res = new StringBuilder();
//        if (er != -1) {
//            int cnt = dist[er][ec][er][ec]-1;
//            res.append(cnt).append(' ');
//
//            StringBuilder sb = new StringBuilder();
//            int pr1 = er, pc1 = ec, pr2 = er, pc2 = ec;
//            while (cnt-- > 0) {
//                int[] path = route[pr1][pc1][pr2][pc2];
//                pr1 = path[0];
//                pc1 = path[1];
//                pr2 = path[2];
//                pc2 = path[3];
//                sb.append(dir.get(path[4]));
//            }
//            res.append(sb.reverse());
//        } else return "IMPOSSIBLE";
//        return res.toString();
        return "IMPOSSIBLE";
    }
    static class State {
        int[] pos;
        String route;
        State(int[] pos) {
            this.pos = pos;
            route = "";
        }
        State(int[] pos, String route) {
            this.pos = pos;
            this.route = route;
        }
    }

}