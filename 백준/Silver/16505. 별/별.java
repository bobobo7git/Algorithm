import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (String s: star(n)) {
            sb.append(s.strip()).append('\n');
        }
        System.out.println(sb);

    }

    static List<String> star(int n) {
        if (n <= 0) return List.of("*");
        List<String> list = new ArrayList<>();
        List<String> prev = star(n-1);

        // n-1번째 별 두 개 붙이기
        for (String row: prev) {
            StringBuilder sb = new StringBuilder();
            sb.append(row).append(row);
            list.add(sb.toString());
        }
        // 공백 추가
        int l = prev.get(0).length();
        for (String row: prev) {
            StringBuilder nr = new StringBuilder();
            nr.append(row);
            for (int i=0; i<l; i++) {
                nr.append(' ');
            }

            list.add(nr.toString());
        }

        return list;
    }

}
