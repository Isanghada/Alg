package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1990
// - 에라토스테네스의 체 : 미리 소수 판별 후 팰린드롬 검사!
public class _03_Solution_1 {
    public static final int MAX = 100_000_000;  // 최대값
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());   // 최소값
        int B = Integer.parseInt(st.nextToken());   // 최대값

        // 소수 판별 : true인 경우 소수가 아님!
        boolean[] isPrime = new boolean[MAX / 2];
        // 에라토스테네스의 체
        final int LIMIT = (int) Math.sqrt(B);
        for(int num = 3; num <= LIMIT ; num += 2){
            int idx = num / 2;
            if(isPrime[idx]) continue;

            int next = num * num;
            while(next <= B){
                isPrime[next / 2] = true;
                next += (num+num);
            }
        }

        // 2를 포함할 경우 출력
        if(A <= 2) sb.append(2).append("\n");

        // A가 짝수일 경우 홀수로 변경
        if((A & 1) != 1) A++;
        // 모든 홀수 검사!
        for(; A <= B; A += 2){
            // 소수이면서
            if(!isPrime[A / 2]){
                // 팰린드롬이면 출력
                if(isPalindrome(A)) sb.append(A).append("\n");
            }
        }
        // 마지막에 -1 출력
        sb.append(-1);

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean isPalindrome(int num) {
        String input = String.valueOf(num);
        for(int i = 0; i < input.length() / 2; i++){
            if(input.charAt(i) != input.charAt(input.length() - i - 1)) return false;
        }

        return true;
    }
}
