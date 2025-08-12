import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> adjList;
    static int[] comp;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }
        comp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {

            int prev = Integer.parseInt(st.nextToken());
            if (prev > 0) {
                adjList.get(prev).add(i);
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            comp[v] += w;
        }

        dfs(1);
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            sb.append(comp[i]).append(' ');
        }
        System.out.print(sb.toString().trim());

    }
    static void dfs(int v) {
        for (int next: adjList.get(v)) {
            comp[next] += comp[v];
            dfs(next);
        }
    }
}
