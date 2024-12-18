package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/31963
// - 그리디 : log를 통해 필요한 연산 횟수 계산!
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수열 길이
        int N = Integer.parseInt(in.readLine());
        // 수열 정보
        long[] A = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        // 정답 : 연산 횟수
        long answer = 0;
        // 이전 값의 연산 횟수
        long prev = 0;
        for(int i = 1; i < N; i++){
            // log를 통해 필요한 연산 횟수 계산
            double count = (long) Math.ceil(Math.log(A[i-1] / (double)A[i]) / Math.log(2)) + prev;

            if(count > 0){
                long c = Math.max(0, (long)count);
                answer += c;
                prev = c;
            }else prev = 0;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
