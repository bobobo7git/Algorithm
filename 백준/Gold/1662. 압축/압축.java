
import java.io.*;
import java.util.*;

public class Main {
    static char[] s;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        String str = br.readLine();
        s = str.toCharArray();
        visited = new boolean[s.length];
        System.out.println(recur(0));
    }
    static int recur(int start) {
        int cnt = 0;
        for (int i=start; i<s.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            if (s[i] != '(' && s[i] != ')') {
                cnt++;
            }

            if (s[i] == '(') {
                int res = recur(i+1);
                cnt += res * (s[i-1]-'0')-1;
            } else if (s[i] == ')') {
                return cnt;
            }
        }

        return cnt;
    }
}
