package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16397
// - BFS : 버튼 A, 버튼 B를 누르는 경우를 모두 탐색!
public class _24_Solution_1 {
    // 최대값
    private static final int MAX = 99_999;
    // 노드 클래스
    private static class Node{
        int num;    // 숫자
        int count;  // 버튼 횟수
        public Node(int num, int count){
            this.num = num;
            this.count = count;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 초기 숫자
        int T = Integer.parseInt(st.nextToken());   // 최대 횟수
        int G = Integer.parseInt(st.nextToken());   // 목표 숫자

        // 정답 출력
        // - bfs를 통해 최소값 탐색
        sb.append(bfs(N, T, G));
        System.out.println(sb);
    }
    private static String bfs(int n, int t, int g) {
        Deque<Node> deque = new LinkedList<>();
        boolean[] used = new boolean[MAX+1];

        deque.offerLast(new Node(n, 0));
        used[n] = true;

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();
//            System.out.println(cur.num+", "+cur.count+"---");
            // 목표 숫자인 경우 횟수 반환
            if(cur.num == g) return String.valueOf(cur.count);

            // 최대 횟수 미만인 경우 다음 숫자 탐색
            if(cur.count < t){
                for(int i = 0; i < 2; i++){
                    Node next = new Node(cur.num, cur.count+1);
                    // A 버튼인 경우 +1
                    if(i == 0) next.num += 1;
                    // B 버튼인 경우 *2!
                    else next.num *= 2;

                    // 최대값 초과인 경우 패스
                    if(next.num > MAX) continue;

                    // B 버튼인 경우 높은 자리수의 수 -1
                    if(i == 1 && next.num != 0) {
                        int minus = 10_000;
                        while(next.num / minus == 0) minus /= 10;
                        next.num -= minus;
                    }

                    // 사용되지 않은 숫자인 경우 추가!
                    if(!used[next.num]){
                        deque.offerLast(next);
                        used[next.num] = true;
                    }

                }
            }
        }

        return "ANG";
    }
}
