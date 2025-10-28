import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        int N = enroll.length;
        Node[] nodes = new Node[N];
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<N; i++) {
            nodes[i] = new Node(i);
            map.put(enroll[i], i);
        }
        Node.wallet = new int[N];
        for (int i=0; i<N; i++) {
            if (!referral[i].equals("-")) {
                nodes[i].link(nodes[map.get(referral[i])]);
            }
        }
        final int m = 100;
        for (int i=0; i<seller.length; i++) {
            int sellerId = map.get(seller[i]);
            nodes[sellerId].sell(amount[i]*m);
        }
        
        return Node.wallet;
    }
    static class Node {
        Node ref;
        int i;
        static int[] wallet;
        Node(int i) {
            this.i = i;
        }
        void link(Node o) {
            this.ref = o;
        }
        void sell(int amount) {
            wallet[i] += amount - amount / 10;
            if (amount / 10 == 0) {
                // 자신이 전부 가짐
                return;
            }

            if (ref != null) ref.sell(amount/10);
        }
    }
}