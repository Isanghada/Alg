package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/16472
// - 투 포인터
public class _22_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 알파벳 최대 개수
        int N = Integer.parseInt(in.readLine());
        // 문자열
        String word = in.readLine();

        // 사용된 알파벳 Set
        Set<Character> alpSet = new HashSet<>();
        // 사용된 알파벳 개수
        int[] countOfAlp = new int['z'-'a'+1];
        // 시작 인덱스
        int start = 0;
        // 끝 인덱스
        int end = -1;
        
        // 정답 초기화
        int answer = 0;
        while(true){
            end++;
            if(end == word.length()) break;
            alpSet.add(word.charAt(end));
            countOfAlp[word.charAt(end)-'a']++;

            while(alpSet.size() > N){
                countOfAlp[word.charAt(start)-'a']--;
                if(countOfAlp[word.charAt(start)-'a'] == 0) alpSet.remove(word.charAt(start));
                start++;
            }

//            System.out.println(start+", "+end+", "+alpSet);
            answer = Math.max(answer, end - start + 1);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
