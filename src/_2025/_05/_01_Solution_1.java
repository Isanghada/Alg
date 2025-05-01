package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1756
// - 수학 : 복소수의 곱셈과 동일한 결과! (가우스 정수의 소수 판별볍)
//          - 복소수의 성질을 이용하여 확인
public class _01_Solution_1 {
    // 최대값 : 1 < M^2 + N^2 < 20000 이므로 범위 내의 최대값 설정
    static final int LIMIT = 145;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            // 은행수 입력
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            // 은행수의 크기 계산
            int mn = M * M + N * N;

            int count = 0;
            for(int a = -LIMIT; a <= LIMIT; a++){
                for(int b = -LIMIT; b <= LIMIT; b++){
                    // 약수 후보 (a, b)의 크기
                    int ab = a * a + b * b;
                    // 불가능한 경우 continue
                    if(ab == 0 || ab > mn || mn % ab != 0) continue;

                    // (a, b)가 (m, n)의 약수인 경우 count 증가
                    if(isDivisor(M, N, a, b)) count++;
                }
            }

            // 8개의 약수를 기준으로 소수 판별
            if(count == 8) sb.append("P\n");
            else sb.append("C\n");
        }

        // 정답 입력
        System.out.println(sb);
    }

    private static boolean isDivisor(int m, int n, int a, int b) {
        int d = a * a + b * b;
        if(d == 0) return false;

        int x = m * a + n * b;
        int y = n * a - m * b;

        return x % d == 0 && y % d == 0;
    }
}
