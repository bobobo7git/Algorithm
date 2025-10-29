import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int g = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        String W = br.readLine();
        String S = br.readLine();

        int[] sml = new int[26];
        int[] lg = new int[26];

        for (char c: W.toCharArray()) {
            // 소문자 또는 대문자
            if (c >= 'a' && c <= 'z') {
                sml[c-'a']++;
            } else {
                lg[c-'A']++;
            }
        }
        /*
        * 슬라이딩 윈도우
        * 윈도우 한칸 이동마다 소문자, 대문자 갯수 체크
        * O(S*26*2)
        * */
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        int l = 0, r = 0;
        for (; r<g; r++) {
            char c = S.charAt(r);
            if (c >= 'a' && c <= 'z') {
                c1[c-'a']++;
            } else {
                c2[c-'A']++;
            }
        }

        int cnt = 0;

        boolean first = true;
        for (int i=0; i<26; i++) {
            if (c1[i] != sml[i] || c2[i] != lg[i]) {
                first = false;
                break;
            }
        }
        if (first) cnt++;
        while (r < s) {
            // 윈도우 이동
            char plus = S.charAt(r++);
            char minus = S.charAt(l++);

            if (plus >= 'a' && plus <= 'z') c1[plus-'a']++;
            else c2[plus-'A']++;
            if (minus >= 'a' && minus <= 'z') c1[minus-'a']--;
            else c2[minus-'A']--;

            boolean can = true;
            for (int i=0; i<26; i++) {
                if (c1[i] != sml[i] || c2[i] != lg[i]) {
                    can = false;
                    break;
                }
            }
            if (can) cnt++;
        }
        System.out.println(cnt);

    }
}
