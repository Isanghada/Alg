package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/16719
// - 재귀 : 각 범위별로 최소값을 탐색!
public class _12_Solution_1 {
    // 정답 출력용 변수
    static StringBuilder sb;
    // 알파벳 사용 여부
    static boolean[] isUsed;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        isUsed = new boolean[100];

        // 문자열 입력
        String word = in.readLine();
        // 문자열 크기
        int size = word.length();

        // 재귀를 통해 각 범위 차례로 계산 : 사전순으로 출력하기 위해 오른쪽 범위부터 탐색
        // 1. 범위의 최소값 탐색
        // 2. 최소값 추가 및 출력
        // 3. 오른쪽 범위 재귀
        // 4. 왼쪽 범위 재귀
        zoac(word, 0, size);

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static void zoac(String word, int left, int right) {
        // 모두 탐색한 경우 종료
        if(left == right) return;
        
        // 최소값 초기화
        int idx = left;
        // 최소값 계산
        for(int i = left+1; i < right; i++){
            if(word.charAt(i) < word.charAt(idx)) idx = i;
        }

        // 알파벳 사용 여부 체크
        isUsed[idx] = true;
        // 현재 문자열 출력
        for(int i = 0; i < word.length(); i++){
            if(isUsed[i]) sb.append(word.charAt(i));
        }
        sb.append("\n");

        // 오른쪽 범위 탐색
        zoac(word, idx+1, right);
        // 왼쪽 범위 탐색
        zoac(word, left, idx);
    }
}
