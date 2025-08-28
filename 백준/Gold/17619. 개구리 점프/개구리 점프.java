import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        Log[] logs = new Log[N+1];
        logs[0] = new Log(0, -1, -1, -1);
        parents = new int[N+1];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            logs[i] = new Log(i, x1, x2, y);
            parents[i] = i;
        }

        sweeping(logs);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if (find(a) == find(b)) sb.append(1);
            else sb.append(0);
            sb.append('\n');
        }
        
        System.out.print(sb);

    }
    static void sweeping(Log[] logs) {
        Arrays.sort(logs, new Comparator<Log>(){
            @Override
            public int compare(Log o1, Log o2) {
                if (o1.x1 != o2.x1) return o1.x1 - o2.x1;
                return o2.x2 - o1.x2;
            }
        });

        int x2 = 0;
        int root = 0;

        for (int i=1; i<logs.length; i++) {
            if (x2 < logs[i].x1) {
                x2 = logs[i].x2;
                root = logs[i].id;
            } else if (x2 < logs[i].x2) {
                x2 = logs[i].x2;
                union(root, logs[i].id);
            } else if (x2 >= logs[i].x1) {
                union(root, logs[i].id);
            }
        }

    }
    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (rx == ry) return;
        else if (rx > ry) parents[rx] = ry;
        else parents[ry] = rx;
    }
    static class Log {
        int id;
        int x1, x2;
        int y;
        public Log(int id, int x1, int x2, int y) {
            this.id = id;
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
        }
    }
}
