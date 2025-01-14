package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20159
// -
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        int half = N / 2;
        long[] card1 = new long[half + 1];
        long[] card2 = new long[half + 1];

        StringTokenizer st=  new StringTokenizer(in.readLine());
        for(int i = 1; i <= half; i++){
            card1[i] = card1[i-1] + Integer.parseInt(st.nextToken());
            card2[i] = card2[i-1] + Integer.parseInt(st.nextToken());
        }

        long answer = card1[half];
        long temp = 0;
        for(int i = 0; i < N; i++){
            if((i & 1) == 1) temp = card1[i/2] +(card2[half] - card2[i/2]);
            else temp = card1[i/2+1] + (card2[half - 1] - card2[i/2]);
            answer = Math.max(answer, temp);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}

