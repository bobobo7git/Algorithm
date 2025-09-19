import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        Line[] lines = new Line[N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines[i] = new Line(s, e);
        }
        Arrays.sort(lines, new Comparator<Line>(){
            @Override
            public int compare(Line o1, Line o2) {
                if (o1.s != o2.s) return o1.s - o2.s;
                return o2.e - o1.e;
            }
        });

        int max = 0;
        // 현재 겹쳐진 선분의 끝점들이 들어간 최소힙
        Queue<Integer> pq = new PriorityQueue<>();
        for (Line l: lines) {
            // 지금 보는 선분의 시작점보다 겹치고있는 선분의 끝점이 클때까지 다 빼버리기
            while (!pq.isEmpty() && pq.peek() <= l.s) pq.poll();
            // 현재 선분을 겹치기
            pq.offer(l.e);
            max = Math.max(max, pq.size());
        }
        System.out.println(max);
    }
    static class Line {
        int s, e;
        Line(int s, int e) {this.s = s; this.e = e;}
    }
}
