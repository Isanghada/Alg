package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/2651
// - DP : 각 포인트별로 도착할 수 있는 최소 시간 탐색
public class _31_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 최대 이동 거리
        int maxDistance = Integer.parseInt(in.readLine());
        // 정비소 개수
        int countOfRepairShop = Integer.parseInt(in.readLine());

        // [정비소 개수 + 2]의 배열로 선언
        // - 출발, 도착을 표현하기 위함
        int[] distances = Arrays.stream(("0 "+in.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] times = Arrays.stream(("0 "+in.readLine()+" 0").split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] parents = new int[countOfRepairShop+2];

        // dp 초기화
        // - dp[i] : i번째 지점에 도착할 때 최소 정비 시간
        long[] dp = new long[countOfRepairShop+2];
        Arrays.fill(dp, Long.MAX_VALUE);
        // - 출발 지점은 0으로 설정
        dp[0] = 0;

        // 1번째 지점부터 차례로 계산
        for(int i = 1; i < countOfRepairShop+2; i++){
            // 이동 거리
            long sum = distances[i];
            // - (i-1)번째 지점부터 차례로 이전 지점 탐색하여 최소 정비 시간 계산
            for(int j = i-1; j >= 0; j--){
                // 최대 이동 거리를 넘어갈 경우 패스
                if(sum > maxDistance) break;
                // j번째 지점에서 정비하는 것이 최소 정비 시간인 경우 갱신
                if(dp[i] > dp[j] + times[j]){
                    dp[i] = dp[j] + times[j];
                    parents[i] = j;
                }
                // 이동 거리 갱신
                sum += distances[j];
            }
            System.out.println(Arrays.toString(dp));
        }
        // System.out.println(Arrays.toString(parents)+"====");

        // 마지막 정비 지점
        int point = parents[countOfRepairShop+1];
        // 정비 지점 탐색
        Deque<Integer> deque = new LinkedList<>();
        while(point != 0){
            deque.offerLast(point);
            point = parents[point];
        }

        // 최소 정비 시간
        sb.append(dp[countOfRepairShop+1]).append("\n")
                // 방문한 정비소 개수
                .append(deque.size()).append("\n");
        // 방문한 정비소 번호
        while(!deque.isEmpty()) sb.append(deque.pollLast()).append(" ");

        // 정답 반환
        System.out.println(sb);
    }
}