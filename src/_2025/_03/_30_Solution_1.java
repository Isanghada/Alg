package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/4320
// - 수학 : 제곱을 통해 최대 p 계산
//          x^(1/2) => 2 제곱근, x^(1/3) => 3 제곱근, ...
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            // 목표
            long X = Long.parseLong(in.readLine());
            // 0인 경우 종료
            if(X == 0) break;

            // 음수인 경우 양수로 변환
            long absX = Math.abs(X);
            // 양수 : 모든 제곱근이 후보 => 1씩 감소
            // 음수 : 홀수 제곱근이 후보 => 2씩 감소
            int type = X > 0 ? 1 : 2;

            // 정답 초기화
            int answer = 1;
            // X가 int(32bit) 범위이므로 31부터 계산
            for(int p = 31; p > 1; p -= type){
                // p 제곱근 계산
                long root = Math.round(Math.pow(absX, 1.0 / p));

                // 제곱하여 목표와 동일한지 비교!
                if(Math.round(Math.pow(root, p)) == absX){
                    // 동일한 경우 answer 갱신 후 종료
                    answer = p;
                    break;
                }
            }

            sb.append(answer).append("\n");
        }
        // 정답 반환
        System.out.println(sb.toString().trim());
    }
}