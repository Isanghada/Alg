package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2179
// - 집합 : 입력된 순서대로 공통된 접두사에 따라 탐색!
public class _21_Solution_1 {
    public static int maxSamePrefixLength;  // 최대 공통 접두사 길이
    public static String S, T;              // 단어
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 단어 개수
        int N = Integer.parseInt(in.readLine());
        // 단어 입력 : 중복 제거
        // - 순서를 지키기 위해 LinkedHashSet 활용
        Set<String> wordSet = new LinkedHashSet<>();
        while(N-- > 0) wordSet.add(in.readLine());

        // 단어 리스트
        List<String> wordList = new ArrayList<>(wordSet);

        // 초기값 설정
        maxSamePrefixLength = -1;
        S = "";
        T = "";

        // 접두사 처음부터 차례로 탐색
        findMaxSamePrefix(wordList, 0);

        sb.append(S).append("\n").append(T);

        // 정답 출력
        System.out.println(sb);
    }

    private static void findMaxSamePrefix(List<String> wordList, int samePrefixLength) {
        // 공통 접두사별 단어 Map
        // - key : 접두사 알파벳
        // - value : 공통된 접두사를 가진 단어 리스트
        Map<Character, List<String>> samePrefixMap = new LinkedHashMap<>();

        // 최대 공통 접두사 갱신!
        if(samePrefixLength > maxSamePrefixLength){
            maxSamePrefixLength = samePrefixLength;
            // 입력 순서 기준 빠른 값을 출력해야하므로 인덱스 0, 1로 입력
            S = wordList.get(0);
            T = wordList.get(1);
        }

        // 단어 리스트를 기준으로 공통 접두사 체크!
        for(String word : wordList){
            // 최대 공통 접두사 길이보다 긴 경우만 탐색
            if(word.length() > samePrefixLength){
                // 접두사 알파벳
                char alp = word.charAt(samePrefixLength);
                // map에 없는 경우 추가!
                if(!samePrefixMap.containsKey(alp)) samePrefixMap.put(alp, new ArrayList<>());
                // 단어 추가
                samePrefixMap.get(alp).add(word);
            }
        }

        // 각 경우 모두 탐색
        for(List<String> nextList : samePrefixMap.values()){
            // 1개 이상의 단어를 가진 경우만 재귀 진행
            if(nextList.size() > 1) findMaxSamePrefix(nextList,  samePrefixLength+1);
        }
    }
}