import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        Map<Integer, String> vis = new HashMap<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        vis.put(s, "");
        
        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == t) break;
            String prev = vis.get(now);
            long mul = (long) now * now;
            if (mul <= 1e9 && !vis.containsKey((int) mul)) {
                q.offer((int) mul);
                vis.put((int) mul, prev+'*');
            }
            long add = (long) now + now;
            if (add <= 1e9 && !vis.containsKey((int) add)) {
                q.offer((int) add);
                vis.put((int) add, prev+'+');
            }
            int sub = 0;
            if (!vis.containsKey(sub)) {
                q.offer(sub);
                vis.put(sub, prev+'-');
            }

            if (now == 0) continue;
            int div = 1;
            if (!vis.containsKey(div)) {
                q.offer(div);
                vis.put(div, prev+'/');
            }
        }   // end bfs
        System.out.println(s == t ? 0 : vis.getOrDefault(t, "-1"));
    }
}
