package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1027
// - 브루트포스 : 각 빌딩을 기준으로 양방향으로 모든 건물의 기울기를 계산해 겹치지 않는 경우 계산
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 건물의 수
        int N = Integer.parseInt(in.readLine());
        // 건물 높이 정보
        int[] buildings = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 정답 초기화
        int answer = 0;
        // 각 건물 중 최대값 선택
        for(int n = 0; n < N; n++) answer = Math.max(answer, getCountOfBuildings(buildings, n));

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static int getCountOfBuildings(int[] buildings, int n) {
        int count = 0;

        // 왼쪽 탐색
        double minSlope = Integer.MAX_VALUE;
        for(int left = n-1; left >= 0; left--){
            double slope = ((double)buildings[left] - buildings[n]) / (left - n);
            if(minSlope > slope){
                count++;
                minSlope = slope;
            }
        }

        // 오른쪽 탐색
        double maxSlope = Integer.MIN_VALUE;
        for(int right = n+1; right < buildings.length; right++){
            double slope = ((double)buildings[right] - buildings[n]) / (right - n);
            if(maxSlope < slope){
                count++;
                maxSlope = slope;
            }}

        return count;
    }
}
