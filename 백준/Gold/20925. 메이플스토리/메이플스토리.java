import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        Field[] fields = new Field[N];
        // 입장 경험치, 분당 경험치
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            fields[i] = new Field(i, c, e);
        }
        int[][] costs = new int[N][N];
        // 이동 시간
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        /*
        * 시간 순서대로 쌓아가야 dp가 성립한다.
        * dp[i][j]: i번 사냥터에 있을 때 j분을 사용한 최대 경험치
        *
        * */
        dp = new int[N][T+1];
        for (int i=0; i<N; i++) {
            if (fields[i].c == 0) continue;
            for (int j=0; j<=T; j++) {
                dp[i][j] = -1;
            }
        }
        // 시간 순서대로 쌓기
        for (int t=0; t<T; t++) {
            for (int i=0; i<N; i++) {
                // 도달 불가능하면 패스
                if (dp[i][t] == -1) continue;
                // 현재 사냥터에서 사냥
                dp[i][t+1] = Math.max(dp[i][t+1], dp[i][t]+fields[i].e);
                // 이동
                for (int next=0; next<N; next++) {
                    if (i == next) continue;
                    int cost = costs[i][next];
                    int nt = t+cost;
                    if (nt > T) continue;
                    if (dp[i][t] < fields[next].c) continue;
                    dp[next][nt] = Math.max(dp[next][nt], dp[i][t]);
                }
            }
        }
        int max = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<=T; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(max);
    }

    static class Field {
        int id;     // 인덱스
        int c, e;   // 최소치, 분당 경험치
        Field(int id, int c, int e) {
            this.id = id;
            this.c = c;
            this.e = e;
        }
    }
}

