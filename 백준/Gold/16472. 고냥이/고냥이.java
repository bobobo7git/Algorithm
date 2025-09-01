import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        String string = br.readLine();

        int result = binarySearch(string.toCharArray(), N);
        System.out.println(result);
    }
    static int binarySearch(char[] arr, int n) {
        int l = 0;
        int r = arr.length;

        while (l <= r) {
            int mid = (l+r) / 2;
            int result = check(arr, mid);
            if (result <= n) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return r;

    }
    static int check(char[] arr, int size) {
        int l = 0;
        int r = size;
        int t = 0;

        int[] cnt = new int[27];
        for (int i=0; i<r; i++) {
            cnt[arr[i] - 'a']++;
            if (cnt[arr[i]-'a'] == 1) {
                t++;
            }
        }
        int min = t;

        for (int i=r; i<arr.length; i++) {
            if (cnt[arr[i]-'a']++ == 0) t++;
            if (--cnt[arr[l++]-'a'] == 0) t = Math.max(t-1, 0);
            min = Math.min(min, t);
        }

        return min;
    }
}

