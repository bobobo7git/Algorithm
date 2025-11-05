
import java.io.*;
import java.util.*;

public class Main {
    static int[] T, B;
    static int maxT = 0, maxB = 0;
    static int[][][] memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> q = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n != 0) q.offer(n);
            else maxT++;
        }

        T = new int[q.size()];
        int idx = 0;
        while (!q.isEmpty()) {
            T[idx++] = q.poll();
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n != 0) q.offer(n);
            else maxB++;
        }
        B = new int[q.size()];
        idx = 0;
        while (!q.isEmpty()) {
            B[idx++] = q.poll();
        }
        /*
        * 위쪽에 i까지, 아래쪽에 j까지 왔고 n열인 경우
        * 위쪽에 사용한 0의 개수 = n-i
        * */
        memo = new int[N][T.length][B.length];
        for (int n=0; n<N; n++) {
            for (int i=0; i<T.length; i++) {
                for (int j=0; j<B.length; j++) {
                    memo[n][i][j] = Integer.MIN_VALUE;
                }
            }
        }
        System.out.println(dfs(0, 0, 0));

    }
    static int dfs(int n, int i, int j) {
        if (i == T.length || j == B.length) return 0;
        if (memo[n][i][j] != Integer.MIN_VALUE) return memo[n][i][j];

        int ret = Integer.MIN_VALUE;
        ret = Math.max(ret, dfs(n+1, i+1, j+1)+T[i]*B[j]);
        if (n-i < maxT) ret = Math.max(ret, dfs(n+1, i, j+1));
        if (n-j < maxB) ret = Math.max(ret, dfs(n+1, i+1, j));
        return memo[n][i][j] = ret;

    }


}

