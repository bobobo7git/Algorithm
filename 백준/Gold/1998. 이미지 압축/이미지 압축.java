import java.util.*;
import java.io.*;
public class Main {
    static int[][] image;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] temp = new int[N][M];

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                temp[i][j] = line.charAt(j) - '0';
            }
        }

        int size = 1;
        while (size < N || size < M) {
            size <<= 1;
        }
        image = new int[size][size];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                image[i][j] = temp[i][j];
            }
        }

        Node root = new Node(size);
//        divide(0, 0, size, root);
        div(0, 0, size, root);
        System.out.println(root.getSize()+" "+dfs(root, new HashSet<>()));
    }
    static Node div(int i, int j, int size, Node node) {
        // base condition
        if (size <= 0) return null;

        int color = image[i][j];
        boolean compressed = true;
        outer: for (int r=i; r<i+size; r++) {
            for (int c=j; c<j+size; c++) {
                if (image[r][c] != color) {
                    compressed = false;
                    break outer;
                }
            }
        }
        if (compressed) {
            node.setColor(color);
            return node;
        }

        // divide into 4 cuts
        int cutSize = size/2;
        Node lu = new Node(cutSize);
        Node ru = new Node(cutSize);
        Node ld = new Node(cutSize);
        Node rd = new Node(cutSize);

        node.lu = div(i, j, cutSize, lu);
        node.ru = div(i, j+cutSize, cutSize, ru);
        node.ld = div(i+cutSize, j, cutSize, ld);
        node.rd = div(i+cutSize, j+cutSize, cutSize, rd);
        // conquer
        String code = "" + node.lu + node.ru + node.ld + node.rd;
        if (Node.map.containsKey(code)) return Node.map.get(code);
        
        Node.map.put(code, node);
        return node;
    }
    static int dfs(Node now, Set<Node> vis) {
        if (now == null) return 0;
        if (vis.contains(now)) return 0;
        vis.add(now);
        int cnt = 1;
        cnt += dfs(now.lu, vis);
        cnt += dfs(now.ru, vis);
        cnt += dfs(now.ld, vis);
        cnt += dfs(now.rd, vis);
        return cnt;
    }

    static class Node {
        int color;  // 0: 흰색, 1: 검은색
        int size;
        Node lu, ru, ld, rd;    // 왼쪽위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래
        static int cnt = 0;
        static Map<String, Node> map = new HashMap<>();
        Node() {

        }
        Node(int size) {
            this.size = size;
            this.color = -1;
        }
        void setColor(int color) {
            this.color = color;
            cnt++;
        }

        int getSize() {
            int sum = 1;
            if (lu != null) sum += lu.getSize();
            if (ru != null) sum += ru.getSize();
            if (ld != null) sum += ld.getSize();
            if (rd != null) sum += rd.getSize();
            return sum;
        }
        @Override
        public String toString() {
            if (this.color != -1) return this.color+"";
            return "["+Objects.hashCode(this)+"]";
        }


    }
}
