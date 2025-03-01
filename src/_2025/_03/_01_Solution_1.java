package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/31235
// - 그리디 : 가능한 (i, j) 쌍에 대해 차례로 탐색
public class _01_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수열 길이
        int N = Integer.parseInt(in.readLine());
        // 수열 정보
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 초기값 설정
        int k = 1;          // (i, j) 범위(=> 길이)
        int answer = 1;     // 정답
        int prev = A[0];    // 이전 최대값

        // 가능한 범위 차례로 계산!!
        // - 가능한 k의 값을 구하며 최대값 선택!
        for(int n = 1; n < N; n++){
            // prev가 큰 값일 경우 prev를 계속 선택할 수 있도록 k 증가
            if(A[n] < prev) k++;
            // 아닐 경우 : prev 갱신 및 k 초기화
            else{
                prev = A[n];
                k = 1;
            }
            answer = Math.max(answer, k);
        }

        // 정답 입력
        sb.append(answer);
        System.out.println(sb);
    }
}
