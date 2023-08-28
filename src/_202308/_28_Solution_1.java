package _202308;

// https://school.programmers.co.kr/learn/courses/30/lessons/131129
// - DP를 활용해서 풀이 : 각 점수의 상태를 차례로 구해가면 해결
// - 싱글(X1), 더블(X2), 트리플(X3), 불(50점)
// - 1 ~ 20점
public class _28_Solution_1 {
    // 점수별 상태를 담을 클래스
    public class Score{
        int countDart;  // 사용한 다트의 수
        int countSingleBull;    // 싱글과 불을 합
        public Score(int countDart, int countSingleBull){
            this.countDart = countDart;
            this.countSingleBull = countSingleBull;
        }
    }

    // Score를 static 배열로 선언
    public static Score[] dp;
    public int[] solution(int target) {
        // 정답 초기화
        int[] answer = new int[2];
        
        // dp 초기화
        dp = new Score[target+1];
        for(int idx = 0; idx <= target; idx++){
            dp[idx] = new Score(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // 0인 상태 설정
        dp[0].countDart = 0;
        dp[0].countSingleBull = 0;

        // 각 점수에서 얻을 수 있는 모든 점수 탐색하며 진행
        // - curScore : 현재 점수
        // - dart : 과녁의 수 ( 0은 불을 의미 )
        // - num : 싱글, 더블, 트리플
        for(int curScore = 0; curScore < target; curScore++){
            for(int dart = 0; dart <= 20; dart++){
                // 불일 경우 현재 점수에 50을 더하여 새로운 값 생성
                // - target을 넘어선다면 패스
                if (dart == 0){
                    int next = curScore + 50;
                    if(next > target) continue;
                    // 다음 점수 변경
                    calScore(curScore, next, 1);
                }else{
                    // 현재 점수에 dart를 차례로 더하며 싱글, 더블, 트리플일 경우 탐색
                    // - target을 넘어선다면 종료
                    int next = curScore + dart;
                    for(int num = 1; num <= 3; num++){
                        if(next > target) break;
                        // 다음 점수 변경
                        calScore(curScore, next, (num == 1 ? 1 : 0));
                        // 다음 점수에 dart를 더해 싱글, 더블, 트리플 순으로 계산
                        next += dart;
                    }
                }
            }
        }

        // 모든 계산이 끝난 후 정답에 target 점수의 상태 입력 후 반환
        answer[0] = dp[target].countDart;
        answer[1] = dp[target].countSingleBull;
        return answer;
    }

    public void calScore(int origin, int next, int isSigleBull){
        Score nextScore = new Score(dp[origin].countDart + 1, dp[origin].countSingleBull + isSigleBull);
        if(dp[next].countDart > nextScore.countDart){
            dp[next].countDart = nextScore.countDart;
            dp[next].countSingleBull = nextScore.countSingleBull;
        }else if(dp[next].countDart == nextScore.countDart){
            dp[next].countSingleBull = Math.max(dp[next].countSingleBull, nextScore.countSingleBull);
        }
    }
}
