
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        char[] sound = br.readLine().toCharArray();
        /*
        * quqacukqauackck
        * qu ac kq ua  ck
        *   q  u  a  ck
        *
        * quqaquuacakcqckkuaquckqauckack
        * qu a         ck   qu   a ck
        *   q  u   a c   k      q u  ack
        *     q uac k q   ua  ck
        *
        * q u a c k 각각 기록하고 있는 객체 생성
        *
        * */
        Map<Character, LinkedList<Duck>> map = new HashMap<>();
        map.put('q', new LinkedList<>());
        map.put('u', new LinkedList<>());
        map.put('a', new LinkedList<>());
        map.put('c', new LinkedList<>());
        map.put('k', new LinkedList<>());
        int result = 0;
        int cnt = 0;
        boolean can = true;
        outer: for (char c: sound) {
            LinkedList<Duck> list = map.get(c);
            switch (c) {
                case 'q': {
                    list.add(new Duck(c));
                    cnt++;
                    break;
                }
                case 'u': {
                    if (map.get('q').isEmpty()) {
                        can = false;
                        break outer;
                    }
                    Duck duck = map.get('q').removeLast();
                    list.add(duck);
                    break;
                }
                case 'a': {
                    if (map.get('u').isEmpty()) {
                        can = false;
                        break outer;
                    }
                    Duck duck = map.get('u').removeLast();
                    list.add(duck);
                    break;
                }
                case 'c': {
                    if (map.get('a').isEmpty()) {
                        can = false;
                        break outer;
                    }
                    Duck duck = map.get('a').removeLast();
                    list.add(duck);
                    break;
                }
                case 'k': {
                    if (map.get('c').isEmpty()) {
                        can = false;
                        break outer;
                    }
                    Duck duck = map.get('c').removeLast();
                    cnt--;
                    break;
                }
            } // end s-c
            result = Math.max(result, cnt);
        }
        if (!map.get('q').isEmpty()) can = false;
        if (!map.get('u').isEmpty()) can = false;
        if (!map.get('a').isEmpty()) can = false;
        if (!map.get('c').isEmpty()) can = false;
        if (!map.get('k').isEmpty()) can = false;
        System.out.println(can ? result : -1);
    }
    static class Duck {
        char c;
        Duck(char c) {
            this.c = c;
        }
    }

}