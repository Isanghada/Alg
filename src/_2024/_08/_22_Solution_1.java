package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/32069
// - BFS : 가로등 위치를 기준으로 너비 우선 탐색을 통해 정답 출력!
public class _22_Solution_1 {
    private static final int[] DELTA = new int[]{1, -1};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long L = Long.parseLong(st.nextToken());    // 도로 길이
        int N = Integer.parseInt(st.nextToken());   // 가로등 개수
        int K = Integer.parseInt(st.nextToken());   // 어두운 정도 개수!

        // 덱 초기화
        Deque<long[]> deque = new LinkedList<>();
        // 방문 정보 초기화
        Set<Long> visited = new HashSet<>();

        // 가로등 정보 추가!
        st = new StringTokenizer(in.readLine());
        while(N-- > 0){
            deque.offerLast(new long[]{Long.parseLong(st.nextToken()), 0});
            visited.add(deque.peekLast()[0]);
        }

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 어두운 정도 반환!
            long[] cur = deque.pollFirst();

            // 현재 값 출력
            sb.append(cur[1]).append("\n");
            // 모든 값을 출력했으면 종료!
            if(--K == 0) break;

            for(int d : DELTA){
                long[] next = new long[]{cur[0]+d, cur[1]+1};
                // 범위를 벗어나거나 이미 방문한 경우 패스!
                if(next[0] < 0 || next[0] > L || visited.contains(next[0])) continue;
                // 다음 어두운 정도 추가!
                deque.offerLast(next);
                visited.add(next[0]);
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}
