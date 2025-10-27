
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int M = Integer.parseInt(br.readLine());
        List<int[]> lines = new ArrayList<>();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (l == 0 && r == 0) break;
            lines.add(new int[]{l, r});
        }
        // 시작점이 작고 끝잠이 먼 순으로 정렬
        Collections.sort(lines, (o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            return o2[1] - o1[1];
        });
        /*
        * 1. 시작점이 0 이하인 선분 중에서 가장 멀리가는 선분을 찾는다.
        * 2. M을 덮을 때까지 잇는다.
        * */
        int idx = -1;
        int longest = -1;
        for (int i=0; i<lines.size(); i++) {
            int[] line = lines.get(i);
            if (line[0] <= 0) {
                if (longest < line[1]) {
                    longest = line[1];
                    idx = i;
                }
            }
        }
        if (idx == -1) {
            System.out.println(0);
            return;
        }
        int e = lines.get(idx)[1];
        int cnt = 1;

        while (e < M) {
            int l = e;
            // 시작 선분부터 다음 선분을 탐색
            for (int i=idx+1; i<lines.size(); i++) {
                int[] line = lines.get(i);
                // 연결할 수 없으면 종료
                if (e < line[0]) break;
                // 가장 멀리갈 수 있는 선분 하나를 골라
                // 정렬해두었기 때문에 idx이전은 다음 반복에서 체크하지 않음
                if (line[0] <= e && line[1] > l) {
                    l = line[1];
                    idx = i;
                }
            }
            if (l == e) {
                cnt = 0;
                break;
            }
            cnt++;
            e = l;

        }
        System.out.println(cnt);

    }
}
