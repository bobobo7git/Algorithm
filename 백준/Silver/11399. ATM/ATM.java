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
        /*
        * 1 2 3 3 4
        * 1
        * 1 2
        * 1 2 3
        * 1 2 3 3
        * 1 2 3 3 4
        * */

        mergeSort(arr, 0, N-1);
        int sum = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<=i; j++) {
                sum += arr[j];
            }
        }
        System.out.println(sum);

    }
    static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = (l+r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid+1, r);
        sort(arr, l, mid, r);
    }
    static void sort(int[] arr, int l, int mid, int r) {
        int i = l;
        int j = mid+1;
        int size = (r-l+1);
        int[] temp = new int[size];
        int p = 0;

        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) temp[p++] = arr[i++];
            else temp[p++] = arr[j++];
        }
        while (i <= mid) temp[p++] = arr[i++];
        while (j <= r) temp[p++] = arr[j++];

        for (int x=0; x<size; x++) {
            arr[l+x] = temp[x];
        }
    }

}
