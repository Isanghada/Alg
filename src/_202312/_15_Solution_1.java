package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11000
// - 우선순위큐 : 종료 시간 기준으로 강의실 개수 체크!
public class _15_Solution_1 {
    // 강의 클래스
    public static class Subject implements Comparable<Subject>{
        int start;  // 시작 시간
        int end;    // 종료 시간
        public Subject(int start, int end){
            this.start = start;
            this.end = end;
        }
        // 시작 시간 기준 오름차순 정렬, 종료 시간 기준 오름차순 정렬
        @Override
        public int compareTo(Subject o) {
            int diff = this.start - o.start;
            return diff == 0 ? this.end - o.end : diff;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 강의 수
        int N = Integer.parseInt(in.readLine());
        
        StringTokenizer st = null;
        // 강의 입력
        Subject[] subjects = new Subject[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            subjects[i] = new Subject(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        // 강의 정렬
        Arrays.sort(subjects);

        // 우선순위큐 초기화
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // - 초기값 설정
        pq.offer(0);

        // 모든 강의 체크
        for(Subject cur : subjects){
            // 시작 시간이 이전까지 최소값보다 크거나 같은 경우!
            // - 루트 제거 O : 이전 사용한 강의실 사용
            // - 루트 제거 X : 새로운 강의실 사용
            if(pq.peek() <= cur.start) pq.poll();
            // 현재 강의 종료 시간 추가
            pq.offer(cur.end);
        }

        // 정답 출력
        // - pq의 크기가 강의실 개수
        sb.append(pq.size());
        System.out.println(sb);
    }
}
