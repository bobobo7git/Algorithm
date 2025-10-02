import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] start = {a, b, c};
        Arrays.sort(start);
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(start);

        Set<String> visited = new HashSet<>();
        visited.add(Arrays.toString(start));
        boolean able = false;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            // base
            if (now[0] == now[1]  && now[0] == now[2]) {
                able = true;
                break;
            }
            // a b
            if (now[0] != now[1]) {
                int na = now[0]*2;
                int nb = now[1]-now[0];
                int[] next = {na, nb, now[2]};
                Arrays.sort(next);
                String v = Arrays.toString(next);
                if (!visited.contains(v)) {
                    visited.add(v);
                    q.offer(next);
                }
            }
            // b c
            if (now[1] != now[2]) {
                int nb = now[1]*2;
                int nc = now[2]-now[1];
                int[] next = {now[0], nb, nc};
                Arrays.sort(next);
                String v = Arrays.toString(next);
                if (!visited.contains(v)) {
                    visited.add(v);
                    q.offer(next);
                }
            }
            // a c
            if (now[0] != now[2]) {
                int na = now[0]*2;
                int nc = now[2]-now[0];
                int[] next = {na, now[1], nc};
                Arrays.sort(next);
                String v = Arrays.toString(next);
                if (!visited.contains(v)) {
                    visited.add(v);
                    q.offer(next);
                }
            }


        }
        System.out.println(able ? 1 : 0);

    }
}

