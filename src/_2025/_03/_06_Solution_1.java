package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20007
// - 1. 떡은 한번에 하나씩만 들고 갈 수 있다!
// - 2. 거리가 가까운 집부터 방문한다!
// - 다익스트라 : 성현이의 집(Y)를 기준을 다른 집에 방문하는 최소 거리 계산! -> 다익스트라
//                다익스트라 결과를 활용해 최소 일 계산
public class _06_Solution_1 {
    //
    static class Edge implements Comparable<Edge>{
        int end;
        int d;
        public Edge(int end, int d){
            this.end = end;
            this.d = d;
        }
        @Override
        public int compareTo(Edge e){
            return this.d - e.d;
        }
    }
    static final int MAX = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        List<Edge>[] edgeList = new List[N];
        for(int n = 0; n < N; n++) edgeList[n] = new ArrayList<>();

        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edgeList[A].add(new Edge(B, C));
            edgeList[B].add(new Edge(A, C));
        }

        int[] dijkstra = getDijkstra(edgeList, N, Y);

        // 정답 출력
        sb.append(getMinDay(dijkstra, N, X));
        System.out.println(sb);
    }

    private static int getMinDay(int[] dijkstra, int n, int x) {
        Arrays.sort(dijkstra);
        if(dijkstra[n-1] * 2 > x) return -1;

        int day = 0, idx = 0, d = 0;
        while(idx < n){
            while(idx < n && (d + dijkstra[idx] * 2) <= x){
                d += dijkstra[idx] * 2;
                idx++;
            }
            day++;
            d = 0;
        }

        return day;
    }

    private static int[] getDijkstra(List<Edge>[] edgeList, int n, int start) {
        int[] dijkstra = new int[n];
        Arrays.fill(dijkstra, MAX);
        dijkstra[start] = 0;

        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        pq.offer(new Edge(start, 0));
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(visited[cur.end]) continue;

            visited[cur.end] = true;
            for(Edge e : edgeList[cur.end]){
                if(visited[e.end]) continue;
                Edge next = new Edge(e.end, cur.d + e.d);
                if(dijkstra[next.end] > next.d){
                    dijkstra[next.end] = next.d;
                    pq.offer(next);
                }
            }
        }

        return dijkstra;
    }

}
