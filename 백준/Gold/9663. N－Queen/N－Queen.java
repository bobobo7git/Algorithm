import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        visited = new int[N];
        Arrays.fill(visited, -1);

        System.out.println(dfs(0));
    }
    // 현재 행을 썼으면 다음으로 넘어간다.
    static int dfs(int row) {
        // N개를 배치했다 = 1~N행을 배치했다.
        if (row == N) {
            return 1;
        }
        int ret = 0;
        // 배치할 열을 선택한다.
        for (int col=0; col<N; col++) {
            if (visited[col] != -1) continue;
            if (!cross(row, col)) continue;
            visited[col] = row;
            ret += dfs(row+1);
            visited[col] = -1;
        }
        return ret;
    }
    static boolean cross(int row, int col) {
        /*
        * 대각선 체크
        * 놓으려고 하는 row, col의 위치와 visited 비교
        *
        * */
        for (int c=0; c<N; c++) {
            if (visited[c] == -1) continue;
            int rowDiff = Math.abs(row-visited[c]);
            int colDiff = Math.abs(col - c);
            if (colDiff == rowDiff) return false;
        }

        return true;
    }


}
