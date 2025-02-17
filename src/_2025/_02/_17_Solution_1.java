package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2405
// - 그리디 : 정렬 후 가능한 모든 경우 체크
//            - 중위값, 평균값의 차이를 가장 크게 하려면
//              가장 큰 값과 중위값 혹은 가장 작은 값과 중위값이 연속되어야한다.
//              따라서, 가장 큰 값 고정, 가장 작은 값 고정 후 남은 부분을 선택하여 계산
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 배열 길이
        int N = Integer.parseInt(in.readLine());
        
        // 배열 정보
        int[] A = new int[N];
        for(int n = 0; n < N; n++) A[n] = Integer.parseInt(in.readLine());

        // 정렬 : 오름차순
        Arrays.sort(A);

        int answer = 0;
        for(int i = 0; i < N-2; i++){
            // 가장 큰 값 고정 후 계산
            answer = Math.max(answer, differceValue(A[i], A[i+1], A[N-1]));
            // 가장 작은 값 고정 후 계산
            answer = Math.max(answer, differceValue(A[0], A[N-2-i], A[N-1-i]));
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // 차이값 : |(중위값 - 평균값) * 3| = |3중위값 - 3평균값| = |3b - (a+b+c)| = |a - 2b + c|
    // - 중위값 : a, b, c로 정렬 되어 있으므로 b
    // - 평균값 : (a + b + c) / 3
    private static int differceValue(int a, int b, int c) {
        return Math.abs(a - 2*b + c);
    }
}
