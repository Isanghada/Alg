package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/
// - 유니온-파인드 : 각 테스트 케이스별 최소 스패닝 트리 계산!
public class _13_Solution_1 {
    // 간선 클래스
    public static class Edge implements Comparable<Edge>{
        int node1;  // 노드
        int node2;  // 노드
        int cost;   // 연결 비용
        public Edge(int node1, int node2, int cost){
            this.node1 =node1;
            this.node2 =node2;
            this.cost =cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    public static int[] PARENTS;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int R = Integer.parseInt(st.nextToken());   // 행 크기
            int C = Integer.parseInt(st.nextToken());   // 열 크기

            // 간선 리스트
            List<Edge> edgeList = new ArrayList<>();

            // 각 행의 간선 추가!
            int idx = 1;
            for(int r = 0; r < R; r++){
                st = new StringTokenizer(in.readLine());
                while(st.hasMoreTokens()){
//                    System.out.println(idx+", "+(idx+1));
                    edgeList.add(new Edge(idx, idx+1, Integer.parseInt(st.nextToken())));
                    idx++;
                }
                idx++;
            }

            // 각 열의 간선 추가!
            idx = 1;
            for(int r = 1; r < R; r++){
                st = new StringTokenizer(in.readLine());
                while(st.hasMoreTokens()){
                    edgeList.add(new Edge(idx, idx+C, Integer.parseInt(st.nextToken())));
                    idx++;
                }
            }

            // 간선 정렬
            Collections.sort(edgeList);

            // 비용 총합
            int total = 0;
            // 남은 간선 개수
            int edgeSize = R * C-1;
            // 루트 노드 초기화
            PARENTS = new int[R * C + 1];
            for(int i = 1; i < PARENTS.length; i++) PARENTS[i] = i;

            // 간선 선택!
            for(Edge edge : edgeList){
                // 순환이 생기지 않으면 연결!
                if(union(edge.node1, edge.node2)){
                    total += edge.cost;
                    edgeSize--;
                    // 모든 간선을 선택한 경우 종료
                    if(edgeSize == 0) break;
                }
            }

            // 비용 출력!
            sb.append(total).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static boolean union(int a, int b) {
        if(find(a) == find(b)) return false;
        PARENTS[PARENTS[b]] = PARENTS[a];

        return true;
    }

    private static int find(int target) {
        if(PARENTS[target] == target) return target;
        return PARENTS[target] = find(PARENTS[target]);
    }
}
