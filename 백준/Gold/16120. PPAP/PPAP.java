import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        String s = br.readLine();
        Stacker stack = new Stacker();
        for (char c: s.toCharArray()) {
            stack.push(c);
            stack.ppap();
        }
        System.out.println(stack.result());

    }
    static class Stacker {
        private static class Elem {
            char val;
            Elem next, before;
            Elem(char val) {
                this.val = val;
            }
            void connect(Elem next) {
                this.next = next;
                next.before = this;
            }
            void disconnect(Elem o) {
                this.before = null;
                o.next = null;
            }

        }
        int size;
        Elem peek;
        Stacker() {
            size = 0;
        }
        void push(char c) {
            Elem next = new Elem(c);
            if (size > 0) peek.connect(next);
            peek = next;
            size++;
        }
        Elem pop() {
            if (size == 0) return null;
            Elem temp = peek.before;
            peek.disconnect(peek.before);
            peek = temp;
            size--;
            return peek;
        }
        void ppap() {
            if (size < 4) return;
            Elem now = peek;

            if (now.val != 'P') return;
            if (now.before.val != 'A') return;
            if (now.before.before.val != 'P') return;
            if (now.before.before.before.val != 'P') return;

            for (int i=0; i<3; i++) pop();
        }
        boolean isPPAP() {
            return size == 1 && peek.val == 'P';
        }
        String result() {
            return isPPAP() ? "PPAP" : "NP";
        }

    }
}

