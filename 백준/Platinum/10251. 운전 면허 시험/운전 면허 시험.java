import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 행, 열 수는 선의 개수를 의미(칸의 개수가 아님)
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            // (i행,j열)에서 오른쪽으로 이동할 때 연료의 비용
            int[][] rowFuel = new int[M][N];
            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N-1; j++) {
                    rowFuel[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // (i행,j열)에서 아래로 이동할 때 연료의 비용
            int[][] colFuel = new int[M][N];
            for (int i=0; i<M-1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    colFuel[i][j] = Integer.parseInt(st.nextToken());
                }
            }
//            for (int i=0; i<M; i++) System.out.println(Arrays.toString(rowFuel[i]));
//            System.out.println();
//            for (int i=0; i<M; i++) System.out.println(Arrays.toString(colFuel[i]));
//            System.out.println();
            /*
            * 방향 전환을 할 때마다 1초
            * 한칸 이동할 떄마다 L초
            * 방향 전환에 비용이 발생하므로 방향도 상태공간에 표시해야함
            * (i,j)에서 오른쪽을 보고있을 때와 아래쪽을 보고있을 때 미래 비용이 다름
            * 연료도 상태공간에 표시해야함
            * 격자크기는 최대 100*100, k=2, G=100만
            * => 네개를 다 표시하면 메모리, 시간초과
            * 시간은 어차피 끝점에 도달할 수 있다면 방향을 몇번 바꿨는지만 알면 됨
            * => 상태는 (i,j)에서 방향이 k이고 l번 방향을 바꿨을 때 최소 연료
            * 방향을 바꿀 수 있는 최대 횟수는 M+N
            * */
            int[][][][] dp = new int[M][N][2][M+N];
            for (int i=0; i<M; i++) {
                for (int j=0; j<N; j++) {
                    for (int k=0; k<2; k++) {
                        // 절대 도달할 수 없는 G+1로 초기화
                        Arrays.fill(dp[i][j][k], G+1);
                    }
                }
            }
            // 시작점에서는 모든 방향으로 시작 가능
            dp[0][0][0][0] = 0;
            dp[0][0][1][0] = 0;
            // 맨 위 가로 초기화
            for (int i=1; i<N; i++) {
                dp[0][i][0][0] = dp[0][i-1][0][0] + rowFuel[0][i-1];
            }
            // 맨 왼쪽 세로 초기화
            for (int i=1; i<M; i++) {
                dp[i][0][1][0] = dp[i-1][0][1][0] + colFuel[i-1][0];
            }
            for (int i=1; i<M; i++) {
                for (int j=1; j<N; j++) {
                    // 한번 이상 방향을 바꾸어야만 도달 가능
                    for (int l=1; l<M+N; l++) {
                        dp[i][j][0][l] = Math.min(dp[i][j][0][l], dp[i][j-1][0][l]+rowFuel[i][j-1]);
                        dp[i][j][0][l] = Math.min(dp[i][j][0][l], dp[i][j-1][1][l-1]+rowFuel[i][j-1]);
                        dp[i][j][1][l] = Math.min(dp[i][j][1][l], dp[i-1][j][1][l]+colFuel[i-1][j]);
                        dp[i][j][1][l] = Math.min(dp[i][j][1][l], dp[i-1][j][0][l-1]+colFuel[i-1][j]);
                    }
                }
            }
            int ans = 9999999;
            for (int dir=0; dir<2; dir++) {
                for (int cnt=0; cnt<M+N; cnt++) {
                    // 시간 = 방향바꾼 횟수 + L * (M-1 + N-1)
                    if (dp[M-1][N-1][dir][cnt] == G+1) continue;
                    ans = Math.min(ans, cnt + L * (M+N-2));
                }
            }

            sb.append(ans == 9999999 ? -1 : ans).append('\n');

        }
        // end
        System.out.print(sb);
    }
}

