import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        Map<Integer, String> map = Map.of(
                0, "zero",
                1, "one",
                2, "two",
                3, "three",
                4, "four",
                5, "five",
                6, "six",
                7, "seven",
                8, "eight",
                9, "nine"
        );
        Map<String, Integer> int2Str = Map.of(
                "zero", 0,
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9
                );
        List<String> list = new ArrayList<>();
        for (int i=M; i<=N; i++) {
            if (i < 10) {
                list.add(map.get(i));
            } else {
                int a = i / 10;
                int b = i % 10;
                StringBuilder sb = new StringBuilder();
                sb.append(map.get(a)).append(' ').append(map.get(b));
                list.add(sb.toString());
            }
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<list.size(); i++) {
            String[] spl = list.get(i).split(" ");
            for (String str: spl) {
                sb.append(int2Str.get(str));
            }
            sb.append(' ');
            if ((i+1) % 10 == 0) sb.append('\n');
        }
        System.out.println(sb);
    }
}
