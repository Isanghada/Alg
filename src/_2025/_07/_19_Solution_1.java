package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30406
// -
public class _19_Solution_1 {
    static final int MAX = 3;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] count = new int[MAX+1];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            count[num]++;
        }

        int answer = 0;
        answer += getSum(0, 3, count);
        answer += getSum(1, 2, count);
        answer += getSum(0, 2, count);
        answer += getSum(1, 3, count);
        answer += getSum(0, 1, count);
        answer += getSum(2, 3, count);

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }

    private static int getSum(int a, int b, int[] count) {
        int min = Math.min(count[a], count[b]);

        count[a] -= min;
        count[b] -= min;
        return (a ^ b) * min;
    }
}
