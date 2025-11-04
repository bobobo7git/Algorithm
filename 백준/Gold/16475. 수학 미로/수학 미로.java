import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<List<int[]>> adjList;
    static boolean[] traps;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }

        // 각 지역의 번호는 1~N이고 그중 K개는 함정 지역
        // K개 함정의 번호
        traps = new boolean[N+1];
        st = new StringTokenizer(br.readLine()); {
            for (int i=0; i<K; i++) {
                int t = Integer.parseInt(st.nextToken());
                traps[t] = true;
            }
        }
        // 일반 길
        for (int i=0; i<M-L; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            // 단방향
            adjList.get(u).add(new int[]{v, g, 0});
        }
        // 함정 길
        for (int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            // 반전 길 표시
            adjList.get(u).add(new int[]{v, g, 0});
            adjList.get(v).add(new int[]{u, g, 1});
        }
        // 출발, 도착
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(s, e, P));
    }
    static int dijkstra(int s, int e, int P) {
        // 정점번호, 가중치합, 스위치 누른 횟수, 현재 상태
        int[] start = new int[]{s, 0, 0, 0};
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
           return o1[1] - o2[1];
        });
        int[][][] dist = new int[N+1][P+1][2];
        for (int i=0; i<=N; i++) {
            for (int j=0; j<=P; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        // 출발지역은 함정이 아니다.
        dist[s][0][0] = 0;
        pq.offer(start);

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            if (now[0] == e) return now[1];

            if (now[1] > dist[now[0]][now[2]][now[3]]) continue;
            int p = now[2];

            for (int[] next: adjList.get(now[0])) {
                int to = next[0];
                int g = next[1];
                // 함정 길이 아니면 스위치와 관계없음
                if (next[2] == 0) {
                    // 다음이 함정 지역이라면 반드시 스위치를 누른다.
                    int np = traps[to] ? (p+1) % P : p;
                    int state = traps[to] && np == 0 ? now[3]^1 : now[3];
                    if (dist[to][np][state] > dist[now[0]][p][now[3]] + g) {
                        dist[to][np][state] = dist[now[0]][p][now[3]] + g;
                        pq.offer(new int[]{to, dist[to][np][state], np, state});
                    }
                } else {
                    // 함정 길이면 방향 확인
                    if (now[3] == 1) {
                        // 다음 지역이 함정 지역일 수 있다.
                        int np = traps[to] ? (p+1) % P : p;
                        int state = traps[to] && np == 0 ? now[3]^1 : now[3];
                        if (dist[to][np][state] > dist[now[0]][p][now[3]] + g) {
                            dist[to][np][state] = dist[now[0]][p][now[3]] + g;
                            pq.offer(new int[]{to, dist[to][np][state], np, state});
                        }
                    }
                }
            }
        }

        return -1;
    }
}

