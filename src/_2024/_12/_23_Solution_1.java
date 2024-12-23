package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22856
// - 순회 : 중위 순회, 유사 중위 순회의 마지막 방문 노드가 같으므로
//          중위 순회를 통해 마지막 노드를 찾고 유사 중위 순회 횟수 계산!
public class _23_Solution_1 {
    public static int LAST_NODE;    // 마지막 노드
    public static Tree[] NODES;     // 트리
    public static boolean FLAG;     // 마지막 노드 방문 플래그
    // - 중위 순회, 유사 중위 순회 경로 리스트
    public static List<Integer> IN_ORDER_LIST, SAME_IN_ORDER_LIST;
    // 트리 클래스
    public static class Tree{
        int left;
        int right;
        public Tree(int left, int right){
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 노드의 수
        int N = Integer.parseInt(in.readLine());

        // 트리 정보 입력
        StringTokenizer st = null;
        NODES = new Tree[N+1];
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(in.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            NODES[node] = new Tree(left, right);
        }

        // 중위 순회
        IN_ORDER_LIST = new ArrayList<>();
        inOrder(1);

        // 마지막 노드
        LAST_NODE = IN_ORDER_LIST.get(N-1);
        // 유사 중위 순회
        SAME_IN_ORDER_LIST = new ArrayList<>();
        sameInOrder(1);
//        System.out.println();
//        System.out.println(SAME_IN_ORDER_LIST);

        // 정답 출력
        // - 이동 횟수 출력
        sb.append(SAME_IN_ORDER_LIST.size()-1);
        System.out.println(sb);
    }

    private static void sameInOrder(int node) {
        SAME_IN_ORDER_LIST.add(node);
//        System.out.print(node+" -> ");

        Tree cur = NODES[node];
        if(cur.left != -1){
            sameInOrder(cur.left);
            if(FLAG) return;
            SAME_IN_ORDER_LIST.add(node);
        }

        if(cur.right != -1){
            sameInOrder(cur.right);
            if(FLAG) return;
            SAME_IN_ORDER_LIST.add(node);
        }

        if(node == LAST_NODE) FLAG = true;


    }

    private static void inOrder(int node) {
        Tree cur = NODES[node];

        if(cur.left != -1) inOrder(cur.left);
        IN_ORDER_LIST.add(node);
        if(cur.right != -1) inOrder(cur.right);
    }
}
