package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/29726
// - 그리디 : left, right를 조정하며 최대값 탐색
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] minNum = new int[N];
        Arrays.fill(minNum, Integer.MIN_VALUE);
        minNum[0] = A[0];
        for(int i = 1; i < N; i++) minNum[i] = Math.min(minNum[i-1], A[i]);

        int answer = Integer.MIN_VALUE;
        for(int right = 1; right < N; right++){
            int deleteRight = N - 1 - right;
            int deleteLeft = M - deleteRight;
            if(deleteLeft >= 0){
                answer = Math.max(answer, A[right] - minNum[deleteLeft]);
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
