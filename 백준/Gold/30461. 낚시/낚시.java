import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                grid[i][j] += Integer.parseInt(st.nextToken());
                if (i < N-1) grid[i+1][j] += grid[i][j];
            }
        }
        for (int i=0; i<N-1; i++) {
            for (int j=0; j<M-1; j++) {
                grid[i+1][j+1] += grid[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
//            int sum = 0;
//            while (a >= 0 && b >=0) {
//                sum += grid[a--][b--];
//            }
//            sb.append(sum).append('\n');
            sb.append(grid[a][b]).append('\n');
        }
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
