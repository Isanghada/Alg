package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2166
// - 신발끈 공식 : https://ko.wikipedia.org/wiki/%EC%8B%A0%EB%B0%9C%EB%81%88_%EA%B3%B5%EC%8B%9D
// - 다각형 A의 면적 : |x1y2 + ... + xny1 - x2y1 - ... - x1yn | / 2
public class _19_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 점의 개수
        int N = Integer.parseInt(in.readLine());
        long[] X = new long[N+1];   // 점의 X 좌표
        long[] Y = new long[N+1];   // 점의 Y 좌표

        // 점의 좌표 입력
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            X[i] = Long.parseLong(st.nextToken());
            Y[i] = Long.parseLong(st.nextToken());
        }
        // 신발끈 공식을 활용하기 위해 마지막 좌표에 첫번째 좌표 입력
        X[N] = X[0];
        Y[N] = Y[0];

        // 좌표값의 합 계산
        long sum = 0;
//        long sum1 = 0;
//        long sum2 = 0;
        for(int i = 0; i < N; i++){
            sum += (X[i]*Y[i+1] - X[i+1]*Y[i]);
//            sum1 += X[i]*Y[i+1];
//            sum2 += X[i+1]*Y[i];
//            System.out.println(sum);
        }

        // 정답 반환
        // - 소수점 첫번째 자리까지만 출력
        sb.append(String.format("%.1f", Math.abs(sum) / 2.0));
        System.out.println(sb);
    }
}
