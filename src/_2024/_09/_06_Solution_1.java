package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1647
// - 크루스칼 : 크루스칼 알고리즘을 통해 최소 스패닝 트리를 만들고
//              마지막에 추가되는 집만 마을에서 분리!
public class _06_Solution_1 {
    // 도로 클래스
    public static class Edge implements Comparable<Edge>{
        int start;
        int end;    // 도착점
        int cost;   // 비용
        public Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
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
        System.setIn(new FileInputStream("src/_2024/_09/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st= new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 마을의 수
        int M = Integer.parseInt(st.nextToken());   // 도로의 수

        // 도로 정보 입력
        List<Edge> adjList =new ArrayList<>();
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList.add(new Edge(a, b, c));
        }

        Collections.sort(adjList);
        int[] parents = initParents(N);

        int answer = 0;
        if(N > 2){
            for(Edge edge : adjList){
                if(union(parents, edge.start, edge.end)) {
                    answer += edge.cost;
                    if(--N == 2) break;
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static boolean union(int[] parents, int start, int end) {
        if(find(parents, start) == find(parents, end)) return false;
        else{
            parents[parents[end]] = parents[start];
            return true;
        }
    }

    private static int find(int[] parents, int target) {
        if(parents[target] == target) return target;
        else return parents[target] = find(parents, parents[target]);
    }

    private static int[] initParents(int n) {
        int[] parents = new int[n+1];
        for(int i = 0; i <= n; i++) parents[i] = i;
        return parents;
    }
}
