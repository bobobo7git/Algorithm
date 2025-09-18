import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine());
        String[] list = new String[N];

        Alphabet[] dict = new Alphabet[26];
        for (int i=0; i<26; i++) {
            dict[i] = new Alphabet(i, 0);
        }
        for (int i=0; i<N; i++) {
            String str = br.readLine();
            list[i] = str;
            char[] word = str.toCharArray();
            for (int j=0; j<word.length; j++) {
                Alphabet a = dict[word[j]-'A'];
                a.g += (long) Math.pow(10, word.length-j) ;    // 자릿수 가중치: 최대길이가 8이므로 이것보다 크면 됨
//                a.g++; // 빈도
            }
        }
        // 기준대로 정렬
        Arrays.sort(dict);

        // map 생성
        Map<Character, Integer> map = new HashMap<>();
        int val = 9;
        for (int i=0; i<dict.length; i++) {
            map.put((char) ('A'+dict[i].c), val--);
            if (val < 0) break;
        }


        int sum = 0;
        for (String s: list) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<s.length(); i++) {
                sb.append(map.get(s.charAt(i)));
            }

            sum += Integer.parseInt(sb.toString());

        }
        System.out.println(sum);


    }
    static class Alphabet implements Comparable<Alphabet> {
        int c;
//        int l;  // 자릿수
//        int f;  // 빈도
        long g;
        Alphabet(int c, long g) {
            this.c = c;
            this.g = g;
        }
        @Override
        public int compareTo(Alphabet o) {
            return Long.compare(o.g, this.g);
        }
    }
}

