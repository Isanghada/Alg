package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19951
// - 누적합 : 명령의 결과를 누적합으로 계산하여 한번에 진행!
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 연변장의 크기
        int M = Integer.parseInt(st.nextToken());   // 조교의 수

        // 연변장 높이 입력
        int[] heights = new int[N+1];
        st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++) heights[i] = Integer.parseInt(st.nextToken());

        // 조교의 명령 누적합 배열
        int[] sum = new int[N+2];
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            sum[a] += k;
            sum[b+1] -= k;
        }
        // 누적합 계산 후 연변장 높이 변화!
        for(int i = 1; i <= N; i++) {
            sum[i] += sum[i-1];
            heights[i] += sum[i];

            sb.append(heights[i]).append(" ");
        }
        System.out.println(sb);
    }
}
