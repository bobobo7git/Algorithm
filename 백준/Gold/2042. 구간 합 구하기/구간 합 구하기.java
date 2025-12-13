import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i=0; i<N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        SegmentTree sgt = new SegmentTree(arr);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            long l = Long.parseLong(st.nextToken());
            long r = Long.parseLong(st.nextToken());
            // 값 바꾸기
            if (cmd == 1) {
                sgt.update((int) l-1, r);
            } else if (cmd == 2) {
                long res = sgt.query((int) l-1, (int) r-1);
                sb.append(res).append('\n');
            }
        }
        System.out.print(sb);
    }
    static class SegmentTree {
        long[] arr;
        int size, h;
        long[] tree;
        SegmentTree(long[] arr) {
            this.arr = arr;
            calculateSize(arr);
            tree = new long[size];
            init(1, 0, arr.length-1);
        }
        void calculateSize(long[] arr) {
            int n = arr.length;
            h = (int) Math.ceil(Math.log(n) / Math.log(2));
            size = 1 << (h+1);
        }
        /*
        * 현재 노드를 [start, end]의 합으로 저장
        * */
        void init(int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            // 좌 우로 내려가기
            int mid = (start + end) / 2;
            init(node*2, start, mid);
            init(node*2+1, mid+1, end);

            tree[node] = tree[node*2] + tree[node*2+1];
        }
        /*
        * 구간 [left, right]의 합 조회하기
        * */
        long query(int node, int start, int end, int left, int right) {
            if (end < left || right < start) return 0;
            // 찾으려는 범위: [left, right]
            // 현재 노드의 범위: [start, end]
            // 현재 노드가 검색 범위에 포함되면 바로 리턴
            if (left <= start && end <= right) return tree[node];
            int mid = (start + end) / 2;
            long lsum = query(node*2, start, mid, left, right);
            long rsum = query(node*2+1, mid+1, end, left, right);
            return lsum + rsum;
        }
        long query(int left, int right) {
            return query(1, 0, arr.length-1, left, right);
        }
        /*
        * 배열의 특정 index 값을 val로 바꾸기
        * */
        void update(int index, long val, int node, int start, int end) {
            if (index < start || end < index) return;
            if (start == end) {
                arr[index] = val;
                tree[node] = val;
                return;
            }
            int mid = (start + end) / 2;
            update(index, val, node*2, start, mid);
            update(index, val, node*2+1, mid+1, end);
            tree[node] = tree[node*2] + tree[node*2+1];
        }
        void update(int index, long val) {
            update(index, val, 1, 0, arr.length-1);
        }
    }
}