package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/10835
// - DP : 왼쪽, 오른쪽 카드를 사용 범위 내에서 최대값을 차레로 계산
public class _14_Solution_1 {
    public static int N;
    public static int[] LEFT, RIGHT;
    public static int[][] DP;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 카드 개수
        N = Integer.parseInt(in.readLine());
        // 왼쪽 카드
        LEFT = Arrays.stream((in.readLine()).split(" ")).mapToInt(Integer::new).toArray();
        // 오른쪽 카드
        RIGHT = Arrays.stream((in.readLine()).split(" ")).mapToInt(Integer::new).toArray();

        // dp 초기화
        DP = new int[N][N];
        for(int r = 0; r < N; r++) Arrays.fill(DP[r], -1);

        // 정답 반환
        // - 모든 카드를 사용하여 최대값 계산
        sb.append(getDP(0, 0));
        System.out.println(sb);
    }
    // DP 함수 : 차례로 계산!
    private static int getDP(int left, int right) {
        // 범위를 벗어나는 경우 0 반환
        if(left == N || right == N) return 0;

        // 값이 계산된 경우 : 값 반환
        if(DP[left][right] != -1) return DP[left][right];

        // 왼쪽 카드를 버리는 경우, 왼쪽과 오른쪽 카드를 버리는 경우 중 최대값 반환!
        DP[left][right] = Math.max(getDP(left+1, right), getDP(left+1, right+1));
        // 왼쪽 카드 값이 큰 경우 오른쪽 카드를 버리는 경우 중 최대값 반환
        if(LEFT[left] > RIGHT[right]) {
            DP[left][right] = Math.max(DP[left][right],
                                       getDP(left, right + 1) + RIGHT[right]);
        }
        // 계산된 값 반환
        return DP[left][right];
    }
}

