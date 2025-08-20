import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        Queue<Station> q = new PriorityQueue<>(new Comparator<Station>() {
            @Override
            public int compare(Station o1, Station o2) {
                return o1.dist - o2.dist;
            }
        });
        Queue<Station> pq = new PriorityQueue<>(new Comparator<Station>() {
            @Override
            public int compare(Station o1, Station o2) {
                return o2.fuel - o1.fuel;
            }
        });
        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());
            q.offer(new Station(dist, fuel));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int now = P;
        int cnt = 0;
        while (now < L) {
            while (!q.isEmpty()) {
                if (q.peek().dist <= now) {
                    pq.offer(q.poll());
                } else break;
            }
            if (pq.isEmpty()) {
                cnt = -1;
                break;
            } else {
                now += pq.poll().fuel;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    static class Station {
        int dist;
        int fuel;
        public Station(int dist, int fuel) {
            this.dist = dist;
            this.fuel = fuel;
        }
    }
}
