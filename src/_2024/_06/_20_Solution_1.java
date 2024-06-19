package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/6443
// - 조합 : next Permutation을 통해 가능한 모든 조합 탐색!
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int N = Integer.parseInt(in.readLine());
        while(N-- > 0){
            // 단어 입력
            char[] word = in.readLine().toCharArray();

            // 오름차순 정렬
            Arrays.sort(word);

            // nextPermutation 함수를 통해 가능한 모든 조합 차례로 탐색
            do sb.append(word).append("\n");
            while(nextPermutation(word));
        }


        // 정답 출력
        System.out.println(sb.toString());
    }

    private static boolean nextPermutation(char[] input) {
        int len = input.length;

        int i = len - 1;
        while(i > 0 && input[i-1] >= input[i]) i--;
        if(i == 0) return false;

        int j = len - 1;
        while(input[i-1] >= input[j]) j--;
        swap(input, i-1, j);

        int k = len - 1;
        while(i < k) swap(input, i++, k--);

        return true;
    }

    private static void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
