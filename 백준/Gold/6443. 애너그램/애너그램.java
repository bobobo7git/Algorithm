import java.io.*;
import java.util.*;

public class Main {
    static char[] str;
    static boolean[] visited;
    static int[] cnt;
    static Set<String> tset;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
             str = br.readLine().toCharArray();
             cnt = new int[26];
             for (char c: str) {
                 cnt[c-'a']++;
             }
             visited = new boolean[str.length];
             tset = new HashSet<>();
             Arrays.sort(str);

             dfs(new StringBuilder());


        }
        bw.flush();
        bw.close();

    }

    static void dfs(StringBuilder sb) throws IOException {
        // base
        if (sb.length() == str.length) {
            bw.write(sb.toString());
            bw.newLine();
            return;
        }

        for (int i=0; i<26; i++) {
            if (cnt[i] == 0) continue;
            sb.append((char) ('a'+i));
            cnt[i]--;
            dfs(sb);
            cnt[i]++;
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
