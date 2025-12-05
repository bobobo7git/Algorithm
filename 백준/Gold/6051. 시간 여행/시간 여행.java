import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);

        int N = Integer.parseInt(br.readLine());
        Node[] snapshots = new Node[N+1];
        Node now = null;
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch (cmd) {
                case "a": {
                    // add
                    int n = Integer.parseInt(st.nextToken());
                    Node next = new Node(n);
                    next.prev = now;
                    now = next;
                    bw.write(now.val+"");
                    break;
                }
                case "s": {
                    // delete
                    if (now != null) now = now.prev;
                    if (now != null) bw.write(now.val+"");
                    else bw.write("-1");
                    break;
                }
                case "t": {
                    // time reverse
                    int n = Integer.parseInt(st.nextToken());
                    now = snapshots[n-1];
                    if (now != null) bw.write(now.val+"");
                    else bw.write("-1");
                    break;
                }
            }
            bw.newLine();
            snapshots[i] = now;
        }
        bw.flush();
        bw.close();
    }
    static class Node {
        int val;
        Node prev;
        Node(int val) {
            this.val = val;
        }
    }
}
