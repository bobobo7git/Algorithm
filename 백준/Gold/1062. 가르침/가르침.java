import java.io.*;
import java.util.*;
/*
* anta tica -> a n t i c
* anta rc tica -> a n t i c + r
* anta hello tica -> a n t i c + h e l o
*
* a n t i c는 반드시 가르친다.
* 나머지 21개 중에서 K-5개를 고르는 경우의 수
* 21C10 = 352,716
* N = 50일때 17,635,800
* */
public class Main {
    static int[] words;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        words = new int[N];
        for (int i=0; i<N; i++) {
            String word = br.readLine();
            for (char c: word.toCharArray()) {
                words[i] |= 1 << (c-'a');
            }
        }
        int start = 0;
        start |= 1 << ('a' - 'a');
        start |= 1 << ('n' - 'a');
        start |= 1 << ('t' - 'a');
        start |= 1 << ('i' - 'a');
        start |= 1 << ('c' - 'a');
        System.out.println(dfs(start, 0, K-5));
    }
    /*
    * 가르친 글자의 목록을 조합으로 구한다.
    * 단어를 순회하면서 몇개 읽을 수 있는지 확인한다.
    * */
    static int dfs(int bits, int i, int remain) {
        if (remain == 0) {
            int cnt = 0;
            for (int w: words) {
                if ((w & bits) == w) cnt++;
            }
            return cnt;
        }
        int cnt = 0;
        for (int c=i; c<=26; c++) {
            int next = bits | (1 << c);
            cnt = Math.max(cnt, dfs(next, c+1, remain-1));
        }
        return cnt;
    }

}