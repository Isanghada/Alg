package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2317
// -
public class _29_Solution_1 {
    static int N, K;
    static int[] LION;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        LION = new int[K];
        int[] H = new int[2];
        int[] L = new int[2];
        H[0] = Integer.MAX_VALUE;
        L[0] = Integer.MAX_VALUE;

        for(int i = 0; i < K; i++){
            LION[i] = Integer.parseInt(in.readLine());
            L[0] = Math.min(L[0], LION[i]);
            L[1] = Math.max(L[1], LION[i]);
        }

        for(int i = K; i < N; i++){
            int human = Integer.parseInt(in.readLine());
            H[0] = Math.min(H[0], human);
            H[1] = Math.max(H[1], human);
        }

        int answer = 0;
        for(int i = 1; i < K; i++) answer += Math.abs(LION[i] - LION[i-1]);

        if(H[0] < L[0]) answer += check(H[0], L[0]);
        if(H[1] > L[1]) answer += check(H[1], L[1]);

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static int check(int h, int l) {
        int value = Math.abs(h-l) * 2;

        value = Math.min(value, Math.abs(LION[0] - h));
        value = Math.min(value, Math.abs(LION[K-1] - h));

        return value;
    }
}