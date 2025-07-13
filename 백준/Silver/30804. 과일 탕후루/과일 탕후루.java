import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        int[] dat = new int[10];
        int cnt = 0;

        int e = 0;
        int max = 0;
        for (int s=0; s<N; s++) {
            while (e<N && cnt <= 2) {
                dat[arr[e]]++;
                if (dat[arr[e++]] == 1) cnt++;
                if (cnt <= 2) max = Math.max(max, e-s);
            }
            dat[arr[s]]--;
            if (dat[arr[s]] == 0) cnt--;
        }
        System.out.println(max);


    }
}
