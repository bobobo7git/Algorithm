import java.io.*;
import java.util.*;

/*
*
* n=3)
* 1d: 2/0.5   w
* 2d: 2/0 wh, 1/1.0 ww
* 3d: 1/0.5 whw, 0/1.5 www, 1/0.5 wwh
* 4d: 0/1.0 whww, 1/0.0 whwh, 0/1.0 wwwh, 0/1.0 wwhw, 1/0.0 wwhh
* 5d: 0/0.5 whwwh, 0/0.5 whwhw, 0/0.5 wwwhh, 0/0.5 wwhwh, 0/0.5 wwhhh
* 6d: whwwhh whwhwh wwwhhh wwhwhh wwhhhh
* 오늘 한조각과 반조각의 개수만큼 새로운 가짓수가 생김
* 2n일차에는 모든 약을 먹음
* dp[n][i][j] = dp[n-1][i>0?][j>0?]
* dp[n][0][0] += dp[n-1][1..i][1..j]
* dp[1][n-1][1] = 1
* dp[2][n-2][2] = 1, dp[2][n-1][0] = 1
* => dp[n][i][j] -> dp[n+1][i-1][j+1] += 1, dp[n+1][i][j-1] += 1
* */
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            long[][][] dp = new long[2*n+1][n+1][n+1];
            dp[1][n-1][1] = 1;
            for (int day=1; day<2*n; day++) {
                // 하나짜리 먹기
                for (int w=1; w<=n; w++) {
                    for (int h=0; h<n; h++) {
                        dp[day+1][w-1][h+1] += dp[day][w][h];
                    }
                }
                // 반조각짜리 먹기
                for (int h=1; h<=n; h++) {
                    for (int w=0; w<=n; w++) {
                        dp[day+1][w][h-1] += dp[day][w][h];
                    }
                }
            }
            System.out.println(dp[2*n][0][0]);
        }
    }

}

