package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/31433
// - 그리디 : K, S, A 각 문자로 시작 또는 끝내는 경우 중 삭제하는 문자 개수가 가장 작은 것 선택
public class _08_Solution_1 {
    // KSA 순서
    // - 문제는 3으로 나눈 나머지가 [1, 2, 0] 인 경우 [K, S, A] 이지만
    //   문제 풀이 편의를 위해 [0, 1, 2] 인 경우 [K, S, A]로 변경
    static final char[] KSA = new char[]{'K', 'S', 'A'};
    // 최대값
    static final int MAX = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기 문자열
        char[] word = in.readLine().toCharArray();
        // 문자열 길이
        int len = word.length;

        // 목표 문자열
        for(int i = 0; i < len; i++) sb.append(KSA[i%3]);
        String target = sb.toString();
        sb.setLength(0);

        // 삭제해야하는 문자의 수
        // - 삭제한 만큼 삽입하면 목표 문자열이 되므로 삭제해야하는 문자의 수만 계산
        int removeCount =  MAX;
        // 문자열의 앞에서 부터 탐색하는 경우
        // - K, S, A 각각의 경우 모두 탐색(가능한 경우 까지만!)
        for(int targetIdx = Math.min(2, len-1); targetIdx > -1; targetIdx--){
            removeCount = Math.min(removeCount, frontCheck(word, target, len, 0, targetIdx));
        }

        // 문자열의 뒤에서 부터 탐색하는 경우
        // - K, S, A 3가지 경우 모두 탐색(가능한 경우 까지만!)
        for(int targetIdx = Math.max(len-3, 0); targetIdx < len; targetIdx++){
            removeCount = Math.min(removeCount, backCheck(word, target, len, len-1, targetIdx));
        }

        // 시행 최소 횟수 : (삭제한 문자) + (삽입한 문자) = (삭제한 문자) * 2
        // - 삭제한 만큼 새로운 문자 삽입!
        int answer = removeCount * 2;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static int backCheck(char[] word, String target, int len, int wordIdx, int targetIdx) {
        int count = 0;

        for(; wordIdx > -1 && targetIdx > -1; wordIdx--){
            if(word[wordIdx] == target.charAt(targetIdx)){
                count++;
                targetIdx--;
            }
        }

        return len-count;
    }

    private static int frontCheck(char[] word, String target, int len, int wordIdx, int targetIdx) {
        int count = 0;

        for(; wordIdx < len && targetIdx < len; wordIdx++){
            if(word[wordIdx] == target.charAt(targetIdx)){
                count++;
                targetIdx++;
            }
        }

        return len-count;
    }
}
