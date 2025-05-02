package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30960
// -
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] students = Arrays.stream(("0 "+ in.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(students);

        int[] sum = new int[N+1];
        for(int n = 2; n <= N; n++) {
            sum[n] = students[n] - students[n-1] + sum[n-2];
        }

//        System.out.println(Arrays.toString(students));
//        System.out.println(Arrays.toString(sum));

        int answer = Integer.MAX_VALUE;
        for(int n = 1; n <= N - 2; n+=2){
            int result = students[n+2] -students[n];
            if(n - 1 > 0) result += sum[n-1];
            if(n < N - 2) result += sum[N] - sum[n+2];

            answer = Math.min(answer, result);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
