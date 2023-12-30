package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2170
// - 우선순위 큐를 사용해 해결! => 시간초과! 한 요소를 추가하거나 제거할 떄 작업 동작이 문제인듯
// - 리스트에 선 정보를 담고 정렬하여 차례로 계산!
public class _02_Solution_1 {
    // 선 정보 클래스
    static class Line implements Comparable<Line>{
        int point;  // 선 좌표
        boolean isStart;    // 시작, 끝 여부

        public Line(int point, boolean isStart){
            this.point = point;
            this.isStart = isStart;
        }

        // 선 좌표 기준 오름차순, (시작 -> 끝) 순서
        @Override
        public int compareTo(Line o) {
            int diff = this.point - o.point;
            if(diff == 0){
                if(this.isStart == o.isStart) return 0;
                else if(this.isStart) return -1;
                else return 1;
            }
            return diff;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 선을 긋는 횟수
        int N = Integer.parseInt(in.readLine());

        // 우선순위 큐에 선을 긋는 좌표 추가
        List<Line> lineList= new ArrayList<>();
        for(;N > 0; N--){
            // 선 정보 입력
            st = new StringTokenizer(in.readLine(), " ");
            // 시작, 끝으로 나누어 추가!
            for(boolean isStart : new boolean[] {true, false})
                lineList.add(new Line(Integer.parseInt(st.nextToken()), isStart));
        }

        Collections.sort(lineList);

        // 정답 초기화
        int answer = 0;
        int idx = 0;
        // 우선순위큐가 빌 때까지 반복
        while(idx < lineList.size()){
            // 시작 지점의 수
            int count = 1;
            // 시작 지점 반환
            Line start = lineList.get(idx++);
            // 끝 지점 초기화
            Line end = null;

            // 시작된 모든 선이 끝난 경우 종료!
            while(count > 0) {
                // 현재 선 정보 반환
                Line cur = lineList.get(idx++);
                // 시작 지점인 경우 count 증가
                if(cur.isStart)count++;
                // 끝 지점인 경우
                else {
                    // end 업데이트
                    end = cur;
                    // count 감소
                    count--;
                }
            }

            // (끝 - 시작) 길이 만큼 정답 증가
            answer += end.point - start.point;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
