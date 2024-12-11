package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20955
// - 크루스칼 : 트리가 되기 위한 시냅스(간선)의 개수는 '(뉴련(정점)의 수)-1개'
//              크루스칼을 통해 최소 신장 트리를 구하며, 추가 및 삭제해야하는 시냅스의 수 계산
public class _11_Solution_1 {
    // 간선 클래스
    public static class Edge{
        int u;  // 연결 노드
        int v;  // 연결 노드
        public Edge(int u, int v){
            this.u = u;
            this.v = v;
        }
    }

    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 뉴련(정점)의 수
        int M = Integer.parseInt(st.nextToken());   // 시냅스(간선)의 수

        // 시냅스 리스트 : 가중치 1인 양방향 그래프이므로 리스트에 그대로 저장
        List<Edge> edgeList = new ArrayList<>();
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(u > v){
                int temp = u;
                u = v;
                v = temp;
            }

            edgeList.add(new Edge(u, v));
        }

        // 부모 정점 초기화
        int[] parents = initParents(N);

        // 삭제한 시냅스의 수
        int countOfRemove = 0;
        // 연결한 시냅스의 수
        int countOfEdge = 0;

        // 모든 시냅스(간선) 탐색)
        for(Edge e : edgeList){
            // 순환이 생기지 않는 경우 연결
            if(union(parents, e.u, e.v)) countOfEdge++;
            // 순환이 생기는 경우 삭제
            else countOfRemove++;
        }

        // 연산 횟수 : 삭제 횟수 + 추가 횟수
        // - 추가 횟수 : 'N-1개'의 간선을 가지도록 계산
        int answer = countOfRemove + (N - countOfEdge - 1);

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }

    private static boolean union(int[] parents, int u, int v) {
        int parentU = find(parents, u);
        int parentV = find(parents, v);

        if(parentU == parentV) return false;
        else {
            parents[parentV] = parentU;
            return true;
        }
    }

    private static int find(int[] parents, int target) {
        if(parents[target] == target) return target;
        return parents[target] = find(parents, parents[target]);
    }

    private static int[] initParents(int n) {
        int[] parents = new int[n+1];
        for(int c = 1; c <= n; c++) parents[c] = c;
        return parents;
    }
}
