import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] candy;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        input();
        int result = knapsack();
        System.out.println(result);
    }
    static int knapsack() {
        int max = 0;
        // 집합별 사탕 수
        Map<Integer, Group> id2group = new HashMap<>();
        for (int i=1; i<=N; i++) {
            int p = find(i);
            if (id2group.containsKey(p)) id2group.get(p).add(candy[i]);
            else id2group.put(p, new Group(1, candy[i]));
        }
        int[] dp = new int[K];
        for (Group group: id2group.values()) {
            for (int i=K-1; i>=group.n; i--) {
                dp[i] = Math.max(dp[i], dp[i-group.n]+group.v);
                max = Math.max(max, dp[i]);
            }
        }

        return max;
    }
    static void input() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candy = new int[N+1];
        parents = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            parents[i] = i;
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }
    }
    static int find(int v) {
        if (parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x > y) parents[x] = y;
        else parents[y] = x;
    }
    static class Group {
        int n, v;
        Group(int n, int v) {
            this.n = n;
            this.v = v;
        }
        void add(int candy) {
            this.v += candy;
            this.n++;
        }
    }
}
