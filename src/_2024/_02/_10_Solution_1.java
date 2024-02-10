package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/23284
// - 백트래킹 : 모든 경우 탐색!
public class _10_Solution_1 {
    public static int N;            // 정수 N
    public static int[] NUMBER;     // 수열 배열
    public static boolean[] ISUSED; // 정수 사용 여부 배열
    public static StringBuilder sb; // 출력문
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());    // 정수 입력
        // 수열 초기화
        NUMBER = new int[N];
        // 정수 사용 여부 초기화
        ISUSED = new boolean[N+1];

        // 수열 계산!
        sequence(0, 0);

        // 정답 반환
        System.out.println(sb);
    }

    // 수열 계산 함수 : 재귀를 통해 모든 경우 탐색!
    private static void sequence(int step, int next) {
        // 가능한 수열인 경우 : 출력문에 추가
        if(step == N){
            for(int idx = 0; idx < N; idx++) sb.append(NUMBER[idx]).append(" ");
            sb.append('\n');
        }else{
            // 모든 정수 확인
            for(int num = 1; num <= N; num++){
                // 이미 사용한 경우 패스
                if(ISUSED[num]) continue;

                // 현재 정수 num이 사용 불가능한 경우
                // - [1 -> 4 -> 2 -> 3]과 같은 경우 [4 -> 2 -> 3]으로 이어지는 경우가 불가능
                if(step > 0 && NUMBER[step-1] < num && num < next) break;

                // 현재 위치에 num 입력
                NUMBER[step] = num;
                // 사용 여부 체크
                ISUSED[num] = true;

                // next 이상인 경우! : next 변경
                if(next <= num) sequence(step+1, num+1);
                // next 미만인 경우 : next 유지
                else sequence(step+1, next);

                // 사용 여부 체크
                ISUSED[num] = false;
            }
        }
    }
}
