package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/24524
// - 그리디 : 각 문자의 가능한 개수를 체크하며 탐색
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] S = in.readLine().toCharArray(); // 선물받은 문자열 S
        char[] T = in.readLine().toCharArray(); // 아름다운 문자열 T

        // T의 구성 체크용 map
        Map<Character, Integer> map = new HashMap<>();
        for(int idx = 0; idx < T.length; idx++) map.put(T[idx], idx);

        // 각 알파벳 개수 초기화
        // - T 문자열은 모두 다르므로 최대 길이가 26.
        // - 모두 다른 문자이므로 현재 알파벳 개수가 앞 알파벳의 개수보다 많을 수 없음.
        int[] count = new int[26];
        // S의 순서에 따라 개수 체크!
        for(char alp : S){
            // 아래의 경우 패스
            // - T에 포함되지 않은 알파벳일 경우
            // - 현재 알파벳을 추가할 수 없는 경우
            if(!map.containsKey(alp) || !check(count, T, map.get(alp))) continue;
            // 현재 알파벳 개수 증가!
            count[alp-'a']++;
        }

        // 결과 반환
        // - 마지막 알파벳의 개수 반환
        sb.append(count[T[T.length-1]-'a']);
        System.out.println(sb);
    }
    // 알파벳 개수 체크 함수 : T[idx] 알파벳 개수와 T[idx-1] 알파벳 개수 비교
    private static boolean check(int[] count, char[] t, int idx) {
        // 첫 알파벳인 경우 가능
        if(idx == 0) return true;
        // 현재 알파벳, 이전 알파벳 개수를 비교하여 반환
        return count[t[idx] - 'a'] < count[t[idx-1] - 'a'];
    }
}
