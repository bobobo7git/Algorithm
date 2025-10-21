
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String s = br.readLine();
        List<String> list = new ArrayList<>();
        // 1 <= i < j < N인 두 수를 고른다
        for (int i=1; i<s.length(); i++) {
            for (int j=i+1; j<s.length(); j++) {
                // 0 <= ... < i, i <= ... < j, j <= ... < N
                StringBuilder sb = new StringBuilder();
                for (int k=i-1; k>=0; k--) {
                    sb.append(s.charAt(k));
                }
                for (int k=j-1; k>=i; k--) {
                    sb.append(s.charAt(k));
                }
                for (int k=s.length()-1; k>=j; k--) {
                    sb.append(s.charAt(k));
                }
                list.add(sb.toString());
            }
        }
        Collections.sort(list);
        System.out.println(list.get(0));
    }
}

