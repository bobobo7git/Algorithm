import java.io.*;
import java.util.*;

/*
* 같은 문자 = 같은 숫자
* 다른 문자 = 다른 문자
* 공백없는 대문자만으로 구성
* => 0-9 최대 10!가지 -> 백트래킹
* */
public class Main {
    static char[] s1;
    static char[] s2;
    static char[] r;
    static int[] chr2id;
    static boolean can;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        s1 = st.nextToken().toCharArray();
        s2 = st.nextToken().toCharArray();
        r = st.nextToken().toCharArray();

        char[] arr = getUniqueCharacters();
        if (arr == null) {
            System.out.println("NO");
            return;
        }
        chr2id = new int[26];
        Arrays.fill(chr2id, -1);
        for (int i=0; i<arr.length; i++) {
            chr2id[arr[i] - 'A'] = i;
        }

        int[] vals = new int[arr.length];
        Arrays.fill(vals, -1);
        boolean[] visited = new boolean[10];
        boolean result = perm(arr, vals, 0, visited);
        System.out.println(can ? "YES" : "NO");
    }
    /*
    * 문자에 숫자를 중복없이 순열로 할당
    * */
    static boolean perm(char[] arr, int[] vals, int idx, boolean[] visited) {
        if (idx == arr.length) {
            return getAnswer(arr, vals);
        }
        boolean ret = false;
        // 현재 idx에 넣을 0-9 선택
        for (int i=0; i<10; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            vals[idx] = i;
            ret = perm(arr, vals, idx+1, visited);
//            if (ret) return true;
            vals[idx] = -1;
            visited[i] = false;
        }
        return ret;
    }
    static boolean getAnswer(char[] arr, int[] vals) {
        long sum1 = 0;
        long sum2 = 0;
        for (int i=0; i<s1.length; i++) {
            int digit = vals[chr2id[s1[i]-'A']];
            sum1 += digit;
            if (i < s1.length-1) sum1 *= 10;
        }
        for (int i=0; i<s2.length; i++) {
            int digit = vals[chr2id[s2[i]-'A']];
            sum2 += digit;
            if (i < s2.length-1) sum2 *= 10;
        }
        long sum = 0;
        for (int i=0; i<r.length; i++) {
            int digit = vals[chr2id[r[i]-'A']];
            sum += digit;
            if (i < r.length-1) sum *= 10;
        }

//        System.out.println(sum1+" "+sum2+" "+sum);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(vals));
        if (sum1 + sum2 == sum) can = true;
        return sum1 + sum2 == sum;
    }
    /*
    * 고유한 문자 배열 추출
    * 10개 넘어가면 불가
    * */
    static char[] getUniqueCharacters() {
        boolean[] check = new boolean[26];
        int cnt = 0;
        for (char c: s1) {
            if (!check[c-'A']) {
                check[c-'A'] = true;
                cnt++;
            }
        }
        for (char c: s2) {
            if (!check[c-'A']) {
                check[c-'A'] = true;
                cnt++;
            }
        }
        for (char c: r) {
            if (!check[c-'A']) {
                check[c-'A'] = true;
                cnt++;
            }
        }
        char[] ret = new char[cnt];
        if (cnt > 10) return null;
        int p = 0;
        for (int i=0; i<26; i++) {
            if (check[i]) ret[p++] = (char) (i + 'A');
        }
        return ret;
    }


}
