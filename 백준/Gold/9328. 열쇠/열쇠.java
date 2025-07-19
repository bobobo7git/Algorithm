import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] grid;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            grid = new char[N+2][M+2];
            for (int i=1; i<=N; i++) {
                String line = br.readLine();
                for (int j=1; j<=M; j++) {
                    grid[i][j] = line.charAt(j-1);
                }
            }

            String keys = br.readLine();

            Node start = new Node(0, 0);
            Node.keys = new boolean[27];
            for (char key: keys.toCharArray()) {
                if (key == '0') break;
                Node.getKey(key);
            }
            Node.docs = new boolean[N+2][M+2];

            sb.append(bfs(start)).append('\n');
        }
        System.out.println(sb.toString());
    }
    static int bfs(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N+2][M+2];
        visited[0][0] = true;
        q.offer(start);
        int docs = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i=0; i<4; i++) {
                int nr = now.r + Node.dr[i];
                int nc = now.c + Node.dc[i];
                if (outOfBound(nr, nc) || visited[nr][nc]) continue;

                char next = grid[nr][nc];
                if (next == '*') continue;

                if (next == '$') {
                    if (!Node.docs[nr][nc]) {
                        docs++;
                        Node.docs[nr][nc] = true;
                    }
                } else if (next - 'A' >= 0 && next - 'A' <= 'Z' - 'A') {
                    if (!Node.hasKey(next)) continue;
                } else if (next - 'a' >= 0 && next - 'a' <= 'z'-'a') {
                    if (!Node.keys[next - 'a']) {
                        visited = new boolean[N+2][M+2];
                        Node.getKey(next);
                    }
                }
                visited[nr][nc] = true;
                q.offer(new Node(nr, nc));
            }
        }


        return docs;
    }

    static boolean outOfBound(int r, int c) {
        return r < 0 || c < 0 || r >= N+2 || c >= M+2;
    }
    static class Node {
        int r, c;
        static boolean[] keys = new boolean[27];
        static boolean[][] docs;
        static final int[] dr = {1, 0, -1, 0};
        static final int[] dc = {0, 1, 0, -1};
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public static void getKey(char key) {
            keys[key - 'a'] = true;
        }
        public static boolean hasKey(char door) {
            return keys[door - 'A'];
        }
    }
}
