package _2024._11;

import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/
// - BFS : 명령어에 따라 만들 수 있는 모든 경우 확인!
public class _18_Solution_1 {
    // 노드 클래스
    public static class Node{
        int num;    // 숫자
        String cmd; // 명령어
        public Node(int num, String cmd){
            this.num = num;
            this.cmd = cmd;
        }
    }
    // 최대값
    public static final int MAX = 10000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());   // 초기값
            int B = Integer.parseInt(st.nextToken());   // 목표값

            // bfs를 통해 명령어 확인
            sb.append(bfs(A, B)).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static String bfs(int a, int b) {
        Deque<Node> deque = new LinkedList<>();
        boolean[] visited = new boolean[MAX];

        // 초기값 설정
        deque.offerLast(new Node(a, ""));
        visited[a] = true;

        while(!deque.isEmpty()){
            // 노드 반환
            Node cur = deque.pollFirst();
            if(cur.num == b) return cur.cmd;

            // 다음 명령 결과!
            Node[] next = new Node[]{
                    new Node(commandD(cur.num), cur.cmd+"D"),
                    new Node(commandS(cur.num), cur.cmd+"S"),
                    new Node(commandL(cur.num), cur.cmd+"L"),
                    new Node(commandR(cur.num), cur.cmd+"R")
            };

            for(Node node : next){
                // 이미 방문한 경우 패스
                if(visited[node.num]) continue;

                // 새로운 노드 추가
                deque.offerLast(node);
                visited[node.num] = true;
            }
        }

        return null;
    }
    private static int commandD(int target) {
        return target*2 % 10000;
    }
    private static int commandS(int target) {
        return target == 0 ? 9999 : target-1;
    }
    private static int commandL(int target) {
        return (target % 1000) * 10 + (target / 1000);
    }
    private static int commandR(int target) {
        return (target % 10) * 1000 + (target / 10);
    }
}
