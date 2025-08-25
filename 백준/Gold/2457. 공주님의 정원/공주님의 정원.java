import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        Queue<Date> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.s != o2.s) return o1.s - o2.s;
            return o2.e - o1.e;
        });

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());

            int start = sm*100 + sd;
            int end = em*100 + ed;
            Date date = new Date(start, end);
            pq.offer(date);
        }
        int leftBound = 301;
        int rightBound = 1130;
        
        int r = 0;
        int cnt = 0;

        while (!pq.isEmpty() && pq.peek().s <= leftBound) {
            Date now = pq.poll();
            r = Math.max(r, now.e);
        }
        cnt++;
        
        while (!pq.isEmpty() && r <= rightBound && pq.peek().s <= r) {
            Date now = pq.poll();
            
            // 현재 종료점보다 시작점이 빠른 구간 꽃 중에서 가장 종료점이 먼 것 선택
            int temp = now.e;
            while (!pq.isEmpty() && pq.peek().s <= r) {
                temp = Math.max(temp, pq.poll().e);
            }

            if (temp == r) continue;
            cnt++;
            r = temp;
            
        }
        
        boolean fail = r <= rightBound;
        cnt = fail ? 0 : cnt;
        System.out.println(cnt);
    }
    static class Date {
        int s, e;
        public Date(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}
