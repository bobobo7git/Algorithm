import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        String s = br.readLine();
        int N = Integer.parseInt(br.readLine());
        int[] lenDict = new int[N];
        int[][] dict = new int[N][26];
        String[] words = new String[N];
        for (int i=0; i<N; i++) {
            String w = br.readLine();
            words[i] = w;
            for (int j=0; j<w.length(); j++) {
                int b = w.charAt(j) - 'a';
                dict[i][b]++;
                lenDict[i] = w.length();
            }
        }
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);

        for (int i=0; i<s.length(); i++) {
            outer: for (int j=0; j<N; j++) {
                if (i+lenDict[j] <= s.length()) {
                    int[] check = new int[26];
                    int len = lenDict[j];
                    for (int k=0; k<len; k++) {
                        check[s.charAt(i+k)-'a']++;
                    }
                    for (int k=0; k<26; k++) {
                        if (check[k] != dict[j][k]) continue outer;
                    }
                    int cost = 0;
                    for (int k=0; k<len; k++) {
                        if (s.charAt(i+k) != words[j].charAt(k)) cost++;
                    }
                    if (i==0) {
                        if (dp[i+len-1] == -1) dp[i+len-1] = cost;
                        else dp[i+len-1] = Math.min(cost, dp[i+len-1]);
                    }
                    else if (dp[i-1] != -1) {
                        if (dp[i+len-1] == -1) dp[i+len-1] = dp[i-1]+cost;
                        else dp[i+len-1] = Math.min(dp[i+len-1], dp[i-1]+cost);
                    }
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[s.length()-1]);
    }
}

