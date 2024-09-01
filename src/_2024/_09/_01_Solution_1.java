package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1922
// - 크루스칼 : 유니온-파인드를 통해 각 정점의 부모 정점을 갱신하며 순환 확인!
//              간선을 오름차순 정렬하여 비용이 낮은 간선부터 체크!
public class _01_Solution_1 {
    // 간선 클래스
    public static class Edge implements Comparable<Edge>{
        int a;  // 컴퓨터
        int b;  // 컴퓨터
        int c;  // 연결 비용
        public Edge(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
        // 연결 비용 기준 오름차순 정렬
        @Override
        public int compareTo(Edge e){ return this.c - e.c; }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());    // 컴퓨터의 수
        int M = Integer.parseInt(in.readLine());    // 선의 수

        // 연결 정보 입력
        StringTokenizer st = null;
        PriorityQueue<Edge> edgePq = new PriorityQueue<>();
        while(M-- > 0) {
            st = new StringTokenizer(in.readLine());
            edgePq.offer(new Edge(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
                    ));
        }

        // 부모 정점 초기화
        int[] parents = initParent(N);

        int count = 1;  // 연결한 선의 수
        int answer = 0; // 최소 연결 비용
        while(count < N){
            Edge cur = edgePq.poll();
            // 순환이 발생하지 않으면 해당 간선 연결!
            if(union(parents, cur.a, cur.b)){
//                System.out.println(cur.a+", "+cur.b+", "+cur.c);
                answer += cur.c;
                count++;
            }
        }

        // 정답 입력
        sb.append(answer);
        System.out.println(sb);
    }
    // 부모 정점 초기화 함수 : 자기자신을 부모로 가지도록 설정
    private static int[] initParent(int n) {
        int[] parents = new int[n+1];
        for(int i = 1; i <= n; i++) parents[i] = i;
        return parents;
    }
    // 루트 탐색 함수 : target의 루트를 재귀로 찾는 함수
    public static int find(int[] parents, int target){
        // 자기자신을 가리키면 해당 값 반환
        if(parents[target] == target) return target;
        // 자기자신을 가리키지 않으면 루트를 찾아 반환
        else return parents[target] = find(parents, parents[target]);
    }
    // 유니온 함수 : a, b를 연결했을 때 순환이 발생하지 않으면 합친다!
    public static boolean union(int[] parents, int a, int b){
        if(a > b){
            int temp = a;
            a = b;
            b = temp;
        }
        // 부모가 같은 경우 순환이 발생하므로 false 반환
        if(find(parents, a) == find(parents, b)) return false;
        // 부모가 다른 경우 연결 후 true 반환
        else{
            // b의 부모 갱신!
            parents[parents[b]] = parents[a];
            return true;
        }
    }
}
