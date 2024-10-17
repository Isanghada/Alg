package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/
// - 크루스칼 : 최소 비용 간선부터 차례로 선택하며 최소 스패닝 트리 구축!
public class _17_Solution_1 {
    // 간선 클래스
    public static class Edge implements Comparable<Edge>{
        int a;  // 연결 노드
        int b;  // 연결 노드
        int cost;   // 비용
        public Edge(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
        // 비용 기준 오름차순 정렬
        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 행성의 수
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;

        // 간선 정보 입력
        // - (i, j) == (j, i)이므로 중복 간선은 추가하지 않음
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++) {
                String cost = st.nextToken();
                if(r < c) edges.add(new Edge(r, c, Integer.parseInt(cost)));
            }
        }

        // 루트 노드 초기화
        int[] parents = initParents(N);

        // 정답 초기화
        long answer = 0;
        // 선택 간선의 수
        int count = 1;

        // 모든 간선을 확인했거나 간선을 모두 선택한 경우 종료!
        while(!edges.isEmpty() && count < N){
            // 현재 간선
            Edge cur = edges.poll();
            // 순환이 생기지 않으면 연결!
            if(union(parents, cur.a, cur.b)){
                answer += cur.cost;
                count++;
            }
        }
        
        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }

    private static boolean union(int[] parents, int a, int b) {
        int parentA = find(parents, a);
        int parentB = find(parents, b);

        if(parentA == parentB) return false;
        parents[parentB] = parentA;
        return true;
    }

    private static int find(int[] parents, int a) {
        if(parents[a] == a) return a;
        else return parents[a] = find(parents, parents[a]);
    }

    private static int[] initParents(int n) {
        int[] parents = new int[n];
        for(int node = 1; node < n; node++) parents[node] = node;
        return parents;
    }
}
