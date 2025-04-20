package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/12834
// - 다익스트라 : KIST, 씨알푸드를 기준으로 다익스트라 계산
//                  모든 팀원들의 거리를 계산하여 정답 출력
public class _20_Solution_1 {
    // 노드 클래스
    static class Node implements Comparable<Node>{
        int end;        // 도착 좌표
        int distance;   // 거리
        public Node(int end, int distance){
            this.end = end;
            this.distance = distance;
        }
        // 거리 기준 오름차순 정렬
        @Override
        public int compareTo(Node n){
            return this.distance - n.distance;
        }
        @Override
        public String toString(){
            return "[ end="+this.end+", distance="+this.distance+" ]";
        }
    }
    // 최대값
    static final int MAX = 10_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 팀원의 수
        int V = Integer.parseInt(st.nextToken());   // 장소의 수
        int E = Integer.parseInt(st.nextToken());   // 도로의 수

        st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());   // KIST 좌표
        int B = Integer.parseInt(st.nextToken());   // 씨알푸드 좌표

        // 팀원 정보 입력
        int[] teams = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 인접 리스트 초기화
        List<Node>[] adjList = new List[V+1];
        for(int node = 1 ; node <= V; node++) adjList[node] = new ArrayList<>();

        // 도로 정보 입력
        while(E-- > 0){
            st = new StringTokenizer(in.readLine());
            int node1=  Integer.parseInt(st.nextToken());
            int node2=  Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            adjList[node1].add(new Node(node2, distance));
            adjList[node2].add(new Node(node1, distance));
        }

        int[] kistDijkstra = getDijkstra(adjList, A, V);    // KIST 기준 다익스트라
        int[] foodDijkstra = getDijkstra(adjList, B, V);    // 씨알푸드 기준 다익스트라

//        System.out.println(Arrays.toString(kistDijkstra));
//        System.out.println(Arrays.toString(foodDijkstra));

        // 정답 초기화
        int answer = 0;
        // 모든 팀원의 결과 계산
        // - 각 위치에 도달할 수 없는 경우 -1로 처리
        for(int team : teams){
            if(kistDijkstra[team] == MAX) answer -= 1;
            else answer += kistDijkstra[team];

            if(foodDijkstra[team] == MAX) answer -=1;
            else answer += foodDijkstra[team];
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString().trim());
    }

    private static int[] getDijkstra(List<Node>[] adjList, int start, int v) {
        int[] dijkstra = new int[v+1];
        Arrays.fill(dijkstra, MAX);
        dijkstra[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[v+1];

        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.end]) continue;

            dijkstra[cur.end] = cur.distance;
            visited[cur.end] = true;

            for(Node node : adjList[cur.end]){
                if(visited[node.end]) continue;

                Node next = new Node(node.end, cur.distance + node.distance);
                pq.offer(next);
            }
        }

        return dijkstra;
    }
}
