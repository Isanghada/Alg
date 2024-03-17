package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19598
// - 그리디 : 시작 시간이 빠른 회의부터 차례로 회의실 배정
public class _17_Solution_1 {
    // 회의 클래스
    public static class Meeting implements Comparable<Meeting>{
        int start;  // 시작 시간
        int end;    // 종료 시간
        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
        // 1. 시작 시간 기준 오름차순 정렬
        // 2. 종료 시간 기준 오름차순 정렬
        @Override
        public int compareTo(Meeting o){
            int diff = this.start - o.start;
            return (diff == 0 ? this.end - o.end : diff);
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 회의 개수
        int N = Integer.parseInt(in.readLine());
        // 회의 배열 초기화
        Meeting[] meetings = new Meeting[N];

        // 회의 입력
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            meetings[i] = new Meeting(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 회의 정렬
        Arrays.sort(meetings);

        // 우선순위 큐 초기화 : 회의가 배정된 회의실의 회의 종료 시간 기준
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(0);

        // 모든 회의 추가!
        for(Meeting meeting : meetings){
            // 현재 가장 빨리 회의가 끝나는 회의실이 현재 회의의 시작 시간보다 작은 경우
            // - 루트 노드 제거
            if(pq.peek() <= meeting.start) pq.poll();
            // 새로운 회의 배정
            pq.offer(meeting.end);
        }

        // 정답 출력
        // - 우선순위 큐의 크기가 배정된 회의실의 수
        sb.append(pq.size());
        System.out.println(sb);
    }
}
