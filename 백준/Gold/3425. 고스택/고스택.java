import java.io.*;
import java.util.*;

public class Main {
    static InputStreamReader reader = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(reader);
    static int[] inputs;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        while (true) {
            Program program = new Program();
            input(program);
            if (!program.isReady) break;
            for (int n: inputs) {
                program.init(n);
                program.run();
                sb.append(program.result).append('\n');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString().trim());
    }
    static void input(Program program) throws IOException {
        while (true) {
            String cmd = br.readLine();
            if (cmd.equals("QUIT")) return;
            program.isReady = true;
            if (cmd.equals("END")) break;
            program.commands.add(cmd);
        }

        int N = Integer.parseInt(br.readLine());
        inputs = new int[N];
        for (int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            inputs[i] = n;
        }
        String end = br.readLine();
    }
    static class Program {
        List<String> commands;
        Deque<Long> stack;
        String result;
        boolean isReady;
        static final String ERROR = "ERROR";
        Program() {
            commands = new ArrayList<>();
            stack = new ArrayDeque<>();
            isReady = false;
        }
        void init(int n) {
            stack.clear();;
            stack.push((long) n);
        }
        void run() {
            for (String cmd: commands) {
                try {
                    if (cmd.startsWith("NUM")) {
                        String[] par = cmd.split(" ");
                        num(Integer.parseInt(par[1]));
                    } else {
                        switch (cmd) {
                            case "POP": pop(); break;
                            case "INV": inv(); break;
                            case "DUP": dup(); break;
                            case "SWP": swp(); break;
                            case "ADD": add(); break;
                            case "SUB": sub(); break;
                            case "MUL": mul(); break;
                            case "DIV": div(); break;
                            case "MOD": mod(); break;
                        }
                    }
                } catch (Exception e) {
                    result = ERROR;
                    return;
                }
                if (!stack.isEmpty() && Math.abs(stack.peek()) > 1000000000) {
                    result = ERROR;
                    return;
                }

            }
            if (stack.size() != 1) {
                result = ERROR;

                return;
            }
            result = stack.peek().toString();
        }
        void num(int x) {stack.push((long) x);}
        void pop() {stack.pop();}
        void inv() {stack.push(stack.pop() * -1);}
        void dup() {stack.push(stack.peek());}
        void swp() {
            Long f = stack.pop();
            Long s = stack.pop();
            stack.push(f);
            stack.push(s);
        }
        void add() {
            Long f = stack.pop();
            Long s = stack.pop();
            stack.push(f+s);
        }
        void sub() {
            Long f = stack.pop();
            Long s = stack.pop();
            stack.push(s-f);
        }
        void mul() {
            Long f = stack.pop();
            Long s = stack.pop();
            stack.push(f*s);
        }
        void div() {
            Long f = stack.pop();
            Long s = stack.pop();
            stack.push(s/f);
        }
        void mod() {
            Long f = stack.pop();
            Long s = stack.pop();
            stack.push(s%f);
        }


    }
}

