package _202310;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/17685
// - 모든 단어를 Tree 형식으로 변환!
// - root부터 차례로 가능한 단어 탐색!
// - 1개의 단어만 만들 수 있거나 마지막 글자인 단어가 있으면 정답 증가
public class _06_Solution_1 {
    // 추천 단어 클래스
    class Word{
        List<String> posibleList;   // 가능한 단어 리스트
        boolean isEnd;  // 마지막 글자인 단어 여부
        Map<Character, Word> childMap;  // 다음 단어에 대한 추천 단어 Map

        public Word(){
            this.posibleList = new ArrayList<>();
            this.isEnd = false;
            this.childMap = new HashMap<>();
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            return sb.append("[").append(posibleList.toString()).append(", ").append(isEnd).append(", ").append(childMap.keySet()).append("]").toString();
        }
    }
    
    // 정답 선언
    public static int answer;
    // ROOT Map 선언
    public static Map<Character, Word> ROOT;
    public int solution(String[] words) {
        // 정답 초기화
        answer = 0;

        // map 초기화
        ROOT = new HashMap<>();
        // 모든 단어에 대해 Tree 생성
        for(String word : words){
            // 마지막 인덱스 계산
            int last = word.length() - 1;
            // 루트 노드부터 탐색
            Map<Character, Word> cur = ROOT;
            // 단어의 모든 글자에 대해 Tree 생성
            for(int idx = 0; idx <= last; idx++){
                // 현재 글자
                char ch = word.charAt(idx);
                // 연결된 child가 없는 경우 추가
                if(!cur.containsKey(ch)) cur.put(ch, new Word());
                // 가능한 단어 리스트에 현재 단어 추가
                cur.get(ch).posibleList.add(word);
                // 마지막 글자인 경우 isEnd 변경
                if(idx == last) cur.get(ch).isEnd = true;
                // chlid map으로 변경
                cur = cur.get(ch).childMap;
            }
        }

        // 모든 루트 글자에 대해 탐색
        for(char root : ROOT.keySet()){
            // root부터 시작! 글자 개수 1부터 시작!
            getMinCount(ROOT.get(root), 1);
        }

        return answer;
    }

    // 최소값 글자수 계산 함수 : DFS 방식으로 가능한 영역 모두 탐색
    private void getMinCount(Word word, int count) {
        // 가능한 단어가 1개이거나 마지막 글자인 경우 정답을 count만큼 증가
        if(word.posibleList.size() == 1 || word.isEnd) answer += count;
        // 가능한 단어가 1개가 아닌 경우!
        if(word.posibleList.size() != 1){
            // 가능한 모든 child에 대해 탐색 : count를 1만큼 증가!
            for(char next : word.childMap.keySet()) getMinCount(word.childMap.get(next), count+1);
        }
    }
}
