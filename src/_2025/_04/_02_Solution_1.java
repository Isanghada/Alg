package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/21941
// - DP : 각 위치에서 가능한 모든 문자열을 제거한 결과 중 최대값 선택
public class _02_Solution_1 {
    // 제거 문자열 클래스
    static class RemoveWord{
        String word;    // 제거 문자열
        int score;      // 점수
        public RemoveWord(String word, int score){
            this.word = word;
            this.score = score;
        }
        @Override
        public String toString(){
            return this.word+"="+this.score;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기 문자열 : 쉬운 계산을 위해 인덱스 0 위치에 의미없는 값 추가
        String S = "0"+in.readLine();

        // 부분 문자열 개수
        int M = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        // 중복 제거를 위해 map 활용
        Map<String, Integer> removeWord = new HashMap<>();
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            String A = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            // 점수를 많이 얻을 수 있는 경우만 추가
            // - 점수가 부분 문자열의 길이 초과인 경우
            // - 초기 문자열의 길이 이하인 경우
            if(A.length() < score && A.length() <= S.length() - 1){
                // 기존 점수가 더 높을 경우 패스
                if(removeWord.containsKey(A) && removeWord.get(A) >= score) continue;
                removeWord.put(A, score);
            }
        }

        // 중복 제거한 Map을 Lsist로 변환
        List<RemoveWord> removeList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : removeWord.entrySet()){
            removeList.add(new RemoveWord(entry.getKey(), entry.getValue()));
        }

        int sSize = S.length() - 1;
        int listSize = removeList.size() ;
        int[] dp = new int[sSize+1];
        for(int i = 1; i <= sSize; i++) dp[i] = i;

        // 문자열 모든 부분 탐색
        for(int s = 1; s <= sSize; s++){
            // 하나의 문자를 지우는 경우 중 최대값 갱신
            dp[s] = Math.max(dp[s], dp[s-1]+1);
            // 부분 문자열을 탐색하여 최대값 갱신
            for(int remove = 0; remove < listSize; remove++){
                RemoveWord cur = removeList.get(remove);
                // 부분 문자열이 존재할 경우 최대값 갱신
                if(cur.word.length() <= s && check(S, s, cur)){
                    dp[s] = Math.max(dp[s], dp[s-cur.word.length()]+cur.score);
                }
            }
        }

        int answer = dp[sSize];

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    // 문자열 체크 함수
    private static boolean check(String s, int sIdx, RemoveWord remove) {
        // sIdx를 마지막 인덱스로 remove와 비교!
        String sub = s.substring(sIdx-remove.word.length()+1, sIdx+1);
        if(sub.equals(remove.word)) return true;
        return false;
    }
}
