package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/17205
// - 재귀 : 각 자리별로 차례로 계산
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 패스워드 최대 길이
        int N = Integer.parseInt(in.readLine());
        // 비밀번호
        char[] password = in.readLine().toCharArray();

        // 정답 출력
        // - 비밀번호 맞히는 시간 계산
        sb.append(getTime(N, 0, password));
        System.out.println(sb);
    }
    // 비밀번호 맞히는 시간 계산 함수 : 재귀 활용
    // - step 인덱스를 기준으로 비밀번호 확인!
    private static long getTime(int n, int step, char[] password) {
        // 시간의 합
        long sum = 0;

        // 현재 문자 이전까지 입력하기 위한 시간 계산
        long time = password[step] - 'a';
        // 남은 비밀번호에 26개의 비밀번호 추가!
        for(int i = step; i < n; i++) {
            sum += time;
            time *= 26;
        }
        // 현재 비밀번호와 동일한 경우 증가
        sum++;
        // 비밀번호를 구하지 못한 경우 다음 인덱스 계산
        if(step+1 < password.length) sum += getTime(n, step+1, password);

        return sum;
    }
}
