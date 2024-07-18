package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/11509
// - 그리디 : 차례대로 필요한 높이에서 화살을 쏘며 진행!
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 풍선의 개수
        int N = Integer.parseInt(in.readLine());
        // 풍선 높이 정보
        int[] H = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 높이별 화살 개수
        int[] countOfArrow = new int[1_000_001];
        // 화살 개수
        int answer = 0;
        // 왼쪽부터 차례로 탐색
        for(int i = 0; i < N; i++){
            // 현재 풍선 높이에 화살이 있는 경우! : 현재 높이의 화살 개수 감소!
            if(countOfArrow[H[i]] > 0) countOfArrow[H[i]]--;
            // 현재 풍선 높이에 화살이 없는 경우 ; 화살 개수 증가!
            else answer++;
            // 풍선을 터트렸으므로 화살 높이 감소
            countOfArrow[H[i]-1]++;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
