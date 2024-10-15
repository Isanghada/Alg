package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2056
// - 위상 정렬 : 각 작업에 대한 정보를 입력하고, 위상 정렬을 활용해 차례로 작업 진행!
public class _15_Solution_1 {
    // 작업 클래스
    public static class Work{
        int time;       // 작업 시간
        int startTime;  // 작업 시작 시간
        int priorCount; // 선행 작업의 수
        List<Integer> nextWork; // 다음 작업 정보
        public Work(int time, int priorCount){
            this.time = time;
            this.startTime = 0;
            this.priorCount = priorCount;
            this.nextWork = new ArrayList<>();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 작업 개수
        int N = Integer.parseInt(in.readLine());

        // 작업 정보 입력
        StringTokenizer st = null;
        Work[] works = new Work[N+1];
        for(int w = 1; w <= N; w++) works[w] = new Work(0, 0);
        for(int w = 1; w <= N; w++){
            st = new StringTokenizer(in.readLine());
            works[w].time = Integer.parseInt(st.nextToken());
            works[w].priorCount = Integer.parseInt(st.nextToken());

            // 선행 작업의 nextWork에 현재 작업 추가!
            while(st.hasMoreTokens()) {
                int next = Integer.parseInt(st.nextToken());
                works[next].nextWork.add(w);
            }
        }

        // 정답 초기화
        int answer = 0;
        // 작업 완료 수
        int count = 0;
        // 작업 완료 여부
        boolean[] used = new boolean[N+1];
        while(count < N){
            // 가능한 작업 리스트
            List<Integer> possibleWork = new ArrayList<>();
            for(int w = 1; w <= N; w++){
                // 작업 완료하지 않았고, 남은 선행 작업이 없는 경우!
                if(!used[w] && works[w].priorCount == 0) {
                    // 리스트에 추가
                    possibleWork.add(w);
                    // 작업 완료 체크
                    used[w] = true;
                    // 정답 갱신!
                    answer = Math.max(answer, works[w].startTime + works[w].time);
                }
            }

            // 작업 완료 수 갱신
            count += possibleWork.size();
            // 선행 작업 처리!
            for(int work : possibleWork){
                for(int next : works[work].nextWork){
                    works[next].priorCount--;
                    works[next].startTime = Math.max(works[next].startTime,
                                                     works[work].startTime + works[work].time);
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
