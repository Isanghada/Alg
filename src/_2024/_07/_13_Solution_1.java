package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/13140
// - 브루트포스 : 가능한 모든 경우 탐색!
//                nextPermutation을 통해 d, e, h, l, o, r, w를 선택!
//                h, w가 0인 경우 패스, 아닌 경우 계산 후 비교
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 타겟 숫자
        int N = Integer.parseInt(in.readLine());
        // 숫자 배열!
        // - nums[i] = [0, 1, 2, 3, 4, 5, 6, 7];
        // - 0인 경우 문자 미배정, [1 ~ 7 => d, e, h, l, o, r, w]
        int[] nums = new int[10];
        for(int i = 3; i < 10; i++) nums[i] = i-2;
        do{
            // 문자별 값 입력
            int[] value = new int[8];
            for(int i = 0; i < 10; i++){
                if(nums[i] != 0) value[nums[i]] = i;
            }

            // h 혹은 w가 0인 경우 패스
            if(value[3] == 0 || value[7] == 0) continue;
            // hello 계산
            int hello = value[3] * 10000 + value[2] * 1000 + value[4] * 100 + value[4] * 10 + value[5];
            // world 계산
            int world = value[7] * 10000 + value[5] * 1000 + value[6] * 100 + value[4] * 10 + value[1];

            // N과 비교하여 동일한 경우 출력 후 종료
            if((hello + world) == N){
                sb.append("  ").append(hello).append("\n")
                        .append("+ ").append(world).append("\n")
                        .append("-------\n")
                        .append(String.format("%7d", N));
                break;
            }
        }while(nextPermutation(nums));

        // 알맞은 값이 없는 경우 No Answer 출력
        if(sb.length() == 0) sb.append("No Answer");

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static boolean nextPermutation(int[] input) {
        int N = input.length;

        int i = N - 1;
        while(i > 0 && input[i-1] >= input[i]) i--;
        if(i == 0) return false;

        int j = N - 1;
        while(input[i-1] >= input[j]) j--;

        swap(input, i-1, j);
        int k = N - 1;
        while(i < k) swap(input, i++, k--);

        return true;
    }

    private static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
