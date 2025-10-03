import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int two = 0, five = 0;
        for (int i=N; i>0; i--) {
            int t = i;
            while (t % 2 == 0) {
                t /= 2;
                two++;
            }
            t = i;
            while (t % 5 == 0) {
                t /= 5;
                five++;
            }
        }
        System.out.println(Math.min(two, five));
    }
}
