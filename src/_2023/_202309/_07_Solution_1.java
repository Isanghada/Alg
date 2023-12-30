package _2023._202309;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://school.programmers.co.kr/learn/courses/30/lessons/1832
// - 정규 표현식 활용 : 어렵넹.. 너무 많아!!!!! 그래도 봐야지
// - https://adjh54.tistory.com/104
// - https://hbase.tistory.com/160
public class _07_Solution_1 {
    // 페이지 상태 정보 클래스
    public class Page{
        int index;  // 인덱스
        double point;   // 매칭 점수
        double linkPoint;   // 링크 점수
        List<String> extraUrl;  // 외부 링크

        public Page(int index, double point, double linkPoint, List<String> extraUrl){
            this.index = index;
            this.point = point;
            this.linkPoint = linkPoint;
            this.extraUrl = extraUrl;
        }
    }
    public int solution(String word, String[] pages) {
        // 정답 초기화
        int answer = 0;
        
        // 검색 단어 소문자로 변환
        word = word.toLowerCase();

        // 페이지를 키로 가지고 상태를 값으로 가지는 map 초기화
        Map<String, Page> map = new HashMap<>();

        // 정규 표현식 활용
        // - 홈페이지 URL 패턴 : URL은 모두 다르기 때문에 (\\S)*로 공백과 탭이 아닌 경우만!
        Pattern homeUrlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S)*\"");
        // - 외부링크 URL 패턴 : URL은 모두 다르기 때문에 (\\S)*로 공백과 탭이 아닌 경우만!
        Pattern linkUrlPattern = Pattern.compile("<a href=\"(\\S)*\"");
        // - 검색 단어 패턴 : \\b로 경계 확인 => 이게 뭔지 잘 모르겟음..(?i)는 왜 들어가지
        Pattern wordPattern = Pattern.compile("\\b(?i)"+word+"\\b");
        
        // 모든 페이지별로 확인
        for(int idx = 0; idx < pages.length; idx++){
            // 홈페이지 Matcher
            Matcher homeUrlMatcher = homeUrlPattern.matcher(pages[idx]);
            // 외부 링크 Matcher
            Matcher linkUrlMatcher = linkUrlPattern.matcher(pages[idx]);

            // 홈페이지 URL 추출
            String homeUrl = null;
            if(homeUrlMatcher.find()) {
                // URL만 담을 수 있도록 split, replace 활용
                homeUrl = homeUrlMatcher.group().split("content=")[1].replaceAll("\"", "");
                // System.out.println(homeUrl);
            }

            // 외부 링크 URL 추출
            List<String> extraList = new ArrayList<>();
            while(linkUrlMatcher.find()){
                // URL만 담을 수 있도록 split, replace 활용
                extraList.add(linkUrlMatcher.group().split("href=")[1].replaceAll("\"", ""));
            }

            // 검색 단어 등장 횟수 체크
            int wordCount = 0;
            // body에서 텍스트만 남기도록 설정
            String body = pages[idx].toLowerCase().split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " ");
            // 단어 등장 횟수 체크
            Matcher bodyMatcher = wordPattern.matcher(body);
            while(bodyMatcher.find()) wordCount++;

            // 결과들을 토대로 정보 저장
            map.put(homeUrl, new Page(idx, wordCount, ((double) wordCount) / extraList.size(), extraList));
        }

        // 연결된 외부 링크에 링크 점수 추가!
        for(Page page : map.values()){
            for(String extraUrl : page.extraUrl){
                // 해당 외부 링크가 있을 경우만
                if(map.containsKey(extraUrl)){
                    map.get(extraUrl).point += page.linkPoint;
                }
            }
        }

        // 최대 매칭 점수를 유지하며 최대값 탐색
        double maxPoint = 0;
        for(Page page : map.values()){
            // 새로운 최대값일 경우 : answer(인덱스), maxPoint 변경
            if(page.point > maxPoint){
                answer = page.index;
                maxPoint = page.point;
            // 최대 점수와 동일한 경우 : 인덱스를 최소값으로 변경
            }else if(page.point == maxPoint){
                answer = Math.min(answer, page.index);
            }
        }
        
        return answer;
    }
}
