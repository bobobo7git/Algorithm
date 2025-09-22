import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] pref = new int[N];
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        
        int angry = 0;
        int eggplant = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            pref[i] = Integer.parseInt(st.nextToken());
            pq.offer(pref[i]);
            angry += pref[i];
            
            while (angry >= M && !pq.isEmpty()) {
                eggplant++;
                angry -= pq.poll()*2;
            }
        }
        System.out.println(eggplant);
    }
}

