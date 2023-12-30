package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14254
// - 참고 : https://nahwasa.com/entry/%EC%9E%90%EB%B0%94-%EB%B0%B1%EC%A4%80-14254-%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8-%EB%B3%80%EA%B2%BD-java
// - 변경되는 문자를 확인해 가장 효율적인 문자로 변경
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] password = in.readLine().toCharArray();  // 기존 비밀번호
        int K = Integer.parseInt(in.readLine());        // 처음, 시작의 동일한 길이

        int diff = password.length - K; // 영향을 받는 길이

        // 정답 초기화
        int answer = 0;
        for(int i = 0; i < diff; i++){
            int sum = 0;    // 전체 개수
            int max = 0;    // 가장 많이 나온 문자
            int[] cnt = new int['z'-'a'+1]; // 문자별 카운팅

            // 영향을 받는 위치의 문자 카운팅
            for(int j = i; j < password.length; j += diff){
                sum++;
                max = Math.max(max, ++cnt[password[j]-'a']);
            }
            // 가장 많이 나온 문자로 변경!
            answer += sum-max;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}