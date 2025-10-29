
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        char[] course = br.readLine().toCharArray();
        /*
        * 투포인터
        * B 이하 W 이상
        * B보다 크면? l++
        * B 이하이면? r++
        * W보다 작으면? r++
        * W 이상이면? r++
        * 아니면 l++
        * */
        int l = 0;
        int r = 0;
        int b = 0;
        int w = 0;
        int max = 0;
        while (r < N) {
            if (b > B) {
                if (course[l] == 'B') b--;
                else w--;
                l++;
            } else {
                if (course[r] == 'B') b++;
                else w++;
                r++;
            }
            if (b <= B && w >= W) max = Math.max(max, r-l);
        }
        System.out.println(max);
    }
}
