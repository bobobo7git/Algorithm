import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);

        int T = Integer.parseInt(br.readLine());
        while (T--> 0) {
            init();
            int F = Integer.parseInt(br.readLine());
            for (int i=0; i<F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String s1 = st.nextToken();
                String s2 = st.nextToken();

                Node n1 = Node.getOrCreate(s1);
                Node n2 = Node.getOrCreate(s2);
                Node p1 = find(n1);
                Node p2 = find(n2);
                if (!p1.name.equals(p2.name)) {
                    union(p1, p2);
                }
                bw.write(p1.size+"\n");
            }
        }
        bw.flush();
        bw.close();
    }
    static Node find(Node node) {
        if (node.parent.name.equals(node.name)) return node;
        return node.parent = find(node.parent);
    }
    static int union(Node p1, Node p2) {
        p2.parent = p1;
        p1.size += p2.size;
        return p1.size;
    }
    static void init() {
        Node.map = new HashMap<>();
    }
    static class Node {
        String name;
        int size;
        Node parent;
        Node(String name, int size) {
            this.name = name;
            this.size = size;
            parent = this;
        }
        static Node getByName(String name) {
            return map.get(name);
        }
        static Node getOrCreate(String name) {
            if (!map.containsKey(name)) map.put(name, new Node(name, 1));
            return map.get(name);
        }
        static Map<String, Node> map;
    }

}