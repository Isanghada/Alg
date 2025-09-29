package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30108
// - 
public class _29_Solution_1 {
    static class Node implements Comparable<Node> {
        int n;
        int a;
        public Node(int n, int a){
            this.n = n;
            this.a = a;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(o.a, this.a);
        }
        @Override
        public String toString(){
            return "[n = "+this.n+", a = "+this.a+"]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        List<Integer>[] adjList = new List[N+1];
        for(int n = 0; n <= N; n++) adjList[n] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int n = 2; n <= N; n++) {
            int p = Integer.parseInt(st.nextToken());
            adjList[p].add(n);
        }

        st = new StringTokenizer(in.readLine());
        Node[] A = new Node[N+1];
        for(int n = 1; n <= N; n++) A[n] = new Node(n, Integer.parseInt(st.nextToken()));

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(A[1]);

        long answer = 0;
        while(!pq.isEmpty()){
            // System.out.println(pq);
            Node cur = pq.poll();
            answer += cur.a;

            for(int next : adjList[cur.n]) pq.offer(A[next]);

            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}