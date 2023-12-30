package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20207
// - 누적합 활용 : 범위를 구하여 계산!
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 일정의 수
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        // 일자별 일정 개수 : 누적합 활용
        int[] scheduleCount = new int[367];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            // 시작 날짜
            int start = Integer.parseInt(st.nextToken());
            // 끝 날짜
            int end = Integer.parseInt(st.nextToken()) + 1;
            // 시작 날짜는 증가
            scheduleCount[start]++;
            // 끝 날짜는 감소
            scheduleCount[end]--;
        }

        // 누적합을 통해 일자별 일정 개수 계산
        for(int i = 1; i < scheduleCount.length; i++) scheduleCount[i] += scheduleCount[i-1];

        // 정답 초기화
        int answer = 0;
        // 범위 초기화
        int w = 0, h = 0;
        // 1일부터 366일까지 진행
        // - 366일은 무조건 0!
        for(int i = 1; i < scheduleCount.length; i++){
            // 일정이 없는 경우 : 이전까지의 범위만큼 증가
            if(scheduleCount[i] == 0){
                // 범위만큼 증가
                answer += w * h;
                // 범위 초기화
                w = 0;
                h = 0;
            // 일정이 있는 경우 : 범위 계산
            }else{
                // 가로 길이는 1만큼 증가
                w += 1;
                // 세로 길이는 h와 일정 개수 중 최대값 선택
                h = Math.max(h, scheduleCount[i]);
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
