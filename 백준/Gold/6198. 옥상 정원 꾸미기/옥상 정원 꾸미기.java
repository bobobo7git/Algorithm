import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[] buildings = new int[N];
        for (int i=0; i<N; i++) {
            buildings[i] = Integer.parseInt(br.readLine());
        }
        
        Deque<Integer> monoStack = new ArrayDeque<>();
        long ans = 0;
        
        for (int h: buildings) {
            while (!monoStack.isEmpty() && monoStack.peek() <= h) {
                monoStack.pop();
            }
            ans += monoStack.size();
            monoStack.push(h);
        }
        
        System.out.println(ans);
    }
}

