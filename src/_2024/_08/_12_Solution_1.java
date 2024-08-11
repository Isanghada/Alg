package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// https://www.acmicpc.net/problem/18112
// - BFS : 너비 우선 탐색을 통해 목표값에 도달하는 최소 횟수 계산!
public class _12_Solution_1 {
    static final int LIMIT = 1 << 10;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 시작 값
        int start = Integer.parseInt(in.readLine(), 2);
        // 목표 값
        int target = Integer.parseInt(in.readLine(), 2);

        // 정답 출력
        // - BFS를 통해 최소 횟수 계산!
        sb.append(bfs(start, target));
        System.out.println(sb.toString());
    }

    private static int bfs(int start, int target) {
        // 값이 동일한 경우 0 반환
        if(start == target) return 0;

        // 횟수
        int count = 0;

        // 덱 초기화
        Deque<int[]> deque =new LinkedList<>();
        // 방문 배열 초기화
        boolean[] visited = new boolean[LIMIT];

        // 초기값 설정 : start를 초기값으로 추가!
        deque.offerLast(new int[]{start, 0});
        visited[start] = true;
        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 값 정보 반환!
            int[] cur = deque.pollFirst();
            // 목표 값인 경우 횟수 입력 후 종료
            if(cur[0] == target){
                count = cur[1];
                break;
            }
            // 가능한 모든 다음 값 탐색!
            for(int next : getNextNumber(cur[0])){
                // 이미 체크한 경우 패스
                if(visited[next]) continue;
                // 새로운 값 추가!
                visited[next] = true;
                deque.offerLast(new int[]{next, cur[1]+1});
            }
        }
        return count;
    }

    private static List<Integer> getNextNumber(int n) {
        List<Integer> nextList = new ArrayList<>();
        // 1을 더하는 경우
        if(n+1 < LIMIT) nextList.add(n+1);
        if(n != 0){
            // 1을 빼는 경우
            nextList.add(n-1);

            // 최상위 값을 제외한 모든 비트를 반전하는 경우
            int bit = 0;
            int msd = Integer.highestOneBit(n);
            while((1 << bit) != msd){
                int next = n^(1 << bit++);
                if(next < LIMIT) nextList.add(next);
            }
        }
        return nextList;
    }
}
