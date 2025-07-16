package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/30797
// - 프림 : 1번 위치를 기준으로 프림 알고리즘을 통해 최소 스패닝 트리 탐색!
public class _16_Solution_1 {
    static class Node implements Comparable<Node>{
        int to;
        long cost;
        long time;
        public Node(int to, long cost, long time){
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
        @Override
        public int compareTo(Node o) {
            int diff = Long.compare(this.cost, o.cost);
            return diff == 0 ? Long.compare(this.time, o.time) : diff;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        List<Node>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();

        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Integer.parseInt(st.nextToken());
            long time = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, cost, time));
            adjList[to].add(new Node(from, cost, time));
        }

        int count = 1;
        long[] answer = new long[]{0, 0};
        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(Node next : adjList[1]) pq.offer(next);

        while(count < N && !pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.to]) continue;
            answer[0] += cur.cost;
            answer[1] = Math.max(answer[1], cur.time);
            count++;
            visited[cur.to] = true;
            // System.out.println(cur.to+", "+cur.cost+", "+cur.time);

            for(Node next : adjList[cur.to]){
                if(visited[next.to]) continue;
                pq.offer(next);
            }
        }

        if(count == N) sb.append(answer[1]).append(" ").append(answer[0]);
        else sb.append(-1);

        // 정답 출력
        System.out.println(sb.toString());
    }
}
