import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        int N = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lectures[i] = new Lecture(s, e);
        }

        Arrays.sort(lectures, (o1, o2) -> {
            if (o1.s != o2.s) return o1.s - o2.s;
            return o1.e - o2.e;
        });
        int cnt = 0;
        Queue<Integer> pq = new PriorityQueue<>();
        for (Lecture l: lectures) {
            // 지금까지 강의 중 가장 빨리 끝나는 걸 골라서 연결
            if (!pq.isEmpty() && pq.peek() <= l.s) {
                pq.poll();
            }
            // 안되면 새 강의실 배정
            else cnt++;
            
            pq.offer(l.e);
        }
        System.out.println(cnt);
    }
    static class Lecture {
        int s, e;
        Lecture(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}
