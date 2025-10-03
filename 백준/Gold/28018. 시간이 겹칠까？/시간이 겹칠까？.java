import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[] diff = new int[1000002];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            diff[s] += 1;
            diff[e+1] -= 1;
        }
        for (int i=1; i<=1000000; i++) {
            diff[i] += diff[i-1];
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (Q-- > 0) {
            int n = Integer.parseInt(st.nextToken());
            sb.append(diff[n]).append('\n');
        }
        System.out.print(sb);
    }
}
