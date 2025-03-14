package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
// https://www.acmicpc.net/problem/28426
// - 수학 : 1. (짝수 + 홀수 => 홀수)이므로 마지막 숫자를 홀수로 고정하고 나머지를 짝수로 고정!
//          2. 짝수의 합을 10의 배수로 고정하면 홀수는 5로 고정!
//             => '(10의 배수) + 5'는 짝수는 약수가 될 수 없고 5만 가능!
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        // 수가 클 경우를 대비하여 2, 8로 10 구성!
        if(N > 3){
            N -= 2;
            sb.append("2 8 ");
        }

        // 남는 수열은 10의 배수로 구성
        int num = 10;
        while(N > 1){
            sb.append(num).append(" ");
            num += 10;
            N--;
        }

        // 마지막 홀수는 5로 구성
        sb.append(5);

        // 정답 반환
        System.out.println(sb);
    }
}

