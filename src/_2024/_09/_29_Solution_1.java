package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1504
// - 크루스칼 : MST를 통해 최소 스패닝 트리를 만들고 전체 비용에서 최소 비용을 제외!
public class _29_Solution_1 {
    // 간선 클래스
    public static class Edge implements Comparable<Edge>{
        int x;  // 이동 정보
        int y;  // 이동 정보
        int z;  // 거리
        public Edge(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
        @Override
        public int compareTo(Edge e){
            return this.z - e.z;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[ ")
                    .append(this.x).append(", ")
                    .append(this.y).append(", ")
                    .append(this.z).append(" ]");
            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        while(true){
            st = new StringTokenizer(in.readLine());
            int M = Integer.parseInt(st.nextToken());   // 집의 수
            int N = Integer.parseInt(st.nextToken());   // 간선의 수

            // 0, 0인 경우 종료
            if(M == 0 && N == 0) break;

            // 루트 노드 초기화
            int[] parents = initParents(M);

            // 전체 비용
            int total = 0;
            
            // 간선 정보 입력
            Edge[] edges = new Edge[N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(in.readLine());
                edges[i] = new Edge(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                );
                // 전체 비용 증가
                total += edges[i].z;
            }

            // 간선 정보 정렬 : z기준 오름차순
            Arrays.sort(edges);

            // 크루스칼 알고리즘
            // - 유니온-파인드를 통해 순환이 생기지 않도록 최소 스패닝 트리 생성!
            for(Edge edge : edges){
                if(union(parents, edge.x, edge.y)) {
//                    System.out.println(edge);
                    // 사용하는 간선일 경우 total 감소
                    total -= edge.z;
                }
            }

            sb.append(total).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
    // 유니온 함수
    private static boolean union(int[] parents, int x, int y) {
        if(find(parents, x) == find(parents, y)) return false;
        else{
            if(x < y) parents[parents[x]] = parents[y];
            else parents[parents[y]] = parents[x];
            return true;
        }
    }

    // 파인드 함수
    private static int find(int[] parents, int target) {
        if(parents[target] == target) return target;
        else return parents[target] = find(parents, parents[target]);
    }

    // 루트 노드 초기화 함수
    private static int[] initParents(int m) {
        int[] parents = new int[m];
        for(int node = 1; node < m; node++) parents[node] = node;
        return parents;
    }
}