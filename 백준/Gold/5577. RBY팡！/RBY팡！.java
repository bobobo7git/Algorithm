import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        int[] balls = new int[N];

        for (int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            balls[i] = n;
        }
        int min = N;
        for (int i=1; i<N-1; i++) {
//            if (balls[i-1] == balls[i+1] && balls[i] != balls[i-1]) {
//
//            }
            int tmp = balls[i];
            balls[i] = balls[i-1];
            min = Math.min(min, simulate(balls, i));
            balls[i] = tmp;
        }
        if (N > 1) {
            int head = balls[0];
            balls[0] = balls[1];
            min = Math.min(min, simulate(balls, 1));
            balls[0] = head;

            int tail = balls[N-1];
            balls[N-1] = balls[N-2];
            min = Math.min(min, simulate(balls, N-2));
            balls[N-1] = tail;
        }

        System.out.println(min);
    }
    static int simulation(int[] balls) {
        Deque<int[]> stack = new ArrayDeque<>();

        for (int i=0; i<N; i++) {
            int n = balls[i];
            if (!stack.isEmpty() && stack.peek()[0] == n) {
                stack.push(new int[]{n, stack.peek()[1]+1});
            } else stack.push(new int[]{n, 1});

            if (!stack.isEmpty() && i < N-1 && balls[i+1] == stack.peek()[0]) continue;
            while (!stack.isEmpty() && stack.peek()[1] >= 4) {
                int cnt = stack.peek()[1];
                for (int j=0; j<cnt; j++) {
                    stack.pop();
                }
            }
        }

        return stack.size();
    }
    static int simulate(int[] balls, int startIndex) {
        int l = startIndex;
        int r =  startIndex;
        int color = balls[startIndex];
        int cnt = 1;
        int sum = 0;
        while (l > 0 || r < N-1) {
            boolean flag = false;

            if (l > 0 && balls[l-1] == color) {
                l--;
                cnt++;
                flag = true;
            }
            if (r < N-1 && balls[r+1] == color) {
                r++;
                cnt++;
                flag = true;
            }
            if (!flag && cnt >= 4) {
                sum += cnt;
                cnt = 0;
                if (l > 0) color = balls[l-1];
                else if (r < N-1) color = balls[r+1];
            } else if (!flag) break;
        }
        if (balls[l] == balls[r] && balls[l] == color) {
            if (cnt >= 4) sum += cnt;
        }

        return N-sum;
    }


}
