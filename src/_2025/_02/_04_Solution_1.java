package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1407
// - 수학 : 로그를 활용해 범위 내의 가장 큰 2의 거듭 제곱 값을 구하고, 큰 값부터 약수가 되는 개수 계산
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long A = Long.parseLong(st.nextToken());    // 범위 시작
        long B = Long.parseLong(st.nextToken());    // 범위 끝

        // 이전 약수 개수 : 2의 거듭제곱 이므로 큰 값은 작은 값을 약수로 가짐.
        //                  따라서, 큰 값의 개수만큼 작은 값의 개수는 감소해야한다.
        long past = 0;
        // 홀수 개수 계산
        long answer = getOddCount(A, B);

        // 최대 거듭 제곱
        long p = (long) Math.pow(2, log(B, 2));
        // 모든 2의 거듭 제곱 확인
        while(p > 1){
            // 거듭 제곱의 개수
            long count = getPowCount(A, B, p);
            // 약수의 합만큼 증가
            answer += (count - past) * p;
            // past 갱신
            past = count;

            // 거듭 제곱 갱신
            p /= 2;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static long log(long target, int p) {
        return (long) (Math.log(target) / Math.log(p));
    }

    private static long getOddCount(long a, long b) {
        return (b - a + 1) - getPowCount(a, b, 2);
    }

    private static long getPowCount(long a, long b, long p) {
        return (b / p) - ((a - 1) / p);
    }
}
