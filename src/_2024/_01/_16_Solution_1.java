package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2195
// - 그리디 : P의 첫 문자부터 차례로 S와 비교하여 가능한 만큼 복사
public class _16_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String S = in.readLine();   // 원본 문자열
        String P = in.readLine();   // 타겟 문자열

        int answer = 1; // 최소 1번의 복사 필요
        int idx = 0;    // P에서 복사 시작할 인덱스
        // P 기준으로 모든 문자 탐색
        for(int i = 0; i < P.length(); i++){
            // (idx ~ i+1) 범위가 S에 있는 경우 패스
            if(S.contains(P.substring(idx, i+1))) continue;
            // 없을 경우 : 이전까지 범위 복사 => 정답 증가
            answer++;
            // P에서 복사 시작할 인덱스 변경
            idx = i;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
