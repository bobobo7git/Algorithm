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
        int d = Integer.parseInt(st.nextToken());
        System.out.println(bfs(a, b, c, d));
    }
    static int bfs(int aCap, int bCap, int aTar, int bTar) {
        State start = new State(0, 0);
        Map<State, Integer> visited = new HashMap<>();
        Queue<State> q = new ArrayDeque<>();

        q.offer(start);
        visited.put(start, 0);

        while (!q.isEmpty()) {
            State now = q.poll();
            int a = now.a;
            int b = now.b;
            if (now.a == aTar && now.b == bTar) return visited.get(now);
            int nd = visited.get(now)+1;
            // fill
            if (a < aCap) {
                State fa = new State(aCap, b);
                if (!visited.containsKey(fa) || visited.get(fa) > nd) {
                    visited.put(fa, nd);
                    q.offer(fa);
                }
            }
            if (b < bCap) {
                State fb = new State(a, bCap);
                if (!visited.containsKey(fb) || visited.get(fb) > nd) {
                    visited.put(fb, nd);
                    q.offer(fb);
                }
            }
            // empty
            if (a > 0) {
                State ea = new State(0, b);
                if (!visited.containsKey(ea) || visited.get(ea) > nd) {
                    visited.put(ea, nd);
                    q.offer(ea);
                }
            }
            if (b > 0) {
                State eb = new State(a, 0);
                if (!visited.containsKey(eb) || visited.get(eb) > nd) {
                    visited.put(eb, nd);
                    q.offer(eb);
                }
            }
            // move
            if (a > 0 && b < bCap) {
                if (a+b <= bCap) {
                    State ma = new State(0, a+b);
                    if (!visited.containsKey(ma) || visited.get(ma) > nd) {
                        visited.put(ma, nd);
                        q.offer(ma);
                    }
                } else {
                    int na = a - (bCap - b);
                    State ma2 = new State(na, bCap);
                    if (!visited.containsKey(ma2) || visited.get(ma2) > nd) {
                        visited.put(ma2, nd);
                        q.offer(ma2);
                    }
                }
            }
            if (b > 0 && a < aCap) {
                if (a+b <= aCap) {
                    State mb = new State(a+b, 0);
                    if (!visited.containsKey(mb) || visited.get(mb) > nd) {
                        visited.put(mb, nd);
                        q.offer(mb);
                    }
                } else {
                    int nb = b - (aCap - a);
                    State mb2 = new State(aCap, nb);
                    if (!visited.containsKey(mb2) || visited.get(mb2) > nd) {
                        visited.put(mb2, nd);
                        q.offer(mb2);
                    }
                }
            }
        }
        return -1;
    }
//    static int bfs(int aCap, int bCap, int aTar, int bTar) {
//        Queue<int[]> q = new ArrayDeque<>();
//        q.offer(new int[]{0, 0});
//        int[][] dist = new int[aCap+1][bCap+1];
//
//        while (!q.isEmpty()) {
//            int[] now = q.poll();
//            int a = now[0];
//            int b = now[1];
//
//            // terminate
//            if (a == aTar && b == bTar) return dist[a][b];
//            // fill
//            if (a < aCap && (dist[aCap][b] == 0 || dist[aCap][b] > dist[a][b]+1)) {
//                enq(q, aCap, b);
//                dist[aCap][b] = dist[a][b]+1;
//            }
//            if (b < bCap && (dist[a][bCap] == 0 || dist[a][bCap] > dist[a][b]+1)) {
//                enq(q, a, bCap);
//                dist[a][bCap] = dist[a][b]+1;
//            }
//            // empty
//            if (a > 0 && (dist[0][b] == 0 || dist[0][b] > dist[a][b]+1)) {
//                enq(q, 0, b);
//                dist[0][b] = dist[a][b]+1;
//            }
//            if (b > 0 && (dist[a][0] == 0 || dist[a][0] > dist[a][b]+1)) {
//                enq(q, a, 0);
//                dist[a][0] = dist[a][b]+1;
//            }
//            // move
//            if (a > 0 && b < bCap) {
//                if (a+b <= bCap && (dist[0][a+b] == 0 || dist[0][a+b] > dist[a][b]+1)) {
//                    enq(q, 0, a+b);
//                    dist[0][a+b] = dist[a][b]+1;
//                } else {
//                    int na = a - (bCap - b);
//                    if (na >= 0 && (dist[na][bCap] == 0 || dist[na][bCap] > dist[a][b]+1)) {
//                        enq(q, na, bCap);
//                        dist[na][bCap] = dist[a][b]+1;
//                    }
//                }
//            }
//            if (b > 0 && a < aCap) {
//                if (a+b <= aCap && (dist[a+b][0] == 0 || dist[a+b][0] > dist[a][b]+1)) {
//                    enq(q, a+b, 0);
//                    dist[a+b][0] = dist[a][b]+1;
//                } else {
//                    int nb = b - (aCap - a);
//                    if (nb >= 0 && (dist[aCap][nb] == 0 || dist[aCap][nb] > dist[a][b]+1)) {
//                        enq(q, aCap, nb);
//                        dist[aCap][nb] = dist[a][b]+1;
//                    }
//                }
//            }
//        }
//        return -1;
//
//    }
    static void enq(Queue<int[]> q, int a, int b) {
        q.offer(new int[]{a, b});
    }
    static class State {
        int a, b;
        State(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public boolean equals(Object o) {
            State s = (State) o;
            return this.a == s.a && this.b == s.b;
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

}
