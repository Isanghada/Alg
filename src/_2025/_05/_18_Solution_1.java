package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12762
// - DP : 시작 지점에서 내리막인 경우, 끝 지점에서 오르막인 경우를 따로 계산 후 합쳐서 체크
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        int[] heights = new int[N+1];
        int[] down = new int[N+1];
        int[] up = new int[N+1];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int n = 1; n <= N; n++) {
            heights[n] = Integer.parseInt(st.nextToken());
            down[n] = 1;
            up[n] = 1;
        }

        for(int i = 1; i <= N; i++){
            int target = heights[i];
            for(int j = 1; j < i; j++){
                int cur = heights[j];
                if(cur > target) down[i] = Math.max(down[i], down[j]+1);
            }
        }

        for(int i = N; i > 0; i--){
            int target = heights[i];
            for(int j = N; j > i; j--){
                int cur = heights[j];
                if(cur > target) up[i] = Math.max(up[i], up[j]+1);
            }
        }

        int answer = 0;
        for(int mid = 1; mid <= N; mid++) answer = Math.max(answer, down[mid] + up[mid] - 1);

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
