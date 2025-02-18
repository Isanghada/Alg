package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1565
// - 유클리드 호제법 : D의 최대 공배수(lcm), M의 최소 공약수(gcd)를 유클리드 호제법을 통해 계산
//                     gcd의 모든 공약수 중 lcm의 공배수의 개수 체크
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int sizeD = Integer.parseInt(st.nextToken());   // D 사이즈
        int sizeM = Integer.parseInt(st.nextToken());   // M 사이즈

        // 배열 입력
        long[] D = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] M = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        // 최대 공약수 계산
        long gcdOfM = M[0];
        for(int i = 1; i < sizeM; i++) gcdOfM = gcd(gcdOfM, M[i]);

        // 최소 공배수 계산
        long lcmOfD = 1;
        for(int i = 0; i < sizeD; i++){
            lcmOfD = lcm(lcmOfD, D[i]);
            if(lcmOfD > gcdOfM) break;
        }

        // 정답 초기화
        int answer = 0;
        // - 공약수 제한!
        long limit = ((long)Math.sqrt(gcdOfM))+1;
        // 조건을 만족하는 경우 체크
        for(long num = 1; num <= limit; num++){
            if(gcdOfM % num == 0){
                long A = num;
                long B = gcdOfM / A;
                if(A % lcmOfD == 0) answer++;
                if(A != B && (B % lcmOfD == 0)) answer++;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static long lcm(long a, long b) {
        return a * b / gcd(a, b);
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
