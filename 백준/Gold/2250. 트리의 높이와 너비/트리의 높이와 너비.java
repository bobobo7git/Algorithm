import java.io.*;
import java.util.*;

public class Main {
    static Node[] nodes;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());

        nodes = new Node[N+1];
        for (int i=0; i<=N; i++) {
            nodes[i] = new Node(i);
        }

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int leftChild = Integer.parseInt(st.nextToken());
            int rightChild = Integer.parseInt(st.nextToken());

            nodes[v].connectLeft(leftChild);
            nodes[v].connectRight(rightChild);
        }
        Node root = nodes[0];
        for (int i=1; i<=N; i++) {
            if (nodes[i].isRoot) {
                root = nodes[i];
                break;
            }
        }

        dfs(root, 0, 1);

        int[][] max = new int[N+1][2];
        for (int i=1; i<=N; i++) {
            max[i][0] = N;
        }
        for (int i=1; i<=N; i++) {
            max[nodes[i].level][0] = Math.min(max[nodes[i].level][0], nodes[i].col);
            max[nodes[i].level][1] = Math.max(max[nodes[i].level][1], nodes[i].col);
        }
        int width = 0;
        int level = 0;
        for (int i=1; i<=N; i++) {
            if (width < max[i][1] - max[i][0] +1) {
                level = i;
                width = max[i][1] - max[i][0] +1;
            }
        }
        System.out.println(level+" "+width);

    }

    static int dfs(Node now, int col, int depth) {
        if (now == null) return col;

        now.level = depth;
        now.col = dfs(now.left, col, depth+1)+1;
        col = dfs(now.right, now.col, depth+1);


        return col;
    }
    static class Node {
        int v;
        int col, level;
        boolean isRoot;
        Node left, right;
        Node(int v) {
            this.v = v;
            this.isRoot = true;
        }
        void connectLeft(int left) {
            if (left == -1) return;
            this.left = nodes[left];
            nodes[left].setRoot(false);
        }
        void connectRight(int right) {
            if (right == -1) return;
            this.right = nodes[right];
            nodes[right].setRoot(false);
        }
        void setRoot(boolean isRoot) {
            this.isRoot = isRoot;
        }

    }

}
