import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<int[]>> adjList;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            adjList.get(u).add(new int[]{v, g});
            adjList.get(v).add(new int[]{u, g});
        }

        System.out.println(binarySearch(s, e));
    }
    static int binarySearch(int s, int e) {
        int l = 1;
        int r = 1000001;

        while (l <= r) {
            int mid = (l+r) / 2;
            boolean canGo = dijkstra(s, e, mid);

            if (canGo) {
                l = mid+1;
            } else r = mid-1;
        }

        return r;

    }
    static boolean dijkstra(int s, int e, int minDistance) {
        /*
        * 지금까지 지나온 다리 무게가 큰 것부터 탐색한다.
        * 만약 다음 정점을 이미 방문했더라도 무게가 더 크면 삽입
        * 나중에 뺀 위치에서 현재무게가 더 작으면 패스
        * */
        // 정점번호, 다리의 최소 하중
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o2[1] - o1[1];
        });
        int[] weights = new int[N+1];
        Arrays.fill(weights, -1);
        pq.offer(new int[]{s, 1000001});
        weights[s] = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            int v = now[0];
            int min = now[1];

            if (v == e) return true;
            if (weights[v] > min) continue;

            for (int[] next: adjList.get(v)) {
                if (next[1] < minDistance) continue;
                int nextMin = Math.min(min, next[1]);
                if (weights[next[0]] >= nextMin) continue;
                pq.offer(new int[] {next[0], nextMin});
                weights[next[0]] = nextMin;
            }

        }
        return false;
    }

}
