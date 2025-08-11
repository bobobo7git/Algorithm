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

        System.out.println(root);
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
        @Override
        public String toString() {
            // postorder
            StringBuilder sb = new StringBuilder();
            if (this.l != null) sb.append(this.l);
            if (this.r != null) sb.append(this.r);
            sb.append(this.n).append('\n');
            return sb.toString();
        }
    }
}
