package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22867
// - 누적합 : 출발, 도착 시간을 토대로 만든 누적합으로 계산
public class _02_Solution_1 {
    // 버스 클래스
    public static class Bus implements Comparable<Bus> {
        int time;   // 출발 혹은 도착 시간
        int count;  // 출발시 +1, 도착시 -1
        public Bus(int time, int count){
            this.time = time;
            this.count = count;
        }
        @Override
        public int compareTo(Bus o){
            return this.time - o.time;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 버스 개수
        int N = Integer.parseInt(in.readLine());

        // 버스 리스트
        List<Bus> busList = new ArrayList<>();
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int start = timeToInt(st.nextToken());
            int end = timeToInt(st.nextToken());
            busList.add(new Bus(start, 1));
            busList.add(new Bus(end, -1));
        }
        // 버스 정렬
        Collections.sort(busList);

        // 현재 누적합
        int cur = 0;
        // 정답
        int answer = 0;
        // 모든 버스에 대해 계산
        for(Bus b : busList){
            cur += b.count; // 현재 누적합 계산
            answer = Math.max(answer, cur); // 정답은 최대값으로 갱신
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
    private static int[] T = new int[]{3600000, 60000, 1000, 1};
    private static int timeToInt(String time) {
        StringTokenizer st = new StringTokenizer(time, ":.");
        int t = 0;
        for(int i = 0; i < 4; i++) t += Integer.parseInt(st.nextToken()) * T[i];
        return t;
    }
}
