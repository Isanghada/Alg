package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/21870
// - 분할 정복 : 유클리드 호제법으로 GCD 계산!
//               분할 정복을 통해 최대값이 되도록 계산!
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 매물 개수
        int N = Integer.parseInt(in.readLine());
        // 매물 정보
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();


        // 정답 출력
        // - 분할 정복을 통해 최대값 계산
        sb.append(getLoveGcd(A, 0, N-1));
        System.out.println(sb);
    }

    private static long getLoveGcd(int[] a, int start, int end) {
        // 정수가 1개인 경우 값 반환
        if(start == end) return a[start];
        // 정수가 2개인 경우 값의 합 반환 : 각 정수가 GCD이기 때문이다
        else if((start + 1) == end) return a[start] + a[end];

        // GCD를 구할 정수 개수
        int size = (end - start + 1) / 2;

        // 왼쪽일 경우 값 계산
        int gcd = a[start];
        int idx = start;
        while(idx <= (start+size-1)) gcd = gcd(gcd, a[idx++]);
        long leftSum = gcd + getLoveGcd(a, start+size, end);

        // 오른쪽일 경우 값 계산
        gcd = a[end];
        while(idx < end) gcd = gcd(gcd, a[idx++]);
        long rightSum = gcd + getLoveGcd(a, start, start+size-1);

        // 왼쪽과 오른쪽 중 최대값 반환!
        return Math.max(leftSum, rightSum);
    }

    private static int gcd(int a, int b) {
        int r = 0;
        while(b != 0){
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
