
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        Deque<Command> stack = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean type = st.nextToken().equals("type");

            if (type) {
                char c = st.nextToken().charAt(0);
                int t = 0;
                int d = Integer.parseInt(st.nextToken());
                stack.push(new Command(type, c, t, d));
            }
            else {
                char c = '.';
                int t = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                stack.push(new Command(type, c, t, d));
            }
        }
        Deque<Character> res = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            Command now = stack.pop();
            if (!now.type) {
                while (!stack.isEmpty() && stack.peek().d >= now.d-now.t) {
                    stack.pop();
                }
            }
            else res.push(now.c);
        }
        StringBuilder sb = new StringBuilder();
        while (!res.isEmpty()) {
            sb.append(res.pop());
        }
        System.out.println(sb);
    }
    static class Command {
        boolean type;
        char c;
        int t;
        int d;
        Command(boolean type, char c, int t, int d) {
            this.type = type;
            this.c = c;
            this.t = t;
            this.d = d;
        }
        @Override
        public String toString() {
            return (type ? "type" : "undo") +" "+c+" "+t+" "+d;
        }
    }

}

