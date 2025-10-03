import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        Skill[] skills = new Skill[M];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            skills[i] = new Skill(l, r, c);
        }
        Arrays.sort(skills, (o1, o2) -> {
            return o1.c - o2.c;
        });
        System.out.println(dijkstra(N, arr, skills));

    }
    static int dijkstra(int N, int[] arr, Skill[] skills) {
        // 방문 처리 -> toString
        // dist -> Map
        Map<String, Integer> dist = new HashMap<>();
        int[] temp = copy(arr);
        Arrays.sort(temp);
        String target = Arrays.toString(temp);
        dist.put(Arrays.toString(arr), 0);
        Queue<State> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.g - o2.g;
        });
        pq.offer(new State(copy(arr), 0));

        while (!pq.isEmpty()) {
            State now = pq.poll();
            // terminate
            if (now.string.equals(target)) return dist.get(now.string);

            if (dist.containsKey(now.string) && now.g > dist.get(now.string)) continue;

            for (Skill sk: skills) {
                int[] prev = copy(now.arr);
                swap(prev, sk.l, sk.r);
                State next = new State(prev, now.g);
                next.g += sk.c;

                if (!dist.containsKey(next.string)) {
                    dist.put(next.string, next.g);
                    pq.offer(next);
                }
                else {
                    if (dist.get(next.string) > next.g) {
                        dist.put(next.string, next.g);
                        pq.offer(next);
                    }
                }

            }
        }
        return -1;
    }
    static int[] copy(int[] arr) {
        int[] ret = new int[arr.length];
        for (int i=0; i<arr.length; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }
    static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    static class State {
        int[] arr;
        int g;
        String string;
        State(int[] arr, int g) {
            this.arr = arr;
            this.g = g;
            this.string = Arrays.toString(arr);
        }
    }
    static class Skill {
        int l, r;
        int c;
        Skill(int l, int r, int c) {
            this.l = l-1;
            this.r = r-1;
            this.c = c;
        }
    }
}
