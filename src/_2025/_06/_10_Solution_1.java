package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/17215
// - 구현 : 1. 각 기회의 기본 점수 계산!
//          2. 스트라이크, 스페어에 따라 마지막 기회 점수 계산
//          3. 각 프레임별로 점수 계산 후 합산! => 스트라이크, 스페어의 경우 추가 점수까지 계산
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 각 기회에 쓰러트린 핀 정보
        char[] bowling = in.readLine().toCharArray();

        // 기회
        int idx = 0;
        // 추가 기회
        int lastChance = 0;
        // 점수 배열
        int[] scores = new int[30];
        // 각 프레임 기준 계산
        for(int frame = 1; frame <= 10; frame++){
            // 스트라이크인 경우 : 10점
            if(bowling[idx] == 'S'){
                scores[idx] = 10;
                // 마지막 프레임인 경우 추가 기회 2회
                if(frame == 10) lastChance = 2;
            }else{
                // 첫 번째 기회 점수
                scores[idx] = (bowling[idx] == '-' ? 0 : bowling[idx] - '0');
                idx++;
                // 두 번째 기회 점수
                if(bowling[idx] == '-') scores[idx] = 0;
                else if(bowling[idx] == 'P') {
                    scores[idx] = 10 - scores[idx-1];
                    // 마지막 프레임인 경우 추가 기회 1회
                    if(frame == 10) lastChance = 1;
                }
                else scores[idx] = bowling[idx] - '0';
            }
            idx++;
        }
        // 추가 기회 점수
        while(lastChance-- > 0){
            if(bowling[idx] == 'S') scores[idx] = 10;
            else if(bowling[idx] == '-') scores[idx] = 0;
            else if(bowling[idx] == 'P') scores[idx] = 10 - scores[idx-1];
            else scores[idx] = bowling[idx] - '0';
            idx++;
        }
        // System.out.println(Arrays.toString(scores));
        
        // 정답 초기화
        int answer = 0;
        // 기회 인덱스
        idx = 0;
        for(int frame = 1; frame <= 10; frame++){
            // 스트라이크인 경우
            if(scores[idx] == 10) {
                answer += scores[idx] + scores[idx+1] + scores[idx+2];
                idx++;
            // 스페어인 경우
            }else if(scores[idx] + scores[idx+1] == 10){
                answer += 10 + scores[idx+2];
                idx += 2;
            // 아닌 경우! ㅇㅅㅇ
            }else{
                answer += scores[idx] + scores[idx+1];
                idx += 2;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
