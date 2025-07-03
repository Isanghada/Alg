package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16169
// - DP : 각 컴퓨터별 작업 시작 시간을 계산하여 탐색
public class _04_Solution_1 {
    // 컴퓨터 클래스
    static class Computer{
        int num;    // 번호
        int time;   // 동작 속도
        public Computer(int num, int time){
            this.num = num;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 컴퓨터 수
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        Map<Integer, List<Computer>> computerMap = new HashMap<>();
        int minRank = 100;  // 최소 등급
        int maxRank = 1;    // 최대 등급
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int rank = Integer.parseInt(st.nextToken());
            Computer c = new Computer(
                    i+1,
                    Integer.parseInt(st.nextToken())
            );

            minRank = Math.min(minRank, rank);
            maxRank = Math.max(maxRank, rank);

            if(!computerMap.containsKey(rank)) computerMap.put(rank, new ArrayList<>());
            computerMap.get(rank).add(c);
        }

        // 작업 시작 시간 초기화
        int[] time = new int[N+1];
        // 최소 등급부터 차례로 탐색
        for(int rank = minRank; rank < maxRank; rank++){
            int nextRank = rank+1;
            for(Computer c : computerMap.get(rank)){
                for(Computer next : computerMap.get(nextRank)){
                    // next에 작업 시작 시간 갱신! : 모든 정보를 받을 수 있는 시간
                    time[next.num] = Math.max(time[next.num],
                                              time[c.num] + pow(c.num - next.num) + c.time);
                }
            }
        }

        int answer = 0;
        for(Computer c : computerMap.get(maxRank)) answer = Math.max(answer, time[c.num]+c.time);

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static int pow(int target) {
        return target * target;
    }
}
