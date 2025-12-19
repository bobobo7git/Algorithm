import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(writer);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        SegmentTree minTree = new SegmentTree(arr, (o1, o2) -> {
            return Math.min(o1, o2);
        });
        SegmentTree maxTree = new SegmentTree(arr, (o1, o2) -> {
            return Math.max(o1, o2);
        });
        
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            bw.write(minTree.query(l, r)+" "+maxTree.query(l, r)+'\n');
        }
        bw.flush();
        bw.close();
    }

}

class SegmentTree {
    int[] arr;  // 원소 <= 10억
    int[] tree;
    int h;
    int size;   // N <= 10만
    Comparator<Integer> comparator;
    SegmentTree(int[] arr, Comparator<Integer> comparator) {
        this.arr = arr;
        calculateTreeSize(arr);
        this.tree = new int[this.size];
        this.comparator = comparator;
        init(1, 0, arr.length-1);
    }
    private void calculateTreeSize(int[] arr) {
        /*
        * 배열의 모든 원소가 세그먼트 트리의 리프노드
        * 1 2 4 ... N보다 크거나 같은 가장 작은 2의 배수
        * */
        int n = arr.length;
        this.h = (int) Math.ceil(Math.log(n) / Math.log(2));
        this.size = 1 << (h+1);
    }
    /*
    * 구간 포인터 start, end
    * 세그먼트 트리의 인덱스 node
    * */
    private void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        init(node*2, start, mid);
        init(node*2+1, mid+1, end);
        tree[node] = comparator.compare(tree[node*2], tree[node*2+1]);
    }
    /*
    * 구간 포인터 start, end
    * 세그먼트 트리의 인덱스 node
    * 검색 범위 포인터 left, right
    * */
    private int query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int v1 = query(node*2, start, mid, left, right);
        int v2 = query(node*2+1, mid+1, end, left, right);
        if (v1 == 0) return v2;
        if (v2 == 0) return v1;
        return comparator.compare(v1, v2);
    }
    int query(int left, int right) {
        return query(1, 0, arr.length-1, left-1, right-1);
    }
}
