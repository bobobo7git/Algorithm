import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        // 2 <= N <= 50
        int N = Integer.parseInt(br.readLine());
        int[][] info = new int[N][3];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            info[i][0] = w;
            info[i][1] = h;
        }

        // O(N^2)
        for (int i=0; i<N; i++) {
            int k = 0;
            for (int j=0; j<N; j++) {
                if (i == j) continue;
                // 나보다 큰 사람들의 수
                if (info[i][0] < info[j][0] && info[i][1] < info[j][1]) k++;
            }
            info[i][2] = k+1;
        }
        StringBuilder sb = new StringBuilder();
        for (int[] i: info) {
            sb.append(i[2]).append(' ');
        }
        System.out.println(sb);
    }
}
