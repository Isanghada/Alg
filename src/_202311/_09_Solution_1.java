package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1263
// - 그리디! : 일을 늦게 끝내도 되는 순부터 차례로 계산
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 일의 개수
        int N = Integer.parseInt(in.readLine());

        // 일에 대한 정보
        // - workArr[i][0] : i번째 일이 걸리는 시간
        // - workArr[i][1] : i번째 일을 끝내야 하는 시간
        int[][] workArr = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine(), " ");
            workArr[i] = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };
        }

        // 끝내야 하는 시간 기준 내림차순 정렬
        Arrays.sort(workArr, (o1, o2) -> { return o2[1] - o1[1];});

        // 가장 늦게 끝나도 되는 일의 끝낸 시간으로 초기화
        int answer = workArr[0][1] - workArr[0][0];

        // 남은 모든 일 처리!
        // - workArr[i][1]이하의 시간에 일이 끝나야 하므로
        // - 남은 시간은 answer과 workArr[i][1] 중 최소값 선택
        for(int i = 1; i < N; i++) answer = Math.min(answer, workArr[i][1]) - workArr[i][0];

        // 정답 입력
        // - 0 이상일 경우 answer, 아닐 경우 -1 반환
        sb.append(answer >= 0 ? answer : -1);
        System.out.println(sb);
    }
}
