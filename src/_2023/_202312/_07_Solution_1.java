package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1501
// - 만들 수 있는 단어별로 개수를 구하여 계산
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202312/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 단어의 수
        int N = Integer.parseInt(in.readLine());
        // 단어 정보를 담을 Map
        Map<String, Integer> wordMap = new HashMap<>();

        // 단어 입력
        for(int i = 0; i < N; i++){
            String word = in.readLine();
            // 단어의 길이가 3 이상인 경우 : (첫글자)+(끝글자)+(중간 글자 정렬)로 변환
            if(word.length() > 3) word = getWord(word);
            // 단어의 개수 증가
            wordMap.put(word, wordMap.getOrDefault(word, 0)+1);
        }

        // 문장 개수
        int M = Integer.parseInt(in.readLine());
        while(M > 0){
            // 문장을 단어별로 구분
            String[] wordArr = in.readLine().split(" ");
            // 카운트 초기화
            int count = 1;
            // 단어별로 개수를 합하여 계산
            for(String word : wordArr) {
                // 단어 길이가 3 이상인 경우 변환
                if(word.length() > 3) word = getWord(word);
                // 단어의 개수만큼 곱하여 계산 : 없는 경우 0
                count *= wordMap.getOrDefault(word, 0);
            }

            // 단어가 해석될 수 있는 경우의 수 출력
            sb.append(count).append("\n");
            
            // 문장 개수 감소
            M--;
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 단어 변환 함수 : 첫 글자+끝 글자+(중간 글자 정렬) 로 변환
    public static String getWord(String word){
        // StringBuilder 초기화
        StringBuilder w = new StringBuilder();
        // 중간 글자 오름차순 정렬
        char[] sub = word.substring(1, word.length()-1).toCharArray();
        Arrays.sort(sub);
        // 첫 글자 + 끝 글자 + 중간 글자 정렬 차례로 추가
        w.append(word.charAt(0))
                .append(word.charAt(word.length() - 1))
                .append(sub);

        // 변환된 단어 반환
        return w.toString();
    }
}
