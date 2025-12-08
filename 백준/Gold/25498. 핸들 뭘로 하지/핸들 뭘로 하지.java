import java.io.*;
import java.util.*;

public class Main {
    static List<List<Node>> adjList;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        char[] c = new char[N+1];
        String line = br.readLine();
        adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        for (int i=1; i<=N; i++) {
            c[i] = line.charAt(i-1);
            adjList.add(new ArrayList<>());
        }
        for (int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList.get(u).add(new Node(v, c[v]));
            adjList.get(v).add(new Node(u, c[u]));
        }
        System.out.println(search(c));

    }
    static String search(char[] c) {
        StringBuilder sb = new StringBuilder();

        /*
        * bfs
        * 현재 깊이의 모든 정점 중 사전순으로 늦게 오는 애들만 삽입
        * */
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] vis = new boolean[c.length];
        char[] route = new char[c.length];
        Arrays.fill(route, '.');
        q.offer(new int[]{1, 1});
        vis[1] = true;

        while (!q.isEmpty()) {
            int depth = q.peek()[1];
            List<Node> list = new ArrayList<>();
            while (!q.isEmpty() && depth == q.peek()[1]) {
                int[] now = q.poll();
                list.add(new Node(now[0], c[now[0]]));
            }

            if (list.isEmpty()) continue;
            list.sort((o1, o2) -> {
                return Character.compare(o2.c, o1.c);
            });
            char std = list.get(0).c;
            route[depth] = std;
            for (Node node: list) {
                if (node.c != std) continue;
                for (Node next: adjList.get(node.v)) {
                    if (vis[next.v]) continue;
                    vis[next.v] = true;
                    q.offer(new int[]{next.v, depth+1});
                }
            }

        }
        for (int i=1; i<route.length; i++) {
            if (route[i] == '.') break;
            sb.append(route[i]);
        }
        return sb.toString();
    }
    static class Node {
        int v;
        char c;
        Node(int v, char c) {
            this.v = v;
            this.c = c;
        }
    }

}
