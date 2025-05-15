package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25825
// - 백트래킹 : 전송할 그룹을 선택하여 가능한 경우 모두 탐색
public class _16_Solution_1 {
    // TIME_TABLE : 메시지 전달 시간 배열
    // GROUP_TABLE : 그룹 구성 배열
    static int[][] TIME_TABLE, GROUP_TABLE = new int[][] {{0, 0}, {1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}};
    // BIT : 비트 배열
    static int[] BIT;
    // FRIEND : 친구 최대 번호
    // GROUP : 그룹 최대 번호
    // MAX : 최대 전송 시간
    static final int FRIEND = 12, GROUP = 6, MAX = 1_000_000;
    // ANSWER: 정답
    static int ANSWER;

    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // BIT 계산
        BIT = new int[FRIEND+1];
        BIT[0] = 1;
        for(int i = 1; i <= FRIEND; i++) BIT[i] = BIT[i-1] << 1;
        // System.out.println(Arrays.toString(BIT));

        // 전송 시간 입력
        TIME_TABLE = new int[FRIEND+1][FRIEND+1];
        StringTokenizer st = null;
        for(int i = 1; i <= FRIEND; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 1; j <= FRIEND; j++) TIME_TABLE[i][j] = Integer.parseInt(st.nextToken());
        }

        // 정답 초기화
        ANSWER = MAX;
        // 백트래킹을 통해 모든 경우 탐색
        backtracking(0, 0, 0, 0);

        // 정답 출력
        sb.append(ANSWER);
        System.out.println(sb);
    }

    private static void backtracking(int step, int end, int bitmask, int time) {
        // 현재 ANSWER 보다 오래 걸리는 경우 패스
        if(ANSWER <= time) return;
        // 모든 그룹에 전달된 경우 ANSWER 갱신
        if(step == GROUP) ANSWER = Math.min(ANSWER, time);
        else{
            // 모든 그룹 탐색
            for(int group = 1; group <= GROUP; group++){
                // 이미 전송한 그룹은 패스
                if((bitmask & BIT[group]) > 0) continue;
                // 현재 그룹 정보
                int[] g = GROUP_TABLE[group];
                // g[1] -> g[0] 순서인 경우
                backtracking(step+1, g[0], bitmask | BIT[group], time + getTime(end, g[1], g[0]));
                // g[0] -> g[1] 순서인 경우
                backtracking(step+1, g[1], bitmask | BIT[group], time + getTime(end, g[0], g[1]));
            }
        }
    }

    private static int getTime(int start, int mid, int end) {
        return TIME_TABLE[start][mid] + TIME_TABLE[mid][end];
    }
}
