
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();
            for (int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();
                if (map.containsKey(type)) map.put(type, map.get(type)+1);
                else map.put(type, 1);
            }
            int sum = 1;
            for (String type: map.keySet()) {
                sum *= (map.get(type)+1);
            }
            sb.append(sum-1).append('\n');
        }
        System.out.print(sb);
    }
}
