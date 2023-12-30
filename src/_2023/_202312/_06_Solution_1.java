package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/12904
// - 직접 변환하며 가능한지 확인!
// - S에서 T로 변환하는 경우는 2가지, T에서 S로 변환하는 경우는 1가지!
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 입력
        String S = in.readLine();
        String T = in.readLine();

        // T의 길이가 더 긴 경우 반복하여 변환!
        while(S.length() < T.length()){
            // T의 마지막 문자
            char end = T.charAt(T.length()-1);
            // 마지막 문자 제거
            T = T.substring(0, T.length()-1);
            // 마지막 문자가 B인 경우 뒤집어 입력
            if(end == 'B') T = new StringBuilder(T).reverse().toString();
        }

        // 정답 출력
        // - S와 T가 동일한 경우 1, 아닐 경우 0
        sb.append(S.equals(T) ? 1 : 0);
        System.out.println(sb);
    }
}
