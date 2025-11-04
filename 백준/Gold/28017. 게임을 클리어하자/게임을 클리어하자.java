import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] table;
    static int[][] memo;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // i번째 회차에 j번 무기를 사용하는 경우 클리어 시간
        table = new int[N+1][M+1];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=M; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        /*
        * 바로 이전 회차의 무기만 사용하지 않음
        * i번째 회차에서 i-1번째 회차에서 선택한 무기 제외
        * */
        memo = new int[N+1][M+1];
        for (int i=1; i<=N; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        int min = Integer.MAX_VALUE;
        for (int w=1; w<=M; w++) {
            min = Math.min(min, topDown(N, w));
        }
        System.out.println(min);


    }
    static int topDown(int i, int j) {
        if (memo[i][j] != Integer.MAX_VALUE) return memo[i][j];
        int res = Integer.MAX_VALUE;
        for (int weapon=1; weapon<=M; weapon++) {
            if (weapon == j) continue;
            res = Math.min(res, topDown(i-1, weapon));
        }
        return memo[i][j] = res + table[i][j];
    }
}
