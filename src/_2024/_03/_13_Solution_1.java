package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22981
// - 정렬 : 한 팀의 속도는 무조건 가장 느린 작업 속도를 가지므로, 다른 팀의 속도를 기준으로 최대값 탐색
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long N = Long.parseLong(st.nextToken());    // 사람의 수
        long K = Long.parseLong(st.nextToken());    // 옮겨야 하는 상자의 수
        // 작업 속도 배열
        long[] V = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::new).toArray();
        // 오름차순 정렬
        Arrays.sort(V);

        // 정답 초기화
        long answer = Long.MAX_VALUE;
        for(int idx = 1; idx < N; idx++){
            // 구성된 팀의 작업 속도
            // - Team1 : V[0]를 작업 속도로 가지는 팀
            // - Team2 : V[idx]를 작업 속도로 가지는 팀
            long Z = (V[0] * idx) + (V[idx] * (N - idx));
            // 현재 정답과 새로운 팀의 경우 중 최소값 선택
            answer = Math.min(answer, K / Z + (K % Z == 0 ? 0 : 1));
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
