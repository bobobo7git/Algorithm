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
        StringBuilder sb = new StringBuilder(U);
        Set<String> set = new HashSet<>();
        for (int i=0; i<N; i++) {
            char c = sb.charAt(i);
            sb.deleteCharAt(i);
            String l = sb.substring(0, (N-1)/2);
            String r = sb.substring((N-1)/2, N-1);
            if (l.equals(r)) set.add(l);
            sb.insert(i, c);
        }
        if (set.size() == 0) return np;
        if (set.size() > 1) return nu;
        for (String s: set) {
            return s;
        }
        return null;
    }
}
