import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Employee[] emps = new Employee[N];
        int m = 0;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            emps[i] = new Employee(i, d, h, m);
            m = (m+1) % M;
        }
        // 0~M-1까지 선두에 넣는다.
        Queue<Employee> pq = new PriorityQueue<>();
        int idx = 0;
        for (; idx<Math.min(N, M); idx++) {
            pq.offer(emps[idx]);
        }
        int turn = 0;
        while (!pq.isEmpty()) {
            Employee use = pq.poll();
            if (use.id == K) break;
            turn++;
            int nextId = use.id + M;
            if (nextId < N) {
                pq.offer(emps[nextId]);
            }
        }
        System.out.println(turn);

    }
    static class Employee implements Comparable<Employee> {
        int id;
        int d, h;
        int col;
        Employee(int id, int d, int h, int col) {
            this.id = id;
            this.d = d;
            this.h = h;
            this.col = col;
        }

        @Override
        public int compareTo(Employee o) {
            if (this.d != o.d) return o.d - this.d;
            if (this.h != o.h) return o.h - this.h;
            return this.col - o.col;
        }
    }

}
