package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23793
// - 다익스트라 :
public class _21_Solution_1 {
    static class Node implements Comparable<Node>{
        int v;
        int w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Node n){
            return this.w - n.w;
        }
    }
    static final int MAX = 2_000_000_000;

    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node>[] adjList = new List[N+1];
        for(int n = 1; n <= N; n++) adjList[n] = new ArrayList<>();

        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            Node edge = new Node(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            adjList[u].add(edge);
        }

        st = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());

        long[] dijkstraX = dijkstra(x, adjList, y);
        long[] dijkstraY = dijkstra(y, adjList, 0);

//        System.out.println(Arrays.toString(dijkstraX));
//        System.out.println(Arrays.toString(dijkstraY));

        long[] answers = new long[]{dijkstraX[y]+dijkstraY[z], dijkstraX[z]};
        sb.append(answers[0] >= MAX ? -1 : answers[0]).append(" ")
                .append(answers[1] >= MAX ? -1 : answers[1]);

        // 정답 출력
        System.out.println(sb);
    }

    private static long[] dijkstra(int start, List<Node>[] adjList, int exception) {
        long[] dijkstra = new long[adjList.length];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(dijkstra, MAX);
        pq.offer(new Node(start, 0));
        dijkstra[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dijkstra[cur.v] < cur.w || cur.v == exception) continue;

            for(Node edge : adjList[cur.v]){
                Node next = new Node(edge.v, edge.w+cur.w);
                if(dijkstra[next.v] > next.w) {
                    dijkstra[next.v] = next.w;
                    pq.offer(next);
                }
            }
        }

        return dijkstra;
    }
}