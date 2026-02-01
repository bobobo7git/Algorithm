import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Box[][] grid;
    static final int[] dr = {0, -1, 1, 0, 0}; // _UDLR
    static final int[] dc = {0, 0, 0, -1, 1}; // _UDLR
    static int c1, c2, c3 = 0;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N*N
        M = Integer.parseInt(st.nextToken()); // M번의 마법

        grid = new Box[N][N];
        Box.BoxBuilder builder = Box.BoxBuilder.getInstance();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                builder.create();
            }
        }
        Box shark = grid[N/2][N/2];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                int ball = Integer.parseInt(st.nextToken());
                grid[i][j].ball = ball;
            }
        }
        shark.ball = -1;
        // M번 마법
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // 방향, 거리
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            // 1. 구슬 파괴
            blizzard(d, s);
            ordering();
            // 2. 정렬-폭발 반복
            while (bomb()) {
                ordering();
            }
            // 3. 변화
            transform();
//            for (int z=0; z<N; z++) {
//                System.out.println(Arrays.toString(grid[z]));
//            }
//            System.out.println();
        }
        System.out.println(c1 + c2*2 + c3*3);
    }
    static void blizzard(int d, int s) {
        Box center = grid[N/2][N/2];
        int r = center.r;
        int c = center.c;
        for (int i=0; i<s; i++) {
            r += dr[d];
            c += dc[d];
            grid[r][c].ball = 0;
        }
    }
    static void ordering() {
        Box box = grid[N/2][N/2];
        // 모든 박스를 순회
        while (box.next != null) {
            // 현재 박스가 빈 칸이면 땡겨온다.
            if (box.ball == 0) {
                Box next = box.next;
                while (next != null && next.ball == 0) {
                    next = next.next;
                }
                if (next == null) break;
                box.ball = next.ball;
                next.ball = 0;
            }
            box = box.next;
        }

    }
    static boolean bomb() {
        Box box = grid[N/2][N/2];
        boolean check = false;
        // 모든 박스를 순회
        while (box.next != null) {
            if (box.ball == 0) {
                box = box.next;
                continue;
            }
            if (box.next.ball == box.ball) {
                int std = box.ball;
                int cnt = 1;
                Box next = box.next;
                while (next != null && next.ball == std) {
                    cnt++;
                    next = next.next;
                }
                if (cnt >= 4) {
                    next = box.next;
                    while (next != null && next.ball == box.ball) {
                        next.ball = 0;
                        next = next.next;
                    }
                    box.ball = 0;
                    check = true;
                    if (std == 1) {
                        c1 += cnt;
                    } else if (std == 2) {
                        c2 += cnt;
                    } else if (std == 3) {
                        c3 += cnt;
                    }
                }
            }
            box = box.next;

        }
        return check;
    }
    static void transform() {
        // 이미 정렬되어있으니 0이 나올 떄까지 반복해서 구슬 저장
        Box box = grid[N/2][N/2-1];
        // A, B를 저장할 큐
        Queue<Integer> q = new ArrayDeque<>();
        // 모든 박스별 A, B를 구해서 큐에 저장
        while (box != null && box.ball != 0) {
            // A: 그룹의 구슬 수, B: 구슬 번호
            int A = 1;
            int B = box.ball;
            while (box.next != null && box.next.ball == B) {
                box = box.next;
                A++;
            }
            q.offer(A);
            q.offer(B);
            box = box.next;
        }
        // 덮어쓰기
        box = grid[N/2][N/2-1];
        while (!q.isEmpty() && box != null) {
            box.ball = q.poll();
            box = box.next;
        }
        while (box != null) {
            box.ball = 0;
            box = box.next;
        }
    }
    static class Box {
        int r, c;
        int idx;
        Box next, prev;
        int ball;   // 1,2,3. 0이면 비활성화
        Box(int r, int c, int idx) {
            this.r = r;
            this.c = c;
            this.idx = idx;
        }
        void link(Box next) {
            this.next = next;
            next.prev = this;
        }
        // to be erased
//        @Override
//        public String toString() {
//            return this.ball+"";
//        }
        static BoxBuilder builder;
        private static class BoxBuilder {
            final int[] dr = {0, 1, 0, -1}; // L D R U
            final int[] dc = {-1, 0, 1, 0}; // L D R U
            int dir;
            int r, c;
            int n;
            BoxBuilder() {
                this.dir = 0;
                this.r = N / 2;
                this.c = N / 2;
                this.n = 0;
                grid[r][c] = new Box(r, c, 0);
            }
            Box create() {
                if (n >= N*N-1) return null;
                Box prev = grid[r][c];
                // 전진 - 방향전환하고 한칸 앞에 빈칸이면 전환
                r += this.dr[dir];
                c += this.dc[dir];
                int tempDir = (dir+1) % 4;
                int tr = r + this.dr[tempDir];
                int tc = c + this.dc[tempDir];
                if (grid[tr][tc] == null) {
                    this.dir = tempDir;
                }
                Box box = new Box(r, c, ++n);
                grid[r][c] = box;
                prev.link(box);
                return box;
            }
            static BoxBuilder instance;
            static BoxBuilder getInstance() {
                if (instance == null) {
                    instance = new BoxBuilder();
                }
                return instance;
            }
        }
    }
}


