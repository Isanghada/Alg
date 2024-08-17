package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24508
// - 그리디 : 나도리가 많이 든 바구니부터 차례로 K개씩 채우며 확인!
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 바구니의 수
        int K = Integer.parseInt(st.nextToken());   // 저주발생 개수
        int T = Integer.parseInt(st.nextToken());   // 최대 이동 횟수

        // 바구니별 나도리!
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
        long sum = sumOfA(A);

        // 정렬
        Arrays.sort(A);

        // K 배수가 아닌 경우 불가능!
        if(sum % K != 0) sb.append("NO");
        else{
            // 옮긴 횟수
            long count = 0;
            for(int a = (int)(N-(sum/K)); a < N; a++) count += K - A[a];
            if(count <= T) sb.append("YES");
            else sb.append("NO");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static long sumOfA(int[] A) {
        long result = 0;
        for(int a : A) result += a;
        return result;
    }
}
