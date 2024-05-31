package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2187
// - 브루트포스 : 각 좌표를 기준으로 모든 지점 확인!
public class _31_Solution_1 {
    // 좌표 클래스
    public static class Point{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int s;      // 가중치
        public Point(int row, int col, int s){
            this.row = row;
            this.col = col;
            this.s = s;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();

            sb.append("[ row=").append(row)
                    .append(", col=").append(col)
                    .append(", s=").append(s).append(" ]");

            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 좌표의 수
        int A = Integer.parseInt(st.nextToken());   // 세로 범위
        int B = Integer.parseInt(st.nextToken());   // 가로 범위

        // 좌표 배열
        Point[] points = new Point[N];
        int maxRow = 0;
        int maxCol = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            points[i] = new Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            maxRow = Math.max(maxRow, points[i].row+1);
            maxCol = Math.max(maxCol, points[i].col+1);
        }

//        for(Point p : points) System.out.println(p);
//        System.out.println(maxRow+", "+maxCol);

        // 정답 초기화
        int answer = 0;
        // 모든 좌표 탐색
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int a = Math.abs(points[i].row - points[j].row);
                int b = Math.abs(points[i].col - points[j].col);
                if(a < A && b < B) answer = Math.max(answer, Math.abs(points[i].s - points[j].s));
            }
        }

//        for(Point p : points) System.out.println(p);

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}