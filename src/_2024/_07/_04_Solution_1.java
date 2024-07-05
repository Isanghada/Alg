package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4233
// - 수학 : 소수 판별, 거듭 제곱을 통해 해결
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        while(true){
            st = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            // 0일 경우 종료!
            if(p == 0 && a == 0) break;

            // 소수가 아니고 페르마 소정리를 만족한 경우 yes
            // 아닐 경우 no
            sb.append(!isPrime(p) && pow(a, p, p) == a ? "yes":"no").append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static long pow(long a, long p, long mod) {
        long result = 1;
        while(p > 0){
            if((p & 1) == 1) result = result * a % mod;
            a = a * a % mod;
            p = p >> 1;
        }
        return result;
    }

    private static boolean isPrime(int num) {
        if( num < 2 ) return false;
        for(int n = 2; n *n <= num; n++) if(num % n == 0) return false;
        return true;
    }
}
