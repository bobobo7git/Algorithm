import java.io.*;
import java.util.*;

public class Main {
    static final int[][] shape = {
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0}
    };
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        int N = Integer.parseInt(br.readLine());
        char[][] result = star(N);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<result.length; i++) {
            for (int j=0; j<result[0].length; j++) {
                if (result[i][j] != '*') sb.append(' ');
                else sb.append('*');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static char[][] star(int n) {
        if (n <= 0) return new char[][] {{'*'}};
        char[][] prev = star(n-1);

        // 배열 크기: n-1번째 별 배열의 5배
        int size = prev.length;
        char[][] now = new char[size*5][size*5];

        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (shape[i][j] == 1) {
                    for (int r=0; r<size; r++) {
                        for (int c=0; c<size; c++) {
                            now[i*size+r][j*size+c] = prev[r][c];
                        }
                    }
                }
            }
        }

        return now;
    }

}

