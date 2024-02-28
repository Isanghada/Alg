package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12438
// - 최소공배수 : https://imkh.dev/algorithm-gcd-lcm#%EC%B5%9C%EC%86%8C%EA%B3%B5%EB%B0%B0%EC%88%98
public class _29_Solution_1 {

    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        int t = 1;
        while(T-- > 0){
            // 달력 정보
            StringTokenizer st = new StringTokenizer(in.readLine());
            long totalMonth = Long.parseLong(st.nextToken());  // 총월수
            long dayOfMonth = Long.parseLong(st.nextToken()); // 월당일수
            long dayOfWeek = Long.parseLong(st.nextToken());  // 주당일수

            // 전체 일수
            long totalDay = totalMonth * dayOfMonth;
            long answer = totalDay / dayOfWeek + totalMonth -
                          totalDay / lcm(dayOfMonth, dayOfWeek);

            sb.append("Case #").append(t++).append(": ").append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
    // 최소공배수 함수
    private static long lcm(long a, long b) {
        if(a < b){
            long temp = a;
            a = b;
            b = temp;
        }
        return a * b / gcm(a, b);
    }
    // 최대공약수 함수
    private static long gcm(long a, long b) {
        long r = 0;
        while(b != 0){
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}