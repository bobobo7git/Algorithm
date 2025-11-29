import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);

        int N = Integer.parseInt(br.readLine());

        Deck[] snapshots = new Deck[N+1];
        Deck cur = null;
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            switch (cmd) {
                case "push": {
                    int x = Integer.parseInt(st.nextToken());
                    // push 할때만 새 덱을 생성
                    // 기존 덱을 prev로 쓰는 덱
                    cur = new Deck(x, cur);
                    break;
                }
                case "pop": {
                    cur = cur.prev;
                    break;
                }
                case "print": {
                    if (cur == null) bw.write("0\n");
                    else bw.write(cur.sum + "\n");
                    break;
                }
                case "restore": {
                    int idx = Integer.parseInt(st.nextToken());
                    cur = snapshots[idx];
                    break;
                }
            }
            snapshots[i] = cur;
        }
        bw.flush();
        bw.close();
    }
    static class Deck {
        Deck prev;
        int head;
        long sum;
        Deck(int head, Deck prev) {
            this.head = head;
            this.prev = prev;
            this.sum = prev == null ? head : prev.sum + head;
        }
    }

}