package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20311
// -
public class _23_Solution_1 {
    static class Tube implements Comparable<Tube>{
        int num;
        int count;
        public Tube(int num, int count){
            this.num = num;
            this.count = count;
        }
        public int getNum(){
            return this.num;
        }
        public int getCount(){
            return this.count;
        }
        public void minusCount(){
            this.count--;
        }
        @Override
        public int compareTo(Tube o) {
            return Integer.compare(o.count, this.count);
        }
        @Override
        public String toString() {
            return "[ num="+this.num+", count="+this.count+" ]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        PriorityQueue<Tube> pq = new PriorityQueue<>();
        for(int num = 1; num <= K; num++) pq.offer(new Tube(num, Integer.parseInt(st.nextToken())));

        Deque<Tube> past = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();
        boolean flag = true;
        while(!pq.isEmpty()){
            Tube cur = pq.poll();
            if(answer.size() > 0 && answer.get(answer.size()-1) == cur.getNum()){
                flag = false;
                break;
            }

            if(!past.isEmpty()) pq.offer(past.pollFirst());
            answer.add(cur.getNum());
            cur.minusCount();
            if(cur.getCount() > 0) past.offerLast(cur);
        }

        if(!flag || !past.isEmpty()) sb.append(-1);
        else for(int num : answer) sb.append(num).append(" ");

        // 정답 출력
        System.out.println(sb);
    }
}
