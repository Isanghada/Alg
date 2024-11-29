package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/18513
// - BFS : 샘물을 기준으로 BFS를 통해 최소 불행도의 합 계산
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 샘물의 수
        int K = Integer.parseInt(st.nextToken());   // 집의 수

        // 샘물 정보
        int[] waters = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 정답 반환
        // - bfs를 통해 최소 불행도의 합 계산
        sb.append(bfs(waters, K));
        System.out.println(sb);
    }

    private static long bfs(int[] waters, int k) {
        // 불행도의 합
        long result = 0L;
        
        // 덱 초기화
        Deque<Integer> deque = new LinkedList<>();
        // 방문 체크 초기화
        Set<Integer> visited = new HashSet<>();

        // 초기값 설정 : waters
        for(int water : waters){
            deque.offerLast(water);
            visited.add(water);
        }
    
        // 불행도 초기화
        long unhappy = 0L;
        while(true){
            // 현재 불행도 범위!
            int size = deque.size();
            while(size-- > 0){
                // 불행도 증가
                result += unhappy;
                // 집을 설치할 수 있는 경우 k 감소
                if(unhappy != 0) k--;
                // 모든 집을 설치한 경우 불행도의 합 반환
                if(k == 0) return result;

                // 현재 위치
                int cur = deque.pollFirst();

//                System.out.println(cur+", "+unhappy+", "+result);

                // 다음 위치 계산
                for(int next : new int[]{cur-1, cur+1}){
                    // 방문한 경우 패스
                    if(visited.contains(next)) continue;
                    // 덱에 추가.
                    deque.offerLast(next);
                    // set에 추가
                    visited.add(next);
                }
            }
            unhappy++;
        }
    }
}