import java.io.*;
import java.util.*;

public class Main {
    static int N, M, P;
    static int[] S;
    static char[][] grid;
    static Castle[][] tile;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = new int[P+1];
        for (int i=1; i<=P; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        grid = new char[N][M];
        tile = new Castle[N][M];
        // 1번부터 순서대로 넣기 위해 리스트에 넣고 정렬
        List<Castle> list = new ArrayList<>();
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                grid[i][j] = line.charAt(j);
                char c = line.charAt(j);
                if (c == '#') {
                    tile[i][j] = new Castle(i, j, 0, 0);
                } else if (c != '.') {
                    tile[i][j] = new Castle(i, j, c-'0',0);
                    list.add(tile[i][j]);
                }
            }
        }
        Collections.sort(list, (o1, o2) -> {
            return o1.n - o2.n;
        });

        int[] result = bfs(list);
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=P; i++) {
            sb.append(result[i]).append(' ');
        }
        System.out.print(sb.toString().trim());
    }
    static int[] bfs(List<Castle> list) {
        final int[] dr = {1, 0, -1, 0};
        final int[] dc = {0, 1, 0, -1};
        // 턴의 순서를 보장하는 큐
        Deque<Castle> q = new ArrayDeque<>();
        for (Castle c: list) {
            q.offer(c);
        }
        // Inner 큐로 각 번호의 성마다 bfs 실행
        Queue<Castle>[] inner = new ArrayDeque[P+1];
        for (int i=1; i<=P; i++) {
            inner[i] = new ArrayDeque<>();
        }

        while (!q.isEmpty()) {
            Castle start = q.poll();
            // 끝자락을 보장
            if (tile[start.r][start.c].d < start.d) continue;
//            start.d = 0;
//            tile[start.r][start.c].d = 0;

            Queue<Castle> innerQ = inner[start.n];
            innerQ.offer(start);
            // 각 성마다 bfs
            while (!innerQ.isEmpty()) {
                Castle now = innerQ.poll();
                // 기존에 갱신된 값이 있으면 패스
                if (tile[now.r][now.c].d < now.d) continue;
                // 끝자락에 도달했으면 중지하고 Outer q에 삽입
                if (now.d == S[now.n]+start.d) {
                    q.offer(now);
                    continue;
                }

                for (int i=0; i<4; i++) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];
                    if (oob(nr, nc)) continue;
                    if (tile[nr][nc] == null) {
                        Castle next = new Castle(nr, nc, now.n, now.d+1);
                        innerQ.offer(next);
                        tile[nr][nc] = next;
                    } else {
                        if (tile[nr][nc].n == now.n && tile[nr][nc].d > now.d+1) {
                            Castle next = new Castle(nr, nc, now.n, now.d+1);
                            innerQ.offer(next);
                            tile[nr][nc] = next;
                        }
                    }
                }
            }

        }
        int[] sum = new int[P+1];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (tile[i][j] == null) continue;
                Castle c = tile[i][j];
                sum[c.n]++;
            }
        }
//        for (int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(tile[i]));
//        }
//        System.out.println(Arrays.toString(sum));
        return sum;
    }
    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
    static class Castle {
        int r, c;
        int n;  // 플레이어 번호
        int d;  // 시작점으로부터 거리
        Castle(int r, int c, int n, int d) {
            this.r = r;
            this.c = c;
            this.n = n;
            this.d = d;
        }
        @Override
        public String toString() {
            return n+"";
        }
    }
}

