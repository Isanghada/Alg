package _2023._202309;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/150365?language=java
// - DFS를 활용하는 방식!
// - 가지치기 조건 파악이 중요
// - 주의할 점 : 사전순으로 계산하면 정답이 한번만 나오면 끝! <- 어려웠음
public class _08_Solution_1 {
    // 이동 방향!
    public static Map<String, int[]> DELTA = new HashMap<>();
    // 이동 순서 : 사전순으로 탐색하기 위해 오름차순으로 탐색
    public static String[] ORDER = new String[] {"d", "l", "r", "u"};
    // 격자의 경계값을 담을 배열
    public static int[] BOUNDARY;
    // 도착 지점을 담을 배열
    public static int[] END;
    // 이동 횟수를 담을 변수
    public static int K;
    // 정답
    public static String answer;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // 정답 초기화
        answer = "";

        // 경계값 : 인덱스가 1부터 시작하므로 +1씩 설정
        BOUNDARY = new int[] {n+1, m+1};
        // 도착 지점
        END = new int[] {r, c};
        // 이동 횟수
        K = k;

        // 이동 방향 설정
        // - key : 이동 명령
        // - value : 이동 좌표(행 좌표, 열 좌표)
        DELTA.put("l", new int[] {0, -1});
        DELTA.put("r", new int[] {0, 1});
        DELTA.put("u", new int[] {-1, 0});
        DELTA.put("d", new int[] {1, 0});

        // 최소 이동 거리 계산
        int minDistance = Math.abs(x - r) + Math.abs(y - c);
        // 이동 횟수와 최소 이동 거리의 차이 계산
        int diff = K - minDistance;
        // 아래의 경우 불가능한 경우
        // - 이동 거리가 K보다 길 경우
        // - 차이(diff)가 홀수인 경우 (다른 방향으로 이동 후 다시 원래 방향으로 돌아오는 과정이 (2의 배수)만큼 이동)
        if(diff < 0 || diff % 2 != 0) answer = "impossible";
        else{
            // 사전순 가장 빠른 경로 탐색
            getMinResult(x, y, "");
        }

        return answer;
    }

    // 사전순 가장 빠른 경로 탐색 함수 : DFS 활용! 사전순으로 이동하므로 정답이 한 번 나오면 끝!
    // - x : 현재 행 좌표
    // - y : 현재 열 좌표
    // - value : 현재까지의 명령어
    private void getMinResult(int x, int y, String value) {
        // 모든 이동 횟수가 끝난 경우!
        if(K == value.length()){
            // 도착 지점일 경우 정답 업데이트!
            if(x == END[0] && y == END[1]) answer = value;
        }else{
            // 이동 순서에 따라 이동(사전순) : d, l, r, u
            for(String o : ORDER){
                // 이동 좌표
                int[] delta = DELTA.get(o);
                // 다음 좌표 계산
                int[] next = new int[] {x + delta[0], y + delta[1]};

                // 도착 지점까지의 최소 이동 거리 계산
                int diff = Math.abs(END[0] - x) + Math.abs(END[1] - y);
                // 남은 이동 횟수 계산
                int k = K - value.length();
                // 아래의 경우 패스
                // - 행 좌표를 벗어나는 경우
                // - 열 좌표를 벗어나는 경우
                // - 최소 이동 거리가 남은 이동 횟수보다 많은 경우
                // - 최소 이동 거리와 남은 이동 횟수가 동일한 상태(홀, 짝)이 아닌 경우
                if(next[0] <= 0 || next[0] >= BOUNDARY[0] ||
                next[1] <= 0 || next[1] >= BOUNDARY[1] ||
                diff > k || (diff % 2 != k % 2)) continue;

                // 재귀 : 다음 좌표, 현재 명령어 + o(다음 명령어)
                getMinResult(next[0], next[1], value+o);
                // 정답이 나왔을 경우 종료!
                if(!answer.equals("")) return;
            }
        }
    }
}
