package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17396
// - 다익스트라 : 0번쨰 분기점에서 (N-1) 분기점으로 가는 최소 시간 계산!
public class _26_Solution_1 {
    // 노드 클래스
    public static class Node implements Comparable<Node>{
        int vertex; // 분기점
        long time;  // 이동 시간
        public Node(int vertex, long time){
            this.vertex = vertex;
            this.time = time;
        }
        @Override
        public int hashCode() {
            return Objects.hash(vertex, time);
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node){
                Node o = (Node) obj;
                if(this.vertex == o.vertex && this.time == o.time) return true;
            }
            return false;
        }
        // 오름차순 정렬
        @Override
        public int compareTo(Node o) {
            long diff = this.time - o.time;
            if(diff > 0) return 1;
            else if(diff == 0) return 0;
            else return -1;
        }
        @Override
        public String toString(){
            return "[ " + this.vertex+", "+this.time+" ]";
        }
    }
    // 최대값
    public static long MAX = 10_000_000_001L;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 분기점의 수
        int M = Integer.parseInt(st.nextToken());   // 연결 개수

        // 시야 정보
        st = new StringTokenizer(in.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        // N-1 분기점은 무조건 보이는 것이므로 0으로 변경!
        A[N-1] = 0;

        // 인접 리스트 정보 초기화
        Map<Integer, Set<Node>> adjMap = new HashMap<>();
        for(int i = 0; i < N; i++) adjMap.put(i, new HashSet<>());

        // 인접 리스트 입력
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            // 어느 한 쪽이라도 적에게 보이는 경우 패스
            if(A[a] == 1 || A[b] == 1) continue;

            adjMap.get(a).add(new Node(b, t));
            adjMap.get(b).add(new Node(a, t));
        }

        // 다익스트라 계산
        long[] dijkstra = dijkstra(N, adjMap);
//        for(int i = 0; i < N; i++){
//            System.out.println(i+" >> "+adjMap.get(i));
//        }
//        System.out.println(Arrays.toString(A));
//        System.out.println(Arrays.toString(dijkstra));

        // 정답 반환
        // -
        sb.append((dijkstra[N-1] == MAX) ? -1 : dijkstra[N-1]);
        System.out.println(sb);
    }
    // 다익스트라 함수 : 0번 분기점을 초기 지점으로 설정하여 모든 분기점으로 가는 최소 시간 계산
    private static long[] dijkstra(int n, Map<Integer, Set<Node>> adjMap) {
        // 다익스트라 배열 초기화
        long[] result = new long[n];
        Arrays.fill(result, MAX);

        // 초기값 설정
        result[0] = 0;

        // 우선순위 큐 초기화
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 0번 분기점을 기준으로 이동 가능한 분기점 추가
        for(Node next : adjMap.get(0)) pq.add(next);

        while(!pq.isEmpty()){
            // 최소 시간인 분기점 반환
            Node cur = pq.poll();
//            System.out.println(cur.vertex +"----------------"+cur.time);

            // 새로운 분기점인 경우
            if(cur.time < result[cur.vertex]){
                // 시간 입력!
                result[cur.vertex] = cur.time;

                // 현재 분기점에서 연결된 다음 분기점 계산
                for(Node temp : adjMap.get(cur.vertex)){
                    // 이미 방문한 경우 패스
                    if(result[temp.vertex] != MAX) continue;
                    // 새로운 분기점 우선순위 큐에 추가
                    Node next = new Node(temp.vertex, temp.time + cur.time);
                    pq.add(next);
                }
            }
        }

        return result;
    }

}
