package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/2166
// - 투 포인터 : 투 포인터로 가능한 범위를 체크하며 최대 길이 탐색
public class _19_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            // 작동하는 키의 수
            int m = Integer.parseInt(in.readLine());

            // 마지막 입력인 경우 종료
            if(m == 0) break;
            
            // 문장 -> char 배열 변환
            char[] sentence = in.readLine().toCharArray();

            int answer = 0; // 정답 초기화
            int left = 0;   // 범위 시작
            int right = 0;  // 범위 끝

            // 문자 체크용 map
            Map<Character, Integer> map = new HashMap<>();
            while(right < sentence.length && left <= right){
                // 새로우 문자를 추가할 수 없는 경우 : left 조정 -> 문자 삭제
                if(!map.containsKey(sentence[right]) && map.size() == m){
                    map.put(sentence[left], map.get(sentence[left])-1);
                    // 문자 개수가 0인 경우 map에서 삭제
                    if(map.get(sentence[left]) == 0) map.remove(sentence[left]);
                    left++;
                }else{
                    // 새로운 문자인 경우 map에 추가
                    if(!map.containsKey(sentence[right])) map.put(sentence[right], 0);
                    map.put(sentence[right], map.get(sentence[right])+1);
                    answer = Math.max(answer, right-left+1);
                    right++;
                }
            }

            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
