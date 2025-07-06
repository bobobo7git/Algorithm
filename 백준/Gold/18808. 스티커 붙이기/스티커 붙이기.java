import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[R][C];
            for (int i=0; i<R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<C; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean step1 = false;
            int rotated = 0;
            while (!step1 && rotated < 4) {
                for (int i=0; i<N; i++) {
                    boolean attach = false;
                    for (int j=0; j<M; j++) {
                        boolean attachable = check(grid, i, j, sticker);
                        if (attachable) {
                            attach = true;
                            for (int r=0; r<R; r++) {
                                for (int c=0; c<C; c++) {
                                    if (sticker[r][c] != 0) grid[i+r][j+c] = sticker[r][c];
                                }
                            }
                            break;
                        }
                    }
                    if (attach) {
                        step1 = true;
                        break;
                    }

                }

                int[][] temp = new int[C][R];
                for (int i=0; i<R; i++) {
                    for (int j=0; j<C; j++) {
                        temp[j][i] = sticker[R-1-i][j];
                    }
                }

                int t = R;
                R = C;
                C = t;
                sticker = temp;
                rotated++;
            }

        }
        int sum = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                sum += grid[i][j];
            }
        }
        System.out.println(sum);
    }
    static boolean check(int[][] grid, int si, int sj, int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                if (si+r >= grid.length || sj+c >= grid[0].length) return false;
                if (sticker[r][c] == 1 && grid[si+r][sj+c] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
