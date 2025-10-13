import java.util.*;
class Solution {
    static Set<String> set;
    static boolean[][] visited;
    public int solution(String[] board) {
        int answer = -1;
        set = new HashSet<>();
        visited = new boolean[3][3];
        char[][] arr = new char[3][3];
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                arr[i][j] = '.';
                sb.append(board[i].charAt(j));
            }
        }
        bruteForce(1, arr);
        
        return answer = set.contains(sb.toString()) ? 1 : 0;
    }
    static void bruteForce(int turn, char[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                sb.append(arr[i][j]);
            }
        }
        set.add(sb.toString());
        if (checkOver(arr)) return;
        
        char node = turn % 2 != 0 ? 'O' : 'X';
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (arr[i][j] == '.') {

                    arr[i][j] = node;
                    bruteForce(turn+1, arr);
                    arr[i][j] = '.';

                }
                
                
            }
        }
    }
    static boolean checkOver(char[][] arr) {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (arr[i][j] != '.') {
                    int row = 0;
                    for (int ni=i; ni<3; ni++) {
                        if (arr[ni][j] == arr[i][j]) row++;
                    }
                    if (row == 3) return true;
                    int col = 0;
                    for (int nj=j; nj<3; nj++) {
                        if (arr[i][nj] == arr[i][j]) col++;
                    }
                    if (col == 3) return true;
                    int crs = 0;
                    for (int d=0; d<3; d++) {
                        int ni = i+d;
                        int nj = j+d;
                        if (ni == 3 || nj == 3) break;
                        if (arr[ni][nj] == arr[i][j]) crs++;
                    }
                    if (crs == 3) return true;
                    int rcrs = 0;
                    for (int d=0; d<3; d++) {
                        int ni = i+d;
                        int nj = j-d;
                        if (ni == 3 || nj < 0) break;
                        if (arr[ni][nj] == arr[i][j]) rcrs++;
                    }
                    if (rcrs == 3) return true;
                }
            }
        }
        return false;
    }
    
}