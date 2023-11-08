package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
// https://www.acmicpc.net/problem/2922
// - 백트래킹 : 모든 경우의 수 체크
// - 값을 하나씩 넣어보며 경우의 수 게산!
public class _08_Solution_1 {
    // 입력된 문자열
    public static String S;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 입력
        S = in.readLine();

        // 정답 입력
        sb.append(backtracking(0, 0, 0, false));
        System.out.println(sb);
    }

    // 즐거운 단어 경우의 수 계산 함수
    // - step : 확인한 인덱스
    // - ja : 자음 연속 개수
    // - mo : 모음 연속 개수
    // - isLExist : L 포함 여부
    private static long backtracking(int step, long ja, long mo, boolean isLExist) {
        // 자음, 모음이 3개 이상 연속된 경우 0 반환
        if(mo >= 3 || ja >= 3) return 0;
        // 단어의 끝까지 확인한 경우 : L 여부에 따라 1, 0 반환
        if(step == S.length()) return isLExist ? 1 : 0;

        // 경우의 수!
        long count = 0;
        // 현재 체크할 문자
        char cur = S.charAt(step);
        // 밑 줄인 경우
        if(cur== '_'){
            // 자음을 추가할 경우의 수 증가
            count += backtracking(step+1, ja+1, 0, isLExist) * 20;
            // 모음을 추가할 경우의 수 증가
            count += backtracking(step+1, 0, mo+1, isLExist) * 5;
            // L을 추가할 경우의 수 증가
            count += backtracking(step+1, ja+1, 0, true);
        // 모음인 경우, 자음인 경우, L인 경우의 수 증가!
        }else if(cur == 'A' || cur == 'E' || cur == 'I' || cur == 'O' || cur == 'U'){
            count += backtracking(step+1, 0, mo+1, isLExist);
        }else if(cur == 'L') count += backtracking(step+1, ja+1, 0, true);
        else count += backtracking(step+1, ja+1, 0, isLExist);

        // 경우의 수 반환
        return count;
    }
}
