package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/
// - 정렬 : 시작, 끝 지점을 각각 리스트에 추가하고, 정렬!
//          시작 지점일 경우 겹치는 경우 증가, 끝 지점일 경우 겹치는 경우 감소!
public class _06_Solution_1 {
    // 선분 클래스
    public static class Line implements Comparable<Line>{
        int point;  // 좌표
        int type;   // 타입(0-시작, 1-끝)
        public Line(int point, int type){
            this.point = point;
            this.type = type;
        }
        @Override
        public int compareTo(Line l) {
            int diff = this.point - l.point;
            if(diff == 0) return l.type - this.type;
            return diff;
        }
    }
    public static final int START = 0, END = 1;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 선분의 수
        int N = Integer.parseInt(in.readLine());

        // 선분 정보 리스트
        List<Line> list = new ArrayList<>();
        StringTokenizer st = null;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            // 시작 지점, 끝 지점 따로 추가!
            list.add(new Line(Integer.parseInt(st.nextToken()), 0));
            list.add(new Line(Integer.parseInt(st.nextToken()), 1));
        }

        // 선분 정보 정렬
        Collections.sort(list);

        // 정답 초기화
        int answer = 0;
        // 현재 겹친 경우의 수
        int count = 0;
        for(Line line : list){
            // 시작 지점일 경우 count 증가
            if(line.type == START){
                count++;
                answer = Math.max(answer, count);
            // 끝 지점일 경우 count 감소
            }else count--;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
