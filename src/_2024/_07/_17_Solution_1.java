package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10253
// - 수학 : 최대공약수(gcd)를 활용해 헨리식 표현법으로 계산!
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());   // 분자
            int b = Integer.parseInt(st.nextToken());   // 분모

            while(a > 1){
                int x = (b % a == 0) ? (b / a) : (b / a + 1);
                a = a*x - b;
                b *= x;

                int gcd = getGcd(a, b);
                a /= gcd;
                b /= gcd;
            }

            // 분모 출력
            sb.append(b).append("\n");
        }


        // 정답 출력
        System.out.println(sb.toString());
    }
    // 최대 공약수 : 유클리드 호제법을 통해 계산
    private static int getGcd(int a, int b) {
        int r = a % b;
        while(r != 0){
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }
}
