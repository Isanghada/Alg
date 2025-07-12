package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1549
// -
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        long[] A = Arrays.stream(("0 "+in.readLine()).split(" ")).mapToLong(Long::parseLong).toArray();

        for(int i = 1; i <= N; i++) A[i] += A[i-1];

        long[] answers = new long[]{0, Long.MAX_VALUE};
        for(int k = N / 2; k > 0; k--){
            int iLimit = N - 2 * k + 1;
            int jLimit = N - k + 1;
            for(int i = 1; i <= iLimit; i++){
                long iSum = A[i+k-1] - A[i-1];
                long diff = Long.MAX_VALUE;
                for(int j = i + k; j <= jLimit; j++){
                    long jSum = A[j+k-1] - A[j-1];
                    diff = Math.min(diff, Math.abs(iSum - jSum));
                }
                if(diff < answers[1]){
                    answers[0] = k;
                    answers[1] = diff;
                }
            }
        }

        // 정답 출력
        sb.append(answers[0]).append("\n").append(answers[1]);
        System.out.println(sb.toString());
    }
}
