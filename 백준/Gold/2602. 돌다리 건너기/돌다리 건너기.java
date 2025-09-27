import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        char[] route = br.readLine().toCharArray();
        char[] dev = br.readLine().toCharArray();
        char[] ang = br.readLine().toCharArray();
        // bridge ij = i번째 다리에서 j번째 문자열까지 도달한 경로 합
        int[][] devil = new int[dev.length][route.length];
        int[][] angel = new int[ang.length][route.length];

        // 시작점을 1로 표시
        int N = dev.length;
        for (int i=0; i<N; i++) {
            if (dev[i] == route[0]) devil[i][0]++;
            if (ang[i] == route[0]) angel[i][0]++;
        }

        // route를 반복
        int M = route.length;
        // i번째 문자열
        for (int i=1; i<M; i++) {
            // 다리 순회
            for (int j=1; j<N; j++) {
                // 현재 위치 앞의 다리에서
                if (dev[j] == route[i]) {
                    for (int k=0; k<j; k++) {
                        // 현재 앞의 다리에 적힌 문자열이 i-1번째 문자열이면
                        if (ang[k] == route[i-1]) devil[j][i] += angel[k][i-1];
                    }
                }
                if (ang[j] == route[i]) {
                    for (int k=0; k<j; k++) {
                        // 현재 앞의 다리에 적힌 문자열이 i-1번째 문자열이면
                        if (dev[k] == route[i-1]) angel[j][i] += devil[k][i-1];
                    }
                }

            }
        }

        int cnt = 0;
        for (int i=1; i<N; i++) {
            cnt += devil[i][M-1];
            cnt += angel[i][M-1];
        }
        System.out.println(cnt);



    }
}
