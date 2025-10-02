import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        List<Job>[] jobs = new ArrayList[10001];
        for (int i=0; i<=10000; i++) {
            jobs[i] = new ArrayList<>();
        }
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            jobs[d].add(new Job(d, p));
        }
        Queue<Job> pq = new PriorityQueue<>((o1, o2) -> {
            return o2.p - o1.p;
        });
        int sum = 0;
        for (int day=10000; day>0; day--) {
            for (Job job: jobs[day]) {
                pq.offer(job);
            }
            if (!pq.isEmpty()) sum += pq.poll().p;

        }
        System.out.println(sum);
    }
    static class Job {
        int d, p;
        Job(int d, int p) {
            this.d = d;
            this.p = p;
        }
    }
}

