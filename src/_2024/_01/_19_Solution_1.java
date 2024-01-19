package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3980
// - DFS : 모든 경우를 탐색하며 최대값, 최소값 계산
public class _19_Solution_1 {
    // N : 지도의 크기
    // MAX : 최대값
    // MIN : 최소값
    public static int N, MAX, MIN;
    public static char[][] MAP; // 지도 정보
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());    // 지도 크기 입력

        StringTokenizer st = null;
        MAP = new char[N][N];       // 지도 초기화
        // 지도 정보 입력
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++) MAP[r][c] = st.nextToken().charAt(0);
        }

        MAX = Integer.MIN_VALUE;    // 최대값 초기화
        MIN = Integer.MAX_VALUE;    // 최소값 초기화

        // DFS를 통해 모든 경우 탐색
        dfs(0, 0, MAP[0][0]-'0', MAP[0][0]);

        // 정답 반환
        sb.append(MAX).append(" ").append(MIN);
        System.out.println(sb);
    }
    // DFS 탐색 함수
    // - r : 행
    // - c : 열
    // - pre : 이전까지의 연산값
    // - operand : 연산자
    private static void dfs(int r, int c, int pre, char operand) {
        // 현재 위치의 값
        int cur = MAP[r][c] - '0';
        // 숫자인 경우 : operand로 연산!
        if(cur >= 0 && cur <= 5){
            if(operand == '+') pre += cur;
            else if(operand == '-') pre -= cur;
            else if(operand == '*') pre *= cur;
        // 연산자인 경우 : operand 변경
        }else operand = MAP[r][c];

        // 도착 지점인 경우 : MAX, MIN 업데이트
        if(r == N-1 && c == N-1){
            MAX = Math.max(MAX, pre);
            MIN = Math.min(MIN, pre);
        }

        // 행, 열로 추가 이동!
        if(r < N-1) dfs(r+1, c, pre, operand);
        if(c < N-1) dfs(r, c+1, pre, operand);
    }
}
