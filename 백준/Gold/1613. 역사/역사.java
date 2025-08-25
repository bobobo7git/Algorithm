import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> adjList;
    static int[] inDegree;
    static boolean[][] memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }
        inDegree = new int[N+1];

        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList.get(u).add(v);
            inDegree[v]++;
        }
        topology(N);
        StringBuilder sb = new StringBuilder();
        final char enter = '\n';
        int Q = Integer.parseInt(br.readLine());
        for (int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (memo[u][v] && !memo[v][u]) sb.append(-1);
            else if (!memo[u][v] && memo[v][u]) sb.append(1);
            else sb.append(0);
            sb.append(enter);
        }
        System.out.print(sb);
    }
    static void topology(int N) {
        Set<Integer>[] hs = new HashSet[N+1];
        memo = new boolean[N+1][N+1];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i=1; i<=N; i++) {
            if (inDegree[i] == 0) q.offer(i);
            hs[i] = new HashSet<>();
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int next: adjList.get(x)) {
                hs[next].add(x);
                memo[x][next] = true;
                for (int prev: hs[x]) {
                    hs[next].add(prev);
                    memo[prev][next] = true;
                }

                inDegree[next]--;
                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}

