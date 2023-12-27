package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/16678
// - 정렬, 그리디
public class _27_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 국회의원의 수
        int N = Integer.parseInt(in.readLine());

        // 국회의원별 명예 점수
        int[] score = new int[N];
        for(int i = 0; i < N; i++) score[i] = Integer.parseInt(in.readLine());

        // 오름차순 정렬
        Arrays.sort(score);

        // 정답 초기화 : 처음 명예 점수를 1로 만들기 위해 필요한 해커 수
        long answer = score[0] -1;
        // 처음 명예 점수 1로 설정
        score[0] = 1;

        // 최소 인원의 해커를 고용하기 위해서는
        // (이전 값+1)과 현재값의 차이만큼 고용하면 된다.
        for(int idx = 1; idx < N; idx++) {
            // 이전값보다 큰 경우 새로운 해커 고용
            if(score[idx] > score[idx-1]) {
                // 현재 값, (이전 값+1)의 차이만큼 증가
                answer += score[idx] - (score[idx - 1] + 1);
                // 현재 값 변경!
                score[idx] = score[idx - 1] + 1;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
