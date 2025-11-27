
import java.io.*;
import java.util.*;

/*
 * 배열 밖으로 나가지 않는 사이클을 찾는다.
 * - dfs
 * 분리집합으로 사이클 집합을 구한다.
 * - union find
 * 집합의 원소 크기가 큰 것부터 K개를 바꾸면 최대를 얻는다.
 * - greedy
 * */
public class Main {
    // LRUD
    static final Map<Character, Integer> map = Map.of(
            'L', 0,
            'R', 1,
            'U', 2,
            'D', 3
    );
    static final int[] dr = {0, 0, -1, 1};
    static final int[] dc = {-1, 1, 0, 0};
    static int N, M;
    static Plate[][] grid;
    static int[] parents;
    static int[] size;
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        grid = new Plate[N][M];
        parents = new int[N*M];
        size = new int[N*M];
        visited = new int[N][M];
        int id = 0;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                char c = st.nextToken().charAt(0);
                grid[i][j] = new Plate(id, map.get(c), -1);
                size[id] = 1;
                parents[id] = id++;
            }
        }

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                int d = Integer.parseInt(st.nextToken());
                Plate plate = grid[i][j];
                plate.dist = d;
            }
        }
        Set<Integer> cycles = new HashSet<>();
        Set<Integer> escapes = new HashSet<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                Plate start = grid[i][j];
                if (visited[i][j] != 0) continue;
                Deque<Integer> stack = new ArrayDeque<>();
                Result result = dfs(i, j, stack);

                while (!stack.isEmpty()) {
                    int node = stack.pop();
                    union(start.id, node);
                }
                if (result.type == CYCLE) {
                    cycles.add(find(start.id));
                } else if (result.type == ESCAPE) {
                    escapes.add(find(start.id));
                }
            }
        }

//        System.out.println(cycles);
//        System.out.println(escapes);
        // 초기값은 탈출 가능 집합의 모든 크기의 합
        int min = 0;
        for (int es: escapes) {
            min += size[es];
        }
        // 최댓값은 가능한 사이클을 큰것부터 탈출로 바꾸면 된다.
        int max = min;
        int cnt = K;
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        for (Integer c: cycles) {
            pq.offer(size[c]);
        }
        while (!pq.isEmpty() && cnt > 0) {
            max += pq.poll();
            cnt--;
        }
        // 최솟값은 가능한 탈출집합을 큰것부터 사이클에 연결하면 된다.
        pq.clear();
        for (Integer es: escapes) {
            pq.offer(size[es]);
        }
        cnt = K;
        while (!pq.isEmpty() && cnt > 0) {
            min -= pq.poll();
            cnt--;
        }

        System.out.println(min+" "+max);



    }
    /*
    * dfs 탐색을 하면서 이미 방문한 곳으로 돌아오면 사이클, 밖으로 나가면 탈출
    * */
    static int CYCLE = 1;
    static int ESCAPE = 2;
    static Result dfs(int r, int c, Deque<Integer> stack) {
        Plate now = grid[r][c];
        visited[r][c] = CYCLE;
        stack.push(now.id);
        int nr = r + dr[now.dir] * now.dist;
        int nc = c + dc[now.dir] * now.dist;

        if (oob(nr, nc)) {
            visited[r][c] = ESCAPE;
            return new Result(now.id, ESCAPE);
        }
        if (visited[nr][nc] == CYCLE) {
            visited[r][c] = CYCLE;
            stack.push(grid[nr][nc].id);
            return new Result(grid[nr][nc].id, CYCLE);
        } else if (visited[nr][nc] == ESCAPE) {
            visited[r][c] = ESCAPE;
            stack.push(grid[nr][nc].id);
            return new Result(grid[nr][nc].id, ESCAPE);
        }

        Result result = dfs(nr, nc, stack);
        visited[r][c] = result.type;
        return result;
    }

    static boolean oob(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
    static int find(int v) {
        if (parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;
        if (x > y) {
            parents[x] = y;
            size[y] += size[x];
        }
        else {
            parents[y] = x;
            size[x] += size[y];
        }
    }
    static class Plate {
        int id; // 분리집합 구분용
        int dir;
        int dist;
        Plate(int id, int dir, int dist) {
            this.id = id;
            this.dir = dir;
            this.dist = dist;
        }
    }
    static class Result {
        int id;
        int type;
        Result(int type) {
            this.type = type;
        }
        Result(int id, int type) {
            this.id = id;
            this.type = type;
        }
    }
}
