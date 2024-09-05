package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/17425
// - 정수 : 1부터 1_000_000까지 모든 수를 돌며 약수인 경우 값 증가!
//          누적합을 통해 g(x) 계산!
public class _05_Solution_1 {
    private static final int MAX = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // g 함수 계산!
        long[] g = getG(MAX);

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 자연수 입력
            int N = Integer.parseInt(in.readLine());
            // 계산한 g함수의 값 반환!
            sb.append(g[N]).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static long[] getG(int n) {
        long[] result = new long[n+1];
        Arrays.fill(result, 1);

        for(int i = 2; i <= n; i++){
            int num = i;
            while(num <= n){
                result[num] += i;
                num += i;
            }
        }
        for(int i = 2; i <= n; i++ ) result[i] += result[i-1];

        return result;
    }
}
