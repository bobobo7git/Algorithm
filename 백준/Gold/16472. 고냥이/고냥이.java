import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        String string = br.readLine();
        char[] arr = string.toCharArray();

        int l = 0;
        int r = 0;
        int[] cnt = new int[27];
        int max = 0;
        int len = 0;

        while (l < arr.length && r < arr.length) {
            if (cnt[arr[r++]-'a']++ == 0) {
                max++;
            }
            while (l < r && max > N) {
                if (--cnt[arr[l++]-'a'] == 0) max--;
            }
            len = Math.max(len, r-l);

        }
        System.out.println(len);

    }
}
