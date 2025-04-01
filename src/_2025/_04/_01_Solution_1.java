package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/9083
// - 그리디 : 모든 버스 시간을 체크하여 가능한 경우 중 최대값을 선택!
//            모두 불가능한 경우는 새로운 버스 배차!
public class _01_Solution_1 {
    // 버스 클래스
    static class Bus implements Comparable<Bus>{
        int time;           // 출발 시간
        boolean isSchool;   // 출발 위치(true-학교, false-터미널)
        public Bus(int time, boolean isSchool){
            this.time = time;
            this.isSchool = isSchool;
        }
        // 출발 시간 기준 오름차순 정렬
        @Override
        public int compareTo(Bus b){
            return this.time - b.time;
        }
    }
    static final int MAX = 30 * 60, START = 6 * 60;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 이동 시간
            int D = Integer.parseInt(in.readLine());

            // 버스 우선순위 큐
            PriorityQueue<Bus> busPq = new PriorityQueue<>();

            // 학교에서 출발하는 버스 스케줄의 수
            int startSchool = Integer.parseInt(in.readLine());
            for(int i = 0; i < startSchool; i++){
                String[] times = in.readLine().split(":");
                busPq.offer(new Bus(timeToInt(times), true));
            }

            // 터미널에서 출발하는 버스 스케줄의 수
            int startTerminal = Integer.parseInt(in.readLine());
            for(int i = 0; i < startTerminal; i++){
                String[] times = in.readLine().split(":");
                busPq.offer(new Bus(timeToInt(times), false));
            }

            // 시간별 버스 개수
            int[] countOfSchool = new int[MAX];     // 학교 출발
            int[] countOfTerminal = new int[MAX];   // 터미널 출발
            // 모든 스케줄 체크
            while(!busPq.isEmpty()){
                Bus cur = busPq.poll();

                // 학교에서 출발
                if(cur.isSchool) busCheck(cur.time, D, countOfSchool, countOfTerminal);
                // 터미널에서 출발
                else busCheck(cur.time, D, countOfTerminal, countOfSchool);
            }
            
            int answer = 0;
            // 시간별 버스 개수의 합 계산
            for(int time = START; time < MAX; time++) answer += countOfSchool[time] + countOfTerminal[time];
            sb.append(answer).append("\n");
        }

        // 정답 입력
        System.out.println(sb);
    }

    private static void busCheck(int time, int d, int[] from, int[] to) {
        int fromTime = time - 2 * d;
        int toTime = time - d;

        // from에서 출발한 버스 중 최대값
        for(; fromTime >= START; fromTime--) if(from[fromTime] > 0) break;
        // to에서 출발한 버스 중 최대값
        for(; toTime >= START; toTime--) if(to[toTime] > 0) break;

        // 현재 스케줄에 버스 추가
        from[time]++;

        // from, to 스케줄 모두 가능한 경우 : from, to 중 큰 값 선택
        if(fromTime >= START && toTime >= START){
            if(fromTime > toTime) from[fromTime]--;
            else to[toTime]--;
        // from만 가능한 경우
        }else if(fromTime >= START) from[fromTime]--;
        // to만 가능한 경우
        else if(toTime >= START) to[toTime]--;
    }

    private static Integer timeToInt(String[] times) {
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }
}
