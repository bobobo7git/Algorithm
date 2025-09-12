import java.io.*;
import java.util.*;

public class Main {
    static int[][] grid;
    static boolean[][] visited;
    static Domino[] dominos;
    static int cnt;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        grid = new int[8][7];
        for (int i=0; i<8; i++) {
            String line = br.readLine();
            for (int j=0; j<7; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }
        dominos = new Domino[28];
        int p = 0;
        for (int i=0; i<=6; i++) {
            for (int j=i; j<=6; j++) {
                Domino domino = new Domino(i, j);
                dominos[p++] = domino;
            }
        }
        visited = new boolean[8][7];
        cnt = 0;
        dfs(0);
        System.out.println(cnt);
    }
    static void dfs(int d) {
        if (d == 28) {
            cnt++;
            return;
        }

        for (int i=0; i<8; i++) {
            for (int j=0; j<7; j++) {
                if (visited[i][j]) continue;
                // 가로 보기
                if (j < 6 && !visited[i][j+1]) {
                    // grid에 맞는 도미노 검색
                    for (Domino dom: dominos) {
                        if (dom.visited) continue;
                        for (int t=0; t<2; t++) {
                            int[][] shape = dom.getTypeOf(t);
                            if (shape == null) continue;
                            if (grid[i][j] == shape[0][0] && grid[i][j+1] == shape[0][1]) {
                                dom.visited = true;
                                visited[i][j] = true;
                                visited[i][j+1] = true;
                                dfs(d+1);
                                dom.visited = false;
                                visited[i][j] = false;
                                visited[i][j+1] = false;
                            }
                        }
                    }
                }
                // 세로 보기
                if (i < 7 && !visited[i+1][j]) {
                    // grid에 맞는 도미노 검색
                    for (Domino dom: dominos) {
                        if (dom.visited) continue;
                        for (int t=2; t<4; t++) {
                            int[][] shape = dom.getTypeOf(t);
                            if (shape == null) continue;
                            if (grid[i][j] == shape[0][0] && grid[i+1][j] == shape[1][0]) {
                                dom.visited = true;
                                visited[i][j] = true;
                                visited[i+1][j] = true;
                                dfs(d+1);
                                dom.visited = false;
                                visited[i][j] = false;
                                visited[i+1][j] = false;
                            }
                        }
                    }
                }
                return;
            }
        }

    }
    static class Domino {
        private int[][][] val; // type a b
        boolean visited;
        Domino(int a, int b) {
            val = new int[4][2][2];
            val[0] = new int[][]{{a, b}};
            if (a != b) val[1] = new int[][]{{b, a}};
            else val[1] = null;
            val[2] = new int[][]{{a}, {b}};
            if (a != b) val[3] = new int[][]{{b}, {a}};
            else val[3] = null;
            visited = false;
        }
        int[][] getTypeOf(int type) {
            if (type < 0 || type > 4) return null;
            return val[type];
        }
    }
}

