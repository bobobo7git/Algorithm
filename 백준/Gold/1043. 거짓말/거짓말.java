import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] truth = new boolean[N+1];
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        for (int i=0; i<n; i++) {
            int id = Integer.parseInt(st.nextToken());
            truth[id] = true;
        }

        parents = new int[N+1];
        for (int i=1; i<=N; i++) {
            parents[i] = i;
        }

        int[] presents = new int[M];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int root = Integer.parseInt(st.nextToken());

            presents[i] = root;
            for (int j=0; j<num-1; j++) {
                int id = Integer.parseInt(st.nextToken());
                if (find(root) != find(id)) {
                    union(root, id, truth);
                }
            }
        }
        int cnt = 0;
        for (int i=0; i<M; i++) {
            if (!truth[find(presents[i])]) cnt++;
        }
        
        System.out.println(cnt);
    }
    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    static void union(int x, int y, boolean[] truth) {
        int rx = find(x);
        int ry = find(y);
        // 진실을 아는 사람이 있는 경우 우선
        if (truth[rx] && !truth[ry]) parents[ry] = rx;
        else if (!truth[rx] && truth[ry]) parents[rx] = ry;
        else if (rx > ry) parents[rx] = ry;
        else parents[ry]= rx;
    }
}
