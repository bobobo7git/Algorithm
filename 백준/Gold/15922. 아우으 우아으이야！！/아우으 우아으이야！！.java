import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int l = -1000000001;
        int r = -1000000001;
        int sum = 0;
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // new line
            if (x > r) {
                sum += (r-l);
                l = x;
                r = y;
            } else if (y > r) {
                r = y;
            }
        }
        sum += (r-l);
        System.out.println(sum);
    }
}
