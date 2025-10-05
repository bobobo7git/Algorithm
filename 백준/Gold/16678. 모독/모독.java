import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        long cnt = 0;
        if (arr[0] > 1) {
            cnt += arr[0] - 1;
            arr[0] = 1;
        }
        for (int i=1; i<N; i++) {
            if (arr[i] > arr[i-1]+1) {
                cnt += (arr[i] - arr[i-1]-1);
                arr[i] = arr[i-1]+1;
            }
        }

        System.out.println(cnt);
    }
}

