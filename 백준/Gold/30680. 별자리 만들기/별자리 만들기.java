import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        /*
        * 각 트리의 리프노드를 기억하고 있어야함
        * 트리를 연결할 때마다, 깊이가 최소인 리프노드들을 갱신
        * 우선순위큐로 가지고 있다가 pop -> 그 노드와 다음 트리 연결
        * 다음 트리의 리프노드들을 우선순위큐에 push
        * 트리 생성, dfs -> stack으로 구현
        * 같은 번호가 또 나오면 트리가 엉키니 번호를 계속 새로 추가
        * */
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> adjList = new ArrayList<>();
        int LIMIT = 500000;
        for (int i=0; i<=LIMIT; i++) {
            adjList.add(new ArrayList<>());
        }

        int start = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        pq.offer(new int[]{0, 0});
        boolean[] visited = new boolean[LIMIT+1];
        visited[0] = true;

        for (int i=0; i<N; i++) {
            int A = Integer.parseInt(br.readLine());
            for (int j=0; j<A-1; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                // 붉은색 별(루트)는 항상 1
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                u += start;
                w += start;
                adjList.get(u).add(w);
                adjList.get(w).add(u);
            }
            // 연결되지 않은 리프 중 최소를 pop
            int[] leaf = pq.poll();
            int root = start+1;
            adjList.get(leaf[0]).add(root);
            adjList.get(root).add(leaf[0]);
            // dfs로 리프노드 찾기
            Deque<int[]> stack = new ArrayDeque<>();
            // 깊이 = 거치는 실의 최소 갯수
            stack.push(new int[]{root, 0});
            visited[root] = true;
            while (!stack.isEmpty()) {
                int[] now = stack.pop();
                boolean isLeaf = true;

                for (Integer next: adjList.get(now[0])) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    stack.push(new int[]{next, now[1]+1});
                    isLeaf = false;
                }
                if (isLeaf) {
                    // leaf의 깊이만큼 더해줘야
                    pq.offer(new int[]{now[0], now[1]+leaf[1]+1});
                }
            }
            start += A;
        }

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});
        int max = 0;
        visited = new boolean[LIMIT+1];
        visited[0] = true;
        while (!stack.isEmpty()) {
            int[] now = stack.pop();
            boolean isLeaf = true;
            for (Integer next: adjList.get(now[0])) {
                if (visited[next]) continue;
                visited[next] = true;
                stack.push(new int[]{next, now[1]+1});
                isLeaf = false;
            }
            if (isLeaf) {
                max = Math.max(max, now[1]);
            }
        }

        System.out.println(max);

    }


}
