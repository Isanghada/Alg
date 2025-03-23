package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/4994
// - BFS : 나머지가 같은 경우 어떤 숫자를 더해도 똑같은 나머지가 계산!
//         나머지로 값을 체크하며 가능한 모든 경우 탐색
public class _23_Solution_1 {
    // 노드 클래스
    static class Node{
        int mod;    // 나머지
        String num; // 숫자
        public Node(int mod, String num){
            this.mod = mod;
            this.num = num;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            // 정수 N!
            int N = Integer.parseInt(in.readLine());
            if(N == 0) break;

            if(N == 1) sb.append(1).append("\n");
            else{
                // 덱 초기화
                Deque<Node> deque = new LinkedList<>();
                // 방문 배열 초기화
                boolean[] visited = new boolean[200];

                // 초기값 설정
                deque.offerLast(new Node(1, "1"));
                visited[1] = true;
                while(!deque.isEmpty()){
                    Node cur = deque.pollFirst();
                    int mod = cur.mod;
                    String num = cur.num;

                    // N으로 나누어지는 경우 출력 추가 후 종료
                    if(mod == 0){
                        sb.append(num).append("\n");
                        break;
                    }

                    int nextMod = (mod * 10) % N;
                    if(!visited[nextMod]){
                        deque.offerLast(new Node(nextMod, num+"0"));
                        visited[nextMod] = true;
                    }

                    nextMod = (mod * 10 +1) % N;
                    if(!visited[nextMod]){
                        deque.offerLast(new Node(nextMod, num+"1"));
                        visited[nextMod] = true;
                    }
                }
            }
        }

        // 정답 출력
        System.out.println(sb.toString().trim());
    }
}
