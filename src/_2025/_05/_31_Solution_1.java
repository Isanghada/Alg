package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16925
// - 구현 : 가장 긴 길이의 접두사, 접미사로 후보군을 만들고 가능한 경우 체크
public class _31_Solution_1 {
    static final String P = "P", S = "S";

    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 길이
        int N = Integer.parseInt(in.readLine());

        // 접두사, 접미사 개수
        int count = 2 * N - 2;
        // 접두사, 접미사 입력
        String[] words = new String[count];
        List<String> maxLengthWord = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            words[i] = in.readLine();
            // 최대 길이 접두사, 접미사 추가!
            if(words[i].length() == N-1) maxLengthWord.add(words[i]);
        }

        // 정답 후보군 생성
        String[] answers = new String[2];
        answers[0] = maxLengthWord.get(0) + maxLengthWord.get(1).charAt(N-2);
        answers[1] = maxLengthWord.get(1) + maxLengthWord.get(0).charAt(N-2);

        // 정답 탐색
        for(String answer : answers){
            Set<String> set = new HashSet<>();
            StringBuilder wordType = new StringBuilder();

            for(String fix : words){
                if(answer.indexOf(fix) == 0 && !set.contains(fix)){
                    set.add(fix);
                    wordType.append(P);
                }else if(fix.equals(answer.substring(N - fix.length()))){
                    wordType.append(S);
                }else break;
            }

            if(wordType.length() == count) {
                sb.append(answer).append("\n").append(wordType.toString());
                break;
            }
        }

        // 정답 반환
        System.out.println(sb);
    }
}