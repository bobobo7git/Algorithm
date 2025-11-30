import java.io.*;
import java.util.*;
/*
* 모든 빈칸에서 같은 명령어로 10회 이내 탈출이 가능해야 한다.
* 4^10 = 1,048,576
* 빈칸의 최대 개수 = 9*9 = 81
* 이동가능한 모든 경로 탐색
* 명령어 이내에서 수행이 가능하면 종료한다.
* 가능한 경로를 Map에 저장한다.
* 모든 빈칸에 대해서 탐색을 종료한 뒤 가능한 경로의 수가 빈칸 수와 같으면 그 경로 출력
* */
public class Main {
    static int N, M;
    static char[][] grid;
    static Map<String, Integer> result;
    static int er, ec;
    // LRUD
    static final Map<Character, Integer> dirs = Map.of(
            'L', 0,
            'R', 1,
            'U', 2,
            'D', 3
    );
    static final int[] dr = {0, 0, -1, 1};
    static final int[] dc = {-1, 1, 0, 0};
    static int cnt;
    static boolean isOver;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);

        int T = Integer.parseInt(br.readLine());
        while (T--> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            grid = new char[N][M];
            cnt = 0;
            for (int i=0; i<N; i++) {
                String line = br.readLine();
                for (int j=0; j<M; j++) {
                    grid[i][j] = line.charAt(j);
                    if (grid[i][j] == 'O') {
                        er = i;
                        ec = j;
                    }
                    else if (grid[i][j] == '.') cnt++;
                }
            }

            Deque<Character> stack = new ArrayDeque<>();
            outer: for (int limit=1; limit<=10; limit++) {
                result = new HashMap<>();
                isOver = false;
                for (int i=0; i<N; i++)  {
                    for (int j=0; j<M; j++) {
                        if (isOver) break outer;
                        if (grid[i][j] == '.') {
                            for (int d=0; d<4; d++) {
                                char c = "LRUD".charAt(d);
                                stack.push(c);
                                dfs(i, j, 0, stack, limit);
                                stack.pop();
                            }
                        }
                    }
                }
            }

            String ret = "XHAE";
            for (String s: result.keySet()) {
                if (result.get(s) == cnt) {
                    ret = s;
                    break;
                }
            }
            bw.write(ret);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static void dfs(int r, int c, int depth, Deque<Character> route, int limit) {
        if (depth == limit || isOver) {
            return;
        }
        // 이동
        int dir = dirs.get(route.peek());
        while (!oob(r+dr[dir], c+dc[dir])) {
            r += dr[dir];
            c += dc[dir];
        }


        if (r == er && c == ec) {
            StringBuilder sb = new StringBuilder();
            for (char x: route) {
                sb.append(x);
            }
            String res = sb.reverse().toString();
            result.put(res, result.getOrDefault(res, 0)+1);
            if (result.get(res) == cnt) {
                isOver = true;
            }
//            return;
        }

        for (int i=0; i<4; i++) {
            char next = "LRUD".charAt(i);
            if (next == route.peek()) continue;
            route.push(next);
            dfs(r, c, depth+1, route, limit);
            route.pop();
        }

    }
    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M || grid[r][c] == '#';
    }

}
