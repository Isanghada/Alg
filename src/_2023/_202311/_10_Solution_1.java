package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3020
// - 누적합을 이용해 높이별 석순(down)과 종유석(up)의 개수를 계산
// - 모든 높이를 탐색하여 장애물을 파괴하는 최소값인 경우의 수를 체크
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 동굴의 길이, 높이 입력
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());   // 길이
        int H = Integer.parseInt(st.nextToken());   // 높이

        // 장애물 초기화
        int[] down = new int[H+2];  // 석순
        int[] up = new int[H+2];    // 종유석
        // 장애물 정보는 짝수개이므로 N/2 만큼만 반복
        for(int i = 0; i < (N / 2); i++){

            // 석순 정보
            int a = Integer.parseInt(in.readLine());
            // 종유석 정보
            int b = H - Integer.parseInt(in.readLine()) + 1;

            // 장애물 정보에 따라 개수 증가
            down[a]++;
            up[b]++;
        }

        // 석순과 종유석 누적합 계산
        for(int i = 1; i <= H; i++){
            down[i] += down[i-1];
        }
        for(int i = H; i >= 1; i--){
            up[i] += up[i+1];
        }

        // 정답 초기화 : 장애물 파괴 최소 횟수는 N으로 초기화
        // - answer[0] : 장애물 파괴 최소 횟수
        // - answer[1] : answer[0]인 경우의 수
        int[] answer = new int[2];
        answer[0] = N;
        // 모든 높이 탐색!
        for(int i = 1; i <= H; i++){
            // 장애물 파괴 횟수 계산
            int destruction = (down[H] - down[i-1]) +  (up[1] - up[i+1]);
            // 새로운 장애물 파괴 최소 횟수인 경우
            if(destruction < answer[0]){
                // - answer[0] destruction 으로 변경
                answer[0] = destruction;
                // - answer[1] 1로 변경
                answer[1] = 1;
            // 장애물 파괴 최소 횟수와 동일한 경우 : answer[1] 증가
            }else if(destruction == answer[0]) answer[1]++;
        }

        // 장애물 파괴 최소 횟수, 경우의 수 반환
        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb);
    }
}
