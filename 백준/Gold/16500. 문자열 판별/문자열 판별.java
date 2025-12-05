import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        String S = br.readLine();
        int N = Integer.parseInt(br.readLine());
        String[] s = new String[N];
        for (int i=0; i<N; i++) {
            s[i] = br.readLine();
        }

        boolean[] dp = new boolean[S.length()+1];
        dp[0] = true;
        for (int i=0; i<S.length(); i++) {
            if (!dp[i]) continue;
            iter: for (int j=0; j<N; j++) {
                if (i+s[j].length() > S.length()) continue;
                for (int k=0; k<s[j].length(); k++) {
                    if (S.charAt(i+k) != s[j].charAt(k)) {
                        continue iter;
                    }
                }
                dp[i+s[j].length()] = true;
            }
        }
        System.out.println(dp[S.length()] ? 1 : 0);
    }
}
