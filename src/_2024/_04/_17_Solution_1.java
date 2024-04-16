package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15681
// - 트리 : DFS, BFS를 통해 정점의 수 계산
public class _17_Solution_1 {
    // 노드 클래스
    public static class Node{
        public int parent;  // 부모 정점
        public Set<Integer> child;  // 자식 정점
        public int count;   // 현재 노드를 루트로 할 때 정점의 수
        public Node(int parent){
            this.parent = parent;
            this.count = 0;
            this.child = new HashSet<>();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 정점의 수
        int R = Integer.parseInt(st.nextToken());   // 루트 번호
        int Q = Integer.parseInt(st.nextToken());   // 질의 개수

        // 노드 초기화 : 자기자신을 부모로 가지도록 설정
        // - 부모가 자기자신인 경우는 루트 노드 하나 뿐!
        Node[] tree = new Node[N+1];
        for(int node = 1; node <= N; node++) tree[node] = new Node(node);

        // 노드 정보 입력 : 초기에는 부모-자식 관계를 모르기 때문에 양방향으로 연결
        for(int i = 1; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].child.add(v);
            tree[v].child.add(u);
        }

        // 루트 기준으로 트리 탐색 : 재귀 DFS로 진행(하는 김에 각 노드를 루트로 가질 때 정점 수도 계산)
        makeTree(tree, R, R);

        // 질의에 대한 답 출력 : 노드별로 구한 정점의 수를 이용해 질의에 대한 답변 진행
        while(Q-- > 0){
            int q = Integer.parseInt(in.readLine());
            sb.append(tree[q].count).append("\n");
        }
        System.out.println(sb.toString());
    }
    // 트리 생성 함수 : 양방향으로 연결된 형태에서 트리로 변경
    // - 부모-자신 관계 확립
    public static void makeTree(Node[] tree, int cur, int parent){
        // cur 노드의 부모를 parent로 변경
        tree[cur].parent = parent;
        // 자식 노드에 parent가 있는 경우 삭제
        tree[cur].child.remove(parent);
        // cur 노드를 루트로 가질 때 정점의 수 1로 초기화 : 자기자신은 무조건 포함되므로!
        tree[cur].count = 1;

        // DFS형태로 리프 노드까지 진행!
        for(int next : tree[cur].child) {
            // cur을 부모로 가지는 경우 모두 진행
            makeTree(tree, next, cur);
            // 자식 노드를 루트로 가질 떄 정점의 수 만큼 증가!
            tree[cur].count += tree[next].count;
        }
    }
}
