package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14395
// - bfs : BFS로 모든 경우를 탐색하며 최소 경우 계산
public class _23_Solution_1 {
    // 연산 순서
    public static char[] DELTA = new char[] {'*', '+', '-', '/'};
    // 노드 클래스
    public static class Node{
        long num;           // 숫자
        String operation;   // 연산 순서
        public Node(long num, String operation){
            this.num = num;
            this.operation = operation;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long S = Long.parseLong(st.nextToken());    // 시작 숫자
        long T = Long.parseLong(st.nextToken());    // 타겟 숫자

        // 정답 출력
        // - BFS를 통해 최소 순서 계산
        sb.append(bfs(S, T));
        System.out.println(sb);
    }

    private static String bfs(long s, long t) {
        if(s == t) return String.valueOf(0);

        Deque<Node> deque = new LinkedList<>();
        Set<Long> set = new HashSet<>();

        deque.offerLast(new Node(s, ""));
        set.add(s);

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();

            if(cur.num == t) return cur.operation;
            for(char d : DELTA){
                Node next = new Node(cur.num, cur.operation+d);
                if(d == '*') next.num *= cur.num;
                else if(d == '+') next.num += cur.num;
                else if(d == '-') next.num -= cur.num;
                else if(cur.num != 0){
                    next.num /= cur.num;
                }
                if(set.contains(next.num)) continue;
                deque.offerLast(next);
                set.add(next.num);
            }
        }
        return String.valueOf(-1);
    }
}
