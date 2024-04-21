package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/20164
// - 브루트포스 : 모든 경우를 탐색하여 최소값, 최대값 계산
public class _21_Solution_1 {
    private static int MAX; // 최대값
    private static int MIN; // 최소값
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 시작 숫자
        int N = Integer.parseInt(in.readLine());

        // 최대값, 최소값 초기화
        MIN = Integer.MAX_VALUE;
        MAX = 0;

        // N부터 시작하여 가능한 홀수 개수 계산
        getMinMaxCount(N, getOddCount(N));

        // 최소값, 최대값 출력
        sb.append(MIN).append(" ").append(MAX);
        System.out.println(sb);
    }
    // 최대값, 최소값 계산 함수 : 재귀를 통해 모든 경우 탐색
    private static void getMinMaxCount(int n, int total){
        // n이 한 자리 수일 경우 종료!
        // - 최대값, 최소값 갱신
        if(n < 10){
            MAX = Math.max(MAX, total);
            MIN = Math.min(MIN, total);
        // n이 두 자리 수일 경우
        }else if(n < 100){
            // 십의 자리, 일의 자리 수를 각각 더하여 다음 수 계산
            int next = n / 10;
            next += n % 10;

            getMinMaxCount(next, total + getOddCount(next));
        }
        // n이 세 자리 이상일 경우
        else{
            // 현재 수를 문자열로 변환 -> 빠르게 3개의 수로 분리하기 위해
            String cur = String.valueOf(n);
            // (0, lenCur) -> (0, a+1), (a+1, b+1), (b+1, lenCur) 3가지 경우로 분리하여 계산
            for(int a = 0; a < cur.length() - 2; a++){
                for(int b = a+1; b < cur.length() - 1; b++){
                    int next = Integer.parseInt(cur.substring(0, a+1));
                    next += Integer.parseInt(cur.substring(a+1, b+1));
                    next += Integer.parseInt(cur.substring(b+1));
                    getMinMaxCount(next, total + getOddCount(next));
                }
            }
        }
    }
    // 홀수 개수 함수 : 매개변수로 주어진 값에 포함된 홀수 개수 계산
    private static int getOddCount(int n){
        int count = 0;
        while(n > 0){
            if(((n % 10) & 1) == 1) count++;
            n  /= 10;
        }

        return count;
    }
}