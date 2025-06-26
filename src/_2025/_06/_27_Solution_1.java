package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1885
// -
public class _27_Solution_1 {
    static final int MAX = 30_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = 1;
        int count = 0;
        int[] numArr = new int[K+1];
        while(N-- > 0){
            int num = Integer.parseInt(in.readLine());
            if(numArr[num] == 0){
                count++;
                numArr[num] = 1;
            }
            if(count == K){
                answer++;
                count = 0;
                for(int i = 1; i <= K; i++) numArr[i] = 0;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
