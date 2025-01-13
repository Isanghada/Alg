package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/5639
// - 그래프 : 전위 순회 결과에 따라 트리를 구성하고, 후위 순회 출력
public class _13_Solution_1 {
    // 노드 클래스
    static class Node{
        int num;            // 숫자
        Node left, right;   // 왼쪽, 오른쪽 자식 노드
        public Node(int num){
            this.num = num;
            this.left = null;
            this.right = null;
        }
        public Node(int num, Node left, Node right){
            this(num);
            this.left = left;
            this.right = right;
        }
        public void insert(int num){
            // - 같은 키(숫자)인 경우는 없으므로 같은 경우는 제외~
            // 현재 숫자보다 큰 경우 오른쪽으로 배치
            if(this.num < num){
                if(this.right ==null) this.right = new Node(num);
                else this.right.insert(num);
            // 현재 숫자보다 작은 경우 왼쪽으로 배치
            }else{
                if(this.left ==null) this.left = new Node(num);
                else this.left.insert(num);
            }
        }
    }
    static StringBuilder ANSWER;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 루트 설정
        Node root = new Node(Integer.parseInt(in.readLine()));

        while(true){
            String input = in.readLine();
            // 입력 끝인지 확인
            if(input == null || input.equals("")) break;
            // 입력 숫자에 따라 배치!
            root.insert(Integer.parseInt(input));
        }

        // 정답 초기화
        ANSWER = new StringBuilder();
        // 후위 순회 진행
        postOrder(root);

        // 정답 반환
        sb.append(ANSWER.toString());
        System.out.println(sb);
    }

    private static void postOrder(Node node) {
        if(node.left != null) postOrder(node.left);
        if(node.right != null) postOrder(node.right);
        ANSWER.append(node.num).append("\n");
    }
}
