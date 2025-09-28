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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i] = new Line(a, b);
        }
        Arrays.sort(lines, (o1, o2) -> {
            return o1.a - o2.a;
        });
        int max = 0;
        int[] lis = new int[N];

        for (int i=0; i<N; i++) {
            if (lis[i] == 0) lis[i] = 1;
            for (int j=i-1; j>=0; j--) {
                if (lines[i].b > lines[j].b) {
                    lis[i] = Math.max(lis[i], lis[j]+1);
                    max = Math.max(max, lis[i]);
                }
            }
        }
        System.out.println(N - max);
    }
    static class Line {
        int a, b;
        Line(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}

