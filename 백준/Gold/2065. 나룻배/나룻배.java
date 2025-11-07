import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        /*
        * i번째 손님이 언제 도착하는지 알아야 한다.
        * i번째 손님의 정류장 도착시간을 알아야한다.
        * */
        Comparator<Customer> comp = new Comparator<>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.t - o2.t;
            }
        };
        Queue<Customer> left = new PriorityQueue<>(comp);
        Queue<Customer> right = new PriorityQueue<>(comp);
        int LEFT = 0;
        int RIGHT = 1;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String v = st.nextToken();

            if (v.equals("left")) left.offer(new Customer(i, t, RIGHT));
            else right.offer(new Customer(i, t, LEFT));
        }

        int time = 0;
        int now = LEFT;
        Queue<Customer> boat = new ArrayDeque<>();
        int[] arrive = new int[N];
        StringBuilder sb =new StringBuilder();
        while (!left.isEmpty() || !right.isEmpty() || !boat.isEmpty()) {
            // 먼저 다 내려줌
            while (!boat.isEmpty()) {
                Customer done = boat.poll();
//                System.out.println(now+" "+done+" "+time);
                arrive[done.id] = time;
//                sb.append(time).append('\n');
            }
            // 현재 정박장에서 태울 수 있는 사람을 전부 태운다.
            Queue<Customer> cur = now == LEFT ? left : right;
            while (!cur.isEmpty() && cur.peek().t <= time && boat.size() < M) {
                boat.offer(cur.poll());
            }
            // 만약 태울 수 있는 사람이 없다면?
            // 현재 시간 time에 도착한 손님이 없다.
            if (boat.isEmpty()) {
                Queue<Customer> opp = now == LEFT ? right : left;
                // 반대편에 바로 태울 수 있는지 확인
                if (!opp.isEmpty() && opp.peek().t <= time) {
                    now ^= 1;
                    time += T;
                }
                // 둘다 바로 못 태움

                else {
                    // 둘다 비어있으면 종료
                    int next = Integer.MAX_VALUE;
                    if (!cur.isEmpty())  next = Math.min(next, cur.peek().t);
                    if (!opp.isEmpty())  next = Math.min(next, opp.peek().t);
                    if (next == Integer.MAX_VALUE) break; // 진짜 아무도 없으면 종료
                    time = Math.max(time, next);
                }

            }
            // 태울 수 있는 사람이 있으면 반대로 이동
            else {
                now ^= 1;
                time += T;
            }
        }
        for (int t: arrive) {
            sb.append(t).append('\n');
        }
        System.out.print(sb);

    }
    static class Customer {
        int id;
        int t;
        int dir;
        Customer(int id, int t, int dir) {
            this.id = id;
            this.t = t;
            this.dir = dir;
        }
        @Override
        public String toString() {
            return String.format("(%d, %d, %d)",id,t,dir);
        }
    }
}