package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2551
// - 정렬 : 계수 정렬을 통해 각 숫자의 개수 체크!
//          1~10000 모든 숫자를 체크하여 가능한 최소의 값 탐색
public class _29_Solution_1 {
    public static final int MAX = 10000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 숫자 개수
        int N = Integer.parseInt(in.readLine());
        int[] numberCounts = new int[MAX+1];

        // 숫자 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        while(st.hasMoreTokens()) numberCounts[Integer.parseInt(st.nextToken())]++;

        // 정답 : 대표 숫자 탐색
        int[] answers = representativeNumber(numberCounts);

        // 정답 반환
        sb.append(answers[0]).append(" ").append(answers[1]);
        System.out.println(sb);
    }

    private static int[] representativeNumber(int[] numberCounts) {
        // result[0] : 대표 숫자 1
        // result[1] : 대표 숫자 2
        int[] result = new int[]{0, 0};
        long[] minSum = new long[]{Long.MAX_VALUE, Long.MAX_VALUE};

        for(int number = 1; number <= MAX; number++){
            long[] sum = new long[]{0, 0};
            for(int target = 1; target <= MAX; target++) {
                long diff = target - number;
                // 방법 1 : 차이의 합(절대값)
                sum[0] += Math.abs(diff) * numberCounts[target];
                // 방법 2 : 차이 제곱의 합
                sum[1] += diff * diff * numberCounts[target];
            }
            if(sum[0] < minSum[0]){
                result[0] = number;
                minSum[0] = sum[0];
            }

            if(sum[1] < minSum[1]){
                result[1] = number;
                minSum[1] = sum[1];
            }
        }

        return result;
    }
}