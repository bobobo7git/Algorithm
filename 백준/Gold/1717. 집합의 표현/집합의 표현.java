import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 집합 초기화
        parents = new int[N+1];
        for (int i=1; i<=N; i++) {
            parents[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        final String yes = "YES";
        final String no = "NO";
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // union
            if (cmd == 0) {
                if (find(a) != find(b)) union(a, b);
            } else {
                if (find(a) == find(b)) sb.append(yes);
                else sb.append(no);
                sb.append('\n');
            }
        }
        System.out.print(sb);

    }
    static int find(int x) {
        if (parents[x] == x) return x;
        // 경로 압축
        return parents[x] = find(parents[x]);
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x > y) parents[x] = y;
        else parents[y] = x;
    }
}
