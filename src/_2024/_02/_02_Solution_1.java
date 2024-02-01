package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7983
// - 그리디 : 마감 기한이 가장 많이 남은 과제부터 마감 기한에 과제를 끝낼 수 있도록 계산.
public class _02_Solution_1 {
    // 과제 클래스
    public static class Task implements Comparable<Task>{
        int d;  // 처리 시간
        int t;  // 마감 기한
        public Task(int d, int t){
            this.d = d;
            this.t = t;
        }
        // 마감 기한 기준 내림차순 정렬
        @Override
        public int compareTo(Task o){
            return o.t - this.t;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[")
                    .append("d : ").append(this.d)
                    .append(", t : ").append(this.t)
                    .append("]");

            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 과제의 수
        int N = Integer.parseInt(in.readLine());
        // 과제 배열 초기화
        Task[] taskArr = new Task[N];
        // 과제 입력
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            taskArr[i] = new Task(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 과제 정렬 : 마감 기한 기준 내림차순 정렬
        Arrays.sort(taskArr);

        // 정답 초기화 : 마감 기한 최대값
        int answer = taskArr[0].t;
        // 모든 과제 차례로 처리
        for(Task task : taskArr){
            // 가능한 마감 기한 설정 : 정답과 과제의 마감 기한 중 최소값
            answer = Math.min(answer, task.t);
            // 계산한 마감 기한에 과제를 끝낼 수 있도록 계산
            answer -= task.d;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
