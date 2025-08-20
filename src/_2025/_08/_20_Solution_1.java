package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/29768
// -
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] answers = new char[N];
        int i = 0;
        for(; i < (N - K); i++) answers[i] = 'a';

        for(int j = 0; j < K; j++){
            if(i >= N) break;
            answers[i] = (char)('a' + j);
            i++;
        }

        // 정답 출력
        sb.append(String.valueOf(answers));
        System.out.println(sb.toString());
    }
}
