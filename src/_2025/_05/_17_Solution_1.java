package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3363
// -
public class _17_Solution_1 {
    static class Node implements Comparable<Node>{
        int startDay;
        int tape;
        public Node(int startDay, int tape){
            this.startDay = startDay;
            this.tape = tape;
        }
        @Override
        public int compareTo(Node n){
            int diff = n.tape - this.tape;
            return (diff == 0 ? this.startDay - n.startDay : diff);
        }
        @Override
        public String toString(){
            return "[ startDay="+this.startDay+", tape="+this.tape+" ]";
        }
    }
    static final int WEEK = 7, DAY = 50_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());

        int[] dayArr = new int[50100];
        int[] split = new int[50100];
        StringTokenizer st = null;
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            dayArr[S]++;
            dayArr[E+1]--;
            split[E]++;
        }

        for(int i = 0; i < 2; i++){
            for(int day = 1; day <= DAY; day++) dayArr[day] += dayArr[day-1];
        }

        //System.out.println(Arrays.toString(dayArr));

        int limit = DAY - WEEK * N + 1;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int day = 1; day <= limit; day++){
            pq.offer(new Node(day, dayArr[day + WEEK * N - 1] - dayArr[day-1]));
        }

        Node target = pq.poll();
        int answer = 0;

        //System.out.println(target);
        for(int week = 0, day = target.startDay; week < N; week++, day += WEEK){
            int end = day + WEEK - 1;
            for(int start = day; start < end; start++) answer += split[start];
            answer += dayArr[day+6] - dayArr[day+5];
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
