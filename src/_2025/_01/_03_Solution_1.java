package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14002
// - DP : 최장 거리를 찾으며, 이전 위치까지 기억하도록 구현!
//        마지막 위치부터 경로를 탐색하여 출력
public class _03_Solution_1 {
    // 노드 클래스
    static class Node{
        int number; // 수열 값
        int count;  // 최장 부분 수열 길이
        int prev;   // 이전 경로
        public Node(){
            this(0);
        }
        public Node(int number){
            this.number = number;
            this.count=1;
            this.prev=0;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[ number=").append(this.number)
                    .append(", count=").append(this.count)
                    .append(", prev=").append(this.prev)
                    .append(" ]");
            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수열 길이
        int N = Integer.parseInt(in.readLine());

        // 수열 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        Node[] nodes = new Node[N+1];
        for(int i = 1; i <= N; i++) nodes[i] = new Node(Integer.parseInt(st.nextToken()));

        // 초기값 설정 : 1번 노드로 설정
        int maxIdx = 1;
        int maxCount = 1;
        // 2번 노드부터 모든 노드의 최장 길이 탐색
        for(int i = 2; i <= N; i++){
            for(int j = 1; j < i; j++){
                if(nodes[j].number < nodes[i].number &&
                        nodes[j].count >= nodes[i].count){
                    nodes[i].count = nodes[j].count+1;
                    nodes[i].prev = j;
                }
            }
            // 최장 부분 수열 갱신
            if(nodes[i].count > maxCount){
                maxIdx = i;
                maxCount = nodes[i].count;
            }
        }
//        System.out.println(Arrays.toString(nodes));
//        System.out.println(maxIdx+", "+maxCount);

        // 최장 부분 수열 탐색
        List<Integer> numberList = new ArrayList<>();
        do{
            numberList.add(nodes[maxIdx].number);
            maxIdx = nodes[maxIdx].prev;
        }while(maxIdx != 0);

        // 수열의 뒤부터 추가했기 때문에 reverse로 뒤집기
        Collections.reverse(numberList);

        // 최장 부분 수열 길이
        sb.append(maxCount).append("\n");
        // 부분 수열 출력
        for(int number : numberList) sb.append(number).append(" ");

        // 정답 반환
        System.out.println(sb);
    }
}
