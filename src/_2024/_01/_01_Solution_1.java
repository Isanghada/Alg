package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16928
// - BFS를 통해 차례로 최소 이동 경로 계산
public class _01_Solution_1 {
    public static int[] POINT;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 게임 설정 정보
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 사다리 개수
        int M = Integer.parseInt(st.nextToken());   // 뱀 개수

        // 이동 Map 초기화
        POINT = new int[101];
        for(int num = 1; num <= 100; num++) POINT[num] = num;

        // 사다리 추가
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            POINT[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        // 뱀 추가
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            POINT[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        // 최소 이동 횟수 계산
        int answer = getMinCount();

        // 정답 입력
        sb.append(answer);
        System.out.println(sb);
    }
    //  최소 이동 횟수 계산 함수
    private static int getMinCount() {
        // map 초기화
        int[] map = new int[101];
        // - 10000으로 초기화
        Arrays.fill(map, 10000);
        // - 시작 위치 초기화
        map[1] = 0;

        // 시작 위치 덱에 추가
        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(1);

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 현재 번호 반환
            int cur = deque.pollFirst();

            // 이동 가능한 최대 번호 계산
            int limit = Math.min(cur+6, 100);
            // 다음 좌표 탐색
            for(int next = cur + 1; next <= limit; next++){
                // 새로운 정보 추가
                if(map[POINT[next]] == 10000){
                    map[POINT[next]] = map[cur]+1;
                    deque.offerLast(POINT[next]);
                }

                if(POINT[next] == 100) return map[next];
            }
        }
        // 최소 이동 횟수 반환
        return map[100];
    }
}
