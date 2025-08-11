import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int v = Integer.parseInt(br.readLine());
        Node root = new Node(v);
        while (true) {
            try {
                int n = Integer.parseInt(br.readLine());
                Node node = new Node(n);
                root.connect(node);
            } catch (Exception e) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        postorder(root, sb);
        System.out.print(sb.toString());
    }
    static void postorder(Node now, StringBuilder sb) {
        if (now.l != null) postorder(now.l, sb);
        if (now.r != null) postorder(now.r, sb);
        sb.append(now.n).append('\n');
    }
    static class Node {
        int n;
        Node l, r;
        public Node(int n) {
            this.n = n;
        }
        public void connect(Node x) {
            if (n > x.n) connectLeft(x);
            else connectRight(x);
        }
        public void connectLeft(Node l) {
            if (this.l != null) this.l.connect(l);
            else this.l = l;
        }
        public void connectRight(Node r) {
            if (this.r != null) this.r.connect(r);
            else this.r = r;
        }
//        @Override
//        public String toString() {
//            // postorder
//            StringBuilder sb = new StringBuilder();
//            if (this.l != null) sb.append(this.l);
//            if (this.r != null) sb.append(this.r);
//            sb.append(this.n).append('\n');
//            return sb.toString();
//        }
    }
}
