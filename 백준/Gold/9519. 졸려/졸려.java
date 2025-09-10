import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        Set<String> set = new LinkedHashSet<>();
        Map<Integer, String> id2str = new HashMap<>();
        
        int cycle = 0;
        while (true) {
            if (set.contains(s)) break;
            set.add(s);
            id2str.put(cycle, s);
            cycle++;

            for (int i=0; i<s.length(); i++) {
                if (i % 2 == 0) sb.append(s.charAt(i));
                else stack.push(s.charAt(i));
            }
            while (!stack.isEmpty()) sb.append(stack.pop());

            s = sb.toString();
            sb.setLength(0);
        }
        System.out.println(id2str.get(N % cycle));
    }

}
