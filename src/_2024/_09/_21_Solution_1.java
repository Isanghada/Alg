package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1327
// - BFS : 수열을 문자열로 구성하여 매 변경마다 Set에 저장하고, 오름차순 결과가 되는지 확인!
public class _21_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 수열 크기
        int K = Integer.parseInt(st.nextToken());   // 변경 길이

        // 수열 입력
        st = new StringTokenizer(in.readLine());
        StringBuilder s = new StringBuilder();
        while(st.hasMoreTokens()) s.append(st.nextToken());
        String number = s.toString();

        // 오름차순 결과
        StringBuilder target = new StringBuilder();
        for(int num = 1; num <= N; num++) target.append(num);

        // 정답 출력
        // - BFS를 통해 최소 선택 반환
        sb.append(bfs(number, target.toString(), N, K));
        System.out.println(sb);
    }

    private static class Node{
        String number;
        int count;
        public Node(String number, int count){
            this.number = number;
            this.count = count;
        }
    }
    private static int bfs(String number, String target, int n, int k) {
        // 덱 초기화
        Deque<Node> deque = new LinkedList<>();
        // 방문 Set 초기화
        Set<String> visited = new HashSet<>();

        // 초기값 설정
        deque.offerLast(new Node(number, 0));
        visited.add(number);

        // 변경 인덱스 제한값
        final int limit = n - k;
        while(!deque.isEmpty()){
            // 값 반환
            Node cur = deque.pollFirst();
            // 오름차순인 경우 count 반환
            if(cur.number.equals(target)) return cur.count;

            // 가능한 모든 인덱스를 선택하여 값 변경!
            for(int i = 0; i <= limit; i++){
                char[] numArr = cur.number.toCharArray();
                swap(numArr, i, k);
                String next = String.valueOf(numArr);

                // 이미 방문한 경우 패스
                if(visited.contains(next)) continue;
                
                // 새로운 값 추가
                deque.offerLast(new Node(next, cur.count+1));
                visited.add(next);
            }
        }

        return -1;
    }

    private static void swap(char[] numArr, int i, int k) {
        k--;
        for(; k > 0; k -= 2, i++){
            char temp = numArr[i];
            numArr[i] = numArr[i+k];
            numArr[i+k] = temp;
        }
    }
}