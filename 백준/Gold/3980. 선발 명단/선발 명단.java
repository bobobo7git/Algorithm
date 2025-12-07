import java.io.*;
import java.util.*;

public class Main {
    static final int N = 11;
    static int[][] s;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int C = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (C--> 0) {
            s = new int[N][N];
            for (int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    s[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int result = dfs(new State(0, 0, new boolean[N]));
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }
    static int dfs(State start) {
        Deque<State> stack = new ArrayDeque<>();
        stack.push(start);
        int ret = 0;
        while (!stack.isEmpty()) {
            State now = stack.pop();
            if (now.depth == N) {
                ret = Math.max(ret, now.sum);
                continue;
            }
            boolean[] vis = now.vis;
            // i번째 사람의 능력치 순회
            for (int j=0; j<N; j++) {
                if (vis[j] || s[now.depth][j] == 0) continue;
                boolean[] nvis = Arrays.copyOf(vis, N);
                nvis[j] = true;
                State next = new State(now.depth+1, now.sum+s[now.depth][j], nvis);
                stack.push(next);
            }
        }
        return ret;
    }
    static class State {
        int depth;
        int sum;
        boolean[] vis;
        State(int depth, int sum, boolean[] vis) {
            this.depth = depth;
            this.sum = sum;
            this.vis = vis;
        }
    }

}
