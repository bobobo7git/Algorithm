import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int max = 0;
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        arr = countingSort(arr, max);
        int ans = 0;
        for (int i=0; i<arr.length; i++) {
            int val = arr[i] * (i+1);
            ans = Math.max(val, ans);
        }
        System.out.println(ans);
    }
    static int[] countingSort(int[] arr, int max) {
        int[] cnt = new int[max+1];
        int[] ret = new int[arr.length];

        for (int i=0; i<arr.length; i++) {
            cnt[arr[i]]++;
        }
        for (int i=1; i<=max; i++) {
            cnt[i] += cnt[i-1];
        }
        for (int i=0; i<arr.length; i++) {
            ret[arr.length - cnt[arr[i]]--] = arr[i];
        }
        return ret;
    }

}
