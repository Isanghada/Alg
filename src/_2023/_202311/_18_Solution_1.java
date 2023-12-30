package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16938
// - 가능한 모든 조합 확인!
// - 모든 조합을 확인하여 조건을 만족하면 경우의 수 증가
public class _18_Solution_1 {
    public static int[] A;  // 문제 난이도 배열
    // N : 문제의 수
    // L : 문제 난이도 합의 하한
    // R : 문제 난이도 합의 상한
    // X : 가장 어려운 문제와 가장 쉬운 문제의 최소 차이
    // answer : 문제를 고르는 방법의 수
    public static int N, L, R, X, answer;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 기본 정보 입력
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 문제 난이도 입력
        A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 정답 초기화
        answer = 0;
        // 각 문제를 기준으로 가능한 모든 경우의 수 탐색 : 재귀
        for(int i = 0; i < N; i++){
            backtracking(1, i+1, A[i], A[i], A[i]);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // 가능한 문제를 찾는 함수 : 재귀를 통해 모든 경우 탐색
    // - step : 선택한 문제의 수
    // - index : 문제를 고를 최소 인덱스
    // - total : 난이도의 총합
    // - min : 난이도의 최소
    // - max : 난이도의 최대
    private static void backtracking(int step, int index, int total, int min, int max) {
        // 난이도의 차이 계산
        int diff = max - min;
        // 2 문제 이상, L이상 R 미만의 난이도 합, 난이도 차이가 X 이상인 경우 정답 증가!
        if(step >= 2 && total >= L && total <= R && diff >= X) answer++;
        // index부터 각 문제를 선택할 경우 탐색
        for(int i = index; i < N; i++){
            backtracking(step+1, i+1, total+A[i], Math.min(min, A[i]), Math.max(max, A[i]));
        }
    }
}
