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

        parents = new int[N+1];
        List<int[]> edges = new ArrayList<>();

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            edges.add(new int[]{u, v, g});
        }

        int[] exits = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            exits[i] = Integer.parseInt(st.nextToken());
            edges.add(new int[]{0, i, exits[i]});
        }
        /*
        * 방의 개수 N, 워프의 개수 M
        * 탈출하는 시간은 필요없으므로
        * 워프로 연결되어있으면 비상구가 하나만 있으면 됨
        * -> 최소신장트리
        * 분리집합으로 신장트리 여러 개 만들고
        * 각 집합별로 가장 비용이 적은 탈출구 설치비용 선택
        * 0번을 탈출구로 두고 노드로 여김
        * */

        // 크루스칼
        for (int i=1; i<=N; i++) {
            parents[i] = i;
        }
        Collections.sort(edges, (o1, o2) -> {
            return o1[2] - o2[2];
        });

        int sum = 0;
        for (int[] edge: edges) {
            int pu = find(edge[0]);
            int pv = find(edge[1]);
            if (pu == pv) continue;
            union(pu, pv);
            sum += edge[2];
        }
        System.out.println(sum);

    }
    static int find(int v) {
        if (v == parents[v]) return v;
        return parents[v] = find(parents[v]);
    }
    static int union(int x, int y) {
        if (x > y) {
            parents[x] = y;
            return y;
        }
        else {
            parents[y] = x;
            return x;
        }
    }
}

