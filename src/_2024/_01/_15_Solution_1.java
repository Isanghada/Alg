package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17088
// - 브루투포스 : 모든 경우의 수 탐색
public class _15_Solution_1 {
    // N : 수열의 길이
    // ANSWER : 연산 횟수 최소값
    public static int N, ANSWER;
    // 수열
    public static int[] B;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 수열 길이 입력
        N = Integer.parseInt(in.readLine());
        // 수열 입력
        B = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 정답 초기화
        ANSWER = Integer.MAX_VALUE;

        // 수열 길이가 2 이하인 경우 등차수열이므로 0으로 변환
        if(N <= 2) ANSWER = 0;
        else{
            // 첫 번째 값의 연산
            for(int idx0 = -1; idx0 <= 1; idx0++){
                // 두 번째 값의 연산
                for(int idx1 = -1; idx1 <= 1; idx1++){
                    // 첫 번째, 두 번째 값을 선택하고 다음 연산 진행
                    getMinCount(2,
                            B[1]+idx1,
                            (B[0]+idx0) - (B[1]+idx1),
                            Math.abs(idx0) + Math.abs(idx1));
                }
            }
        }

        // 정답 출력
        // - 가능한 경우가 없는 경우 -1, 있는 경우 ANSWER 반환
        sb.append(ANSWER == Integer.MAX_VALUE ? -1 : ANSWER);
        System.out.println(sb);
    }
    // 브루트포스 : 재귀를 통해 모든 경우 탐색
    private static void getMinCount(int idx, int prev, int diff, int count) {
        // 모든 경우를 탐색한 경우 : 정답 최소값으로 갱신
        if(idx == B.length) ANSWER = Math.min(ANSWER, count);
        else{
            // 현재 값의 연산
            for(int cur = -1; cur <= 1; cur++){
                // 현재 값 계산
                int value = B[idx] + cur;
                // 공차가 같은 경우 : 다음 값 탐색
                // - 공차가 같은 경우 다른 연산은 진행하지 않아도 되므로 종료
                if(prev - value == diff){
                    getMinCount(idx+1, value, diff, count + Math.abs(cur));
                    break;
                }
            }
        }
    }
}
