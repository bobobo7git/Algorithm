import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        Work[] works = new Work[plans.length];
        for (int i=0; i<plans.length; i++) {
            String name = plans[i][0];
            String start = plans[i][1];
            String playtime = plans[i][2];
            // 시작시간 분 기준으로 파싱
            String[] hhmm = start.split(":");
            int s = Integer.parseInt(hhmm[0]) * 60 + Integer.parseInt(hhmm[1]);
            int pt = Integer.parseInt(playtime);
            
            Work work = new Work(name, s, pt);
            works[i] = work;
        }
        // 시작 시간으로 정렬
        Arrays.sort(works);
        
        Deque<Work> stack = new ArrayDeque<>();
        Deque<String> finished = new ArrayDeque<>();
        // 스택에 첫번째 과제를 넣어놓고 시작
        int now = works[0].s;
        stack.push(works[0]);
        
        // for (int i=1; i<works.length; i++) {
        //     Work next = works[i];
        //     while (now < next.s && !stack.isEmpty() && now+stack.peek().pt <= next.s) {
        //         Work done = stack.pop();
        //         now += done.pt;
        //         finished.offer(done.name);
        //     }
        //     if (!stack.isEmpty() && now < next.s) {
        //         int diff = next.s - now;
        //         stack.peek().pt -= diff;
        //         now += diff;
        //     }
        //     stack.push(next);
        // }
        for (int i=1; i<works.length; i++) {
    Work next = works[i];
    while (!stack.isEmpty() && now < next.s) {
        Work cur = stack.peek();
        if (now + cur.pt <= next.s) {
            now += cur.pt;
            finished.offer(stack.pop().name);
        } else {
            cur.pt -= (next.s - now);
            now = next.s;
        }
    }
    now = next.s;
    stack.push(next);
}
        while (!stack.isEmpty()) finished.offer(stack.poll().name);
        
        String[] answer = new String[plans.length];
        for (int i=0; i<answer.length; i++) {
            answer[i] = finished.poll();
        }
        return answer;
    }
    
    static class Work implements Comparable<Work> {
        String name;
        int s;  // 시작시간
        int pt; // 플레이타임
        Work(String name, int s, int pt) {
            this.name = name;
            this.s = s;
            this.pt = pt;
        }
        @Override
        public String toString() {return name;}
        @Override
        public int compareTo(Work o) {
            return s - o.s; // 시작시간이 전부 달라서 겹칠 일이 없음
        }
    }
}