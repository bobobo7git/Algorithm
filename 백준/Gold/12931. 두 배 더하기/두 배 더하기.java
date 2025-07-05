
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(arr, 0));
        int ans = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            boolean clear = true;
            int oddIdx = -1;
            int[] next = new int[N];
            for (int i=0; i<N; i++) {
                next[i] = now.arr[i];
                if (now.arr[i] != 0) {
                    clear = false;
                }
                if (now.arr[i] % 2 != 0) {
                    oddIdx = i;
                }
            }
            if (clear) {
                ans = now.t;
                break;
            }

            if (oddIdx != -1) {
                next[oddIdx]--;
                q.offer(new Node(next, now.t+1));
            } else {
                for (int i=0; i<N; i++) {
                    next[i] /= 2;
                }
                q.offer(new Node(next, now.t+1));
            }
        }
        System.out.println(ans);


    }
    static class Node {
        int[] arr;
        int t;
        public Node(int[] arr, int t) {
            this.arr = arr;
            this.t = t;
        }
    }
}
