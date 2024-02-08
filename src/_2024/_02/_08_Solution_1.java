package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25634
// - 누적합 : 누적합을 통해 구간의 합을 빠르게 계산!
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 전구의 수
        int N = Integer.parseInt(in.readLine());

        // 전구의 밝기 배열
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] lightArr = new int[N+1];
        for(int i = 1; i <= N; i++) lightArr[i] = Integer.parseInt(st.nextToken());

        // 전구의 초기 상태 배열
        st = new StringTokenizer(in.readLine());
        int count = 0;  // 켜져있는 전구의 수
        int[] lightState = new int[N+1];
        for(int i = 1; i <= N; i++) {
            lightState[i] = Integer.parseInt(st.nextToken());
            count += lightState[i];
        }

        // 누적합 : 켜져있는 전구의 밝기 합!
        int[] sum = new int[N+1];
        for(int idx = 1; idx <= N; idx++) {
            sum[idx] = sum[idx-1];
            if(lightState[idx] == 1) sum[idx] += lightArr[idx];
        }

        // 정답 초기화
        int answer = sum[N];

        // 모든 전구가 켜져있지 않다면! : 상태를 뒤집은 구간이 기존 보다 큰 모든 경우 탐색!
        if(count < N){
            int prev = 0;           // 구간 시작(미포함)
            int reverseTotal = 0;   // 구간 밝기 합
            for(int idx = 1; idx <= N; idx++){
                // 초기 상태가 0인 경우 밝기 증가!
                reverseTotal += (lightState[idx] == 0 ? lightArr[idx] : 0);
                // 초기 상태의 구간 합 이하 경우 : 구간 갱신
                if(reverseTotal <= sum[idx] - sum[prev]){
                    prev = idx;
                    reverseTotal = 0;
                // 초기 상태의 구간 합 초과인 경우 : 정답 갱신
                }else answer = Math.max(answer, sum[prev] + reverseTotal + sum[N] - sum[idx]);
            }
        // 모든 전구가 켜져있는 경우 : 가장 밝기가 작은 전구 상태 변경!
        }else{
            int min = lightArr[1];
            for(int idx = 2; idx <= N; idx++) min = Math.min(min, lightArr[idx]);
            answer -= min;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
