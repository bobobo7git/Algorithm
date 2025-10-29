
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        /*
        * 중간에 폭이 넓어져도 그 윗칸의 너비만큼만 가능
        * 피자는 들어온 순서대로 넣어야한다.
        * 피자를 넣고나면 아래의 공간도 아무런 쓸모가 없다.
        * 단조 감소 스택을 유지해서 peek이 최대 깊은 공간의 지름을 표시한다.
        * */
        Deque<int[]> stack = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=D; i++) {
            int width = Integer.parseInt(st.nextToken());
            if (stack.isEmpty()) stack.push(new int[]{width, i});
            else if (stack.peek()[0] < width) stack.push(new int[]{stack.peek()[0], i});
            else stack.push(new int[]{width, i});
        }

        st = new StringTokenizer(br.readLine());

        int pos = 0;
        for (int i=0; i<N; i++) {
            int pizza = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && stack.peek()[0] < pizza) {
                stack.pop();
            }
            if (i == N-1) {
                pos = stack.isEmpty() ? 0 : stack.peek()[1];
            }
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        System.out.println(pos);

    }
}
