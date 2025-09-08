
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        String U = br.readLine();

        String S = check(N, U);
        System.out.println(S);
    }
    static String check(int N, String U) {
        String np = "NOT POSSIBLE";
        String nu = "NOT UNIQUE";

        if (N % 2 == 0) return np;
        String s1 = U.substring(0, N/2);
        String s2 = U.substring(N/2, N);
        String s3 = U.substring(0, N/2+1);
        String s4 = U.substring(N/2+1, N);

        Set<String> set = new HashSet<>();
        int i = 0;
        int j = 0;
        int cnt = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++; j++; cnt++;
            } else {
                if (i+1 < s1.length() && s1.charAt(i+1) == s2.charAt(j)) i++;
                else if (j+1 < s2.length() && s2.charAt(j+1) == s1.charAt(i)) j++;
                else break;
            }
        }

        if (cnt == s1.length()) set.add(s1);
        if (cnt == s2.length()) set.add(s2);


        if (set.size() > 1) return nu;

        i = 0; j = 0; cnt = 0;
        while (i < s3.length() && j < s4.length()) {
            if (s3.charAt(i) == s4.charAt(j)) {
                i++; j++; cnt++;
            } else {
                if (i+1 < s3.length() && s3.charAt(i+1) == s4.charAt(j)) i++;
                else if (j+1 < s4.length() && s4.charAt(j+1) == s3.charAt(i)) j++;
                else break;
            }
        }

        if (cnt == s3.length()) set.add(s3);
        if (cnt == s4.length()) set.add(s4);

        if (set.isEmpty()) return np;
        if (set.size() == 1) for (String s: set) return s;
        return nu;
    }
}
