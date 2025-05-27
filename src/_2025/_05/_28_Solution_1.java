package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/13265
// - DP : Map을 사용하여 각 좌표에 도달할 수 있는 경우의 수 계산
public class _28_Solution_1 {
    // DP 정의
    static Map<Integer, Map<Integer, Map<Integer, Long>>> DP;
    // 이동 변수
    static final int[][] DELTA = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // DP 초기화
        DP = new HashMap<>();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 이동 횟수
            int N = Integer.parseInt(in.readLine());
            // 초기 좌표 (0, 0)에 N번의 이동으로 돌아오는 경우 반환
            sb.append(getDP(0, 0, N)).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static long getDP(int x, int y, int move) {
        // move == 0 인 경우 : 움직이지 못하는 경우
        // - 좌표가 첫 위치(0, 0)인 경우 1 아닌 경우 0 반환
        if(move == 0) return (x == 0 && y == 0) ? 1 : 0;

        // 이미 계산된 경우 : 값 반환
        if(DP.containsKey(x) && DP.get(x).containsKey(y) && DP.get(x).get(y).containsKey(move)){
            return DP.get(x).get(y).get(move);
        }

        // 초기화되지 않은 경우 초기화!
        if(!DP.containsKey(x)) DP.put(x, new HashMap<>());
        if(!DP.get(x).containsKey(y)) DP.get(x).put(y, new HashMap<>());
        if(!DP.get(x).get(y).containsKey(move)) DP.get(x).get(y).put(move, 0L);

        // 초기값 설정
        long count = DP.get(x).get(y).get(move);
        // 도달할 수 있는 모든 경우 체크
        for(int[] d : DELTA){
            int nextX = x + d[0];
            int nextY = y + d[1];

            count += getDP(nextX, nextY, move-1);
        }

        // 이동하는 경우의 수 입력
        DP.get(x).get(y).put(move, count);
        // 이동하는 경우의 수 반환
        return DP.get(x).get(y).get(move);
    }
}
