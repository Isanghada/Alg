package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2531
// - 구간별로 숫자의 개수를 계산하여 빠르게 확인.
// - 구간이 맞춰지게끔 시작, 끝 범위까지는 직접 계산.
public class _10_Solution_1 {
    public static int[] answer;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202310/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 마지막 페이지 입력
        int N = Integer.parseInt(in.readLine());

        // 정답 초기화
        answer = new int[10];
        // 시작 페이지
        int start = 1;
        // 숫자의 개수를 구할 자리수 : 1의 자리부터 마지막 자리수까지 계산
        int digit = 1;

        // 시작 페이지가 끝 페이지 이하일 경우 반복
        while(start <= N){
            // 끝 페이지의 마지막 수를 9가 되게끔 설정
            // - 각 페이지별로 숫자 개별 카운팅
            while(N % 10 != 9 && start <= N) count(N--, digit);
            // 모든 페이지를 탐색한 경우 종료
            if (N < start) break;
            // 시작 페이지의 마지막 수가 0이 되게끔 설정
            // - 각 페이지별로 숫자 개별 카운팅
            while(start % 10 != 0 && start <= N) count(start++, digit);

            // 마지막 자리수는 필요없으므로 제거
            start /= 10;
            N /= 10;
            // 모든 숫자가 같은 횟수로 나오도록 범위를 조정했으므로 각 숫자가 나올 횟수 계산
            // - XXX0 ~ YYY9 일 경우(단 XXX0 <= YYY9)
            //   - (YYY - XXX + 1) 만큼 0~9가 반복.
            //   - 해당 값에 digit을 곱하여 최종 횟수 계산.
            int count = (N - start + 1) * digit;
            // 모든 숫자에 count만큼 증가
            for(int idx = 0; idx < 10; idx++) answer[idx] += count;
            // 자리수 증가
            digit *= 10;
        }
        // 정답 반환
        for(int count : answer) sb.append(count).append(" ");
        System.out.println(sb.toString().trim());
    }

    private static void count(int n, int digit) {
        while(n > 0){
            answer[n % 10] += digit;
            n = n / 10;
        }
    }
}
