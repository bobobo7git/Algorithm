import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] grid;
    static int ans;
    static int or, oc;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                char c = line.charAt(j);
                grid[i][j] = c;
                if (c == 'O') {
                    or = i;
                    oc = j;
                }
            }
        }
        
        ans = 11;
        backtrack(0);
        ans = ans == 11 ? -1 : ans;
        System.out.println(ans);
    }
    static void backtrack(int depth) {
        if (depth >= ans) return;

        int rr = -1, rc = -1, br = -1, bc = -1;
        char[][] copy = new char[N][M];
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                copy[i][j] = grid[i][j];
                if (grid[i][j] == 'R') {
                    rr = i;
                    rc = j;
                } else if (grid[i][j] == 'B') {
                    br = i;
                    bc = j;
                }
            }
        }
        if (rr == -1 && rc == -1 && br != -1 && bc != -1) {
            ans = Math.min(ans, depth);
            return;
        }
        for (int d=0; d<4; d++) {
            gravity(rr, rc, br, bc, d);
            backtrack(depth+1);
            restoreGrid(copy);
        }
        
    }
    static void gravity(int rr, int rc, int br, int bc, int dir) {
        // change grid
        int[] tr = {rr, rc};
        int[] tb = {br, bc};
        while (true) {
            boolean redMoved = false;
            boolean blueMoved = false;
            if (rr != -1 && rc != -1 && grid[rr][rc] != 'O') {
                int rnr = rr + dr[dir];
                int rnc = rc + dc[dir];
                if (grid[rnr][rnc] == '.' || grid[rnr][rnc] == 'O') {
                    grid[rr][rc] = '.';
                    rr = rnr;
                    rc = rnc;
                    if (grid[rr][rc] != 'O') grid[rr][rc] = 'R';
                    redMoved = true;
                }
            }
            if (br != -1 && bc != -1 && grid[br][bc] != 'O') {
                int bnr = br + dr[dir];
                int bnc = bc + dc[dir];
                if (grid[bnr][bnc] == '.' || grid[bnr][bnc] == 'O') {
                    grid[br][bc] = '.';
                    br = bnr;
                    bc = bnc;
                    if (grid[br][bc] != 'O') grid[br][bc] = 'B';
                    blueMoved = true;
                }
            }

            if (!redMoved && !blueMoved) break;
        }
//        grid[tr[0]][tr[1]] = '.';
//        grid[tb[0]][tb[1]] = '.';
//        grid[rr][rc] = 'R';
//        grid[br][bc] = 'B';
//        grid[or][oc] = 'O';
    }
    static void restoreGrid(char[][] to) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                grid[i][j] = to[i][j];
            }
        }
    }
    
}
