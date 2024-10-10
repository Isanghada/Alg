package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21924
// - 유니온-파인드 : 모든 간선을 연결한 비용에서 최소로 연결하면서 해당 비용만큼 감소!
//                   연결한 간선이 부족한 경우 -1, 모두 연결한 경우 남은 비용만큼 절약!
public class _10_Solution_1 {
    // 간선 클래스
    public static class Edge implements Comparable<Edge>{
        int a;  // 건물!
        int b;  // 건물!
        int c;  // 비용
        public Edge(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
        // 비용 기준 오름차순 정렬!
        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }
    // 루트 노드 배열!
    public static int[] PARENTS;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시의 수
        int M = Integer.parseInt(st.nextToken());   // 간선의 수

        // 전체 간선 비용
        long total = 0;
        // 우선순위 큐 초기화
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // 간선 정보 추가
        // 간선 정보 추가
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            total += c;
            pq.offer(new Edge(a, b, c));
        }

        // 부모 노드 초기화
        PARENTS = new int[N+1];
        for(int city = 1; city <= N; city++) PARENTS[city] = city;
        N--;

        // 연결한 간선의 수
        int count = 0;
        while(!pq.isEmpty() && count < N){
            // 간선 정보
            Edge cur = pq.poll();
            // 연결이 가능한 경우 정보 갱신!
            if(union(cur.a, cur.b)){
                total -= cur.c;
                count++;
            }
        }

        // 정답 출력
        sb.append(count == N ? total : -1);
        System.out.println(sb.toString());
    }

    // union 함수 : a, b를 연결하는 간선이 가능한 경우 true, 불가능한 경우 false 반환
    private static boolean union(int a, int b) {
        if(a > b){
            int temp = b;
            b = a;
            a = temp;
        }

        int aParent = find(a);
        int bParent = find(b);
        if(aParent == bParent) return false;
        else{
            PARENTS[aParent] = bParent;
            return true;
        }
    }

    // find 함수 : target의 루트 노드를 찾는 함수!
    private static int find(int target) {
        // 자기자신인 경우 target 반환
        if(PARENTS[target] == target) return target;
        // 그렇지 않은 경우 재귀를 통해 루트 탐색 후 반환
        else return PARENTS[target] = find(PARENTS[target]);
    }
}
