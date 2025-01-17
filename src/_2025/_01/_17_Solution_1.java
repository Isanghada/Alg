package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14950
// - 유니온-파인드 : 최소 스패닝 트리 알고리즘을 통해 최소 비용 계산
public class _17_Solution_1 {
    // 간선 클래스
    static class Edge implements Comparable<Edge>{
        int a;  // 연결 노드
        int b;  // 연결 노드
        int c;  // 비용
        public Edge(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
        // 비용 기준 오름차순 정렬
        @Override
        public int compareTo(Edge e){
            return this.c - e.c;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시의 수
        int M = Integer.parseInt(st.nextToken());   // 도로의 수
        int T = Integer.parseInt(st.nextToken());   // 추가 비용

        // 간선 정보 입력
        List<Edge> edgeList = new ArrayList<>();
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(A, B, C));
        }

        // 비용 기준 오름 차순 정렬
        Collections.sort(edgeList);

        int answer = 0;     // 정답 초기화
        int count = 1;
        int plusCost = 0;   // 추가 비용
        int[] parents = initParents(N); // 부모 노드 초기화

        for(Edge edge : edgeList){
            // a, b를 연결할 수 있다면 연결!
            if(union(edge.a, edge.b, parents)){
                // 비용 만큼 증가
                answer += edge.c + plusCost;
                count++;
                // 비용 증가!
                plusCost += T;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static boolean union(int a, int b, int[] parents) {
        if(a > b){
            int temp = a;
            a = b;
            b = temp;
        }

        int parentA = find(a, parents);
        int parentB = find(b, parents);
        if(parentA == parentB) return false;
        else {
            parents[parentB] = parentA;
            return true;
        }
    }

    private static int find(int node, int[] parents) {
        if(node != parents[node]) return parents[node] = find(parents[node], parents);
        return parents[node];
    }

    private static int[] initParents(int n) {
        int[] parents = new int[n+1];
        for(int i = 1; i <= n; i++) parents[i] = i;
        return parents;
    }
}
