package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23889
// -
public class _11_Solution_1 {
    static class Node implements Comparable<Node>{
        int point;
        int count;
        public Node(int point, int count){
            this.point = point;
            this.count = count;
        }
        @Override
        public int compareTo(Node n){
            int diff = n.count - this.count;
            return diff == 0 ? this.point - n.point : diff;
        }
        @Override
        public String toString(){
            return "[ point="+this.point+", count="+this.count+" ]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] castleArr = new int[N+1];
        st = new StringTokenizer(in.readLine());
        for(int n = 1; n <= N; n++) castleArr[n] = castleArr[n-1] + Integer.parseInt(st.nextToken());

        int[] stoneArr = new int[K+1];
        st = new StringTokenizer(in.readLine());
        stoneArr[K] = N+1;
        for(int k = 0; k < K; k++) stoneArr[k] = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int k = 0; k < K; k++){
            int sum = castleArr[stoneArr[k+1]-1] - castleArr[stoneArr[k]-1];
            pq.offer(new Node(stoneArr[k], sum));
        }

        // System.out.println(pq);
        List<Integer> answerList = new ArrayList<>();
        while(M-- > 0) answerList.add(pq.poll().point);

        Collections.sort(answerList);

        for(int answer : answerList) sb.append(answer).append("\n");

        // 정답 출력
        System.out.println(sb);
    }
}
