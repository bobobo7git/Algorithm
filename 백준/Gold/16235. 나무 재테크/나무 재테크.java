
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 매년 추가되는 양분
        int[][] bonus = new int[N+2][N+2];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                bonus[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 심은 나무 목록
        // 나이가 어린 순으로 양분
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[2] - o2[2];
        });
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{r, c, age});
        }
        // 처음에는 5만큼 양분이 있음
        int[][] grid = new int[N+2][N+2];
        for (int i=0; i<=N+1; i++) {
            Arrays.fill(grid[i], 5);
        }
        /*
        * K년 동안 사계절 반복
        * 봄에는 양분 먹고 자람
        * 여름에는 죽은 나무가 양분으로 변함
        * 가을에는 나무 번식
        * 겨울에는 보너스 양분 추가
        * */
        // 죽는 애들을 담아두기
        Queue<int[]> dead = new ArrayDeque<>();
        Queue<int[]> ready = new ArrayDeque<>();
        final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        while (K-- > 0) {
            // 봄
            while (!pq.isEmpty()) {
                int[] tree = pq.poll();
                // 나이만큼 양분 먹기
                if (grid[tree[0]][tree[1]] >= tree[2]) {
                    grid[tree[0]][tree[1]] -= tree[2];
                    tree[2]++;
                    ready.offer(tree);
                } else {
                    dead.offer(tree);
                }
            }
            // 여름
            while (!dead.isEmpty()) {
                int[] tree = dead.poll();
                grid[tree[0]][tree[1]] += tree[2] / 2;
            }
            // 가을
            while (!ready.isEmpty()) {
                int[] tree = ready.poll();
                if (tree[2] % 5 == 0) {
                    for (int i=0; i<8; i++) {
                        int nr = tree[0] + dr[i];
                        int nc = tree[1] + dc[i];
                        if (nr <= 0 || nc <= 0 || nr > N || nc > N) continue;
                        int[] child = {nr, nc, 1};
                        pq.offer(child);
                    }
                }
                pq.offer(tree);
            }
            // 겨울
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    grid[i][j] += bonus[i][j];
                }
            }
        }
        System.out.println(pq.size());

    }
}
