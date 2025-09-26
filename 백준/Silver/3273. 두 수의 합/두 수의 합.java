import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        int i = 0, j = N-1;
        int cnt = 0;
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == x) {
                cnt++;
                i++; j--;
            } else if (sum > x) j--;
            else i++;
        }
        System.out.println(cnt);
    }
}

