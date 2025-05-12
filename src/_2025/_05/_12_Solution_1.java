package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/29616
// - 유클리드 호제법 : 각 백분율을 최대 공약수로 나누어 최소 투표수 계산
//                      현재 시점의 경우 이전 시점보다 각 항목의 투표수가 만도록 조정!
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 투표 항목의 수
        int P = Integer.parseInt(st.nextToken());   // 소수점 자리수!

        // 이전 시점
        long[] before = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        // 현재 시점
        long[] after = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        // 이전 시점 최대 공약수
        long beforeGCD = before[0];
        for(long p : before) if(p > 0) beforeGCD = gcd(beforeGCD, p);

        // 현재 시점 최대 공약수
        long afterGCD = after[0];
        for(long p : after) if(p > 0) afterGCD = gcd(afterGCD, p);

        // System.out.println(beforeGCD+", "+afterGCD);

        // 이전 시점 대비 현재 시점 배수!
        long max = 1;
        for(int i = 0; i < N; i++){
            before[i] /= beforeGCD; // 이전 시점 조정
            after[i] /= afterGCD;   // 현재 시점 조정

            // 현재 시점의 투표수가 이전 시점 이상이 되기 위한 최소 배수!
            if(after[i] > 0) {
                long diff = before[i] / after[i] + (before[i] % after[i] == 0 ? 0 : 1);
                max = Math.max(max, diff);
            }
        }

        // 정답 초기화
        // - 각 시점의 투표수 합 계산
        long[] answer = new long[2];
        for(int i = 0; i < N; i++){
            answer[0] += before[i];
            answer[1] += after[i] * max;
        }


        // 결과 반환
        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb.toString().trim());
    }

    private static long gcd(long a, long b) {
        while(b != 0){
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
