import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] paper;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] res = recur(0, 0, N);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<3; i++) {
            sb.append(res[i]).append('\n');
        }
        System.out.println(sb.toString());
    }
    static int[] recur(int r, int c, int size) {
        int[] result = new int[3];
        int num = paper[r][c];

        for (int i=r; i<r+size; i++) {
            for (int j=c; j<c+size; j++) {

                if (paper[i][j] != num) {
                    int ns = size / 3;
                    int[] sub = new int[3];
                    for (int k=0; k<3; k++) {
                        for (int l=0; l<3; l++) {
                            sub = recur(r+k*ns, c+l*ns, ns);
                            for (int m=0; m<3; m++) {
                                result[m] += sub[m];
                            }
                        }
                    }
                    return result;
                }
            }
        }
        if (num == -1) result[0]++;
        if (num == 0) result[1]++;
        if (num == 1) result[2]++;

        return result;
    }

}

