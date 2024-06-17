package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/1490
// - 브루트포스 : 만들 수 있는 모든 수 탐색
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기 숫자
        int N = Integer.parseInt(in.readLine());

        // 각 자리수의 최소 공배수
        int lcm = getLcm(N);

        // 정답 출력
        // - N과 lcm을 사용해 최소값 계산
        sb.append(getMinNumber(N, lcm));
        System.out.println(sb.toString());
    }

    // n을 시작으로 0~9를 추가하며 최소값 탐색 : BFS
    private static long getMinNumber(int n, int num) {
        Deque<Long> deque = new LinkedList<>();

        deque.offerLast((long)n);
        while(!deque.isEmpty()){
            long cur = deque.pollFirst();

            if(cur % num == 0) return cur;
            cur *= 10;
            for(int i = 0; i < 10; i++) deque.offerLast(cur + i);
        }
        return -1;
    }

    // 최소 공배수 함수 : n의 각 자리수의 최소 공배수 계산
    private static int getLcm(int n) {
        // 초기값 설정
        int lcm = 1;
        while(n > 0){
            // 현재 자리수
            int num = n % 10;
            n /= 10;

            // 현재 자리수가 0인 경우 패스
            if(num == 0) continue;
            // 0이 아닌 경우 최대 공약수 계산
            int gcd = euclidean(lcm, num);
            // lcm과 num의 최소 공배수 계산
            lcm = lcm * num / gcd;
        }

        return lcm;
    }
    // 유클리디안 호제법 : 최대 공약수 계산
    private static int euclidean(int a, int b) {
         while(b != 0){
             int temp = a;
             a = b;
             b = temp % b;
         }
         return a;
    }
}
