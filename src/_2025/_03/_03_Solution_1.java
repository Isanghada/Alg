package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11662
// - 수학 : 절대/상대 오차는 10^(-6)이므로, 속도를 10^(-6)를 기준으로 계산!
//          속도에 따라 이동시키며 가장 가까운 위치 탐색
public class _03_Solution_1 {
    // 좌표 클래스
    public static class Point{
        double x;
        double y;
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }
    // 최대 오차
    static final int INTERVAL = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        // 민호 시작 좌표
        Point A = new Point(
                Double.parseDouble(st.nextToken()),
                Double.parseDouble(st.nextToken())
        );
        // 민호 도착 좌표
        Point B = new Point(
                Double.parseDouble(st.nextToken()),
                Double.parseDouble(st.nextToken())
        );
        // 강호 시작 좌표
        Point C = new Point(
                Double.parseDouble(st.nextToken()),
                Double.parseDouble(st.nextToken())
        );
        // 강호 도착 좌표
        Point D = new Point(
                Double.parseDouble(st.nextToken()),
                Double.parseDouble(st.nextToken())
        );

        // 민호 속도
        Point aD = new Point(
                (B.x - A.x) / INTERVAL,
                (B.y - A.y) / INTERVAL
        );
        // 강호 속도
        Point cD = new Point(
                (D.x - C.x) / INTERVAL,
                (D.y - C.y) / INTERVAL
        );

        // 시작점 기준 거리 계산
        double answer = getDistance(A.x, A.y, C.x, C.y);
        // 이동시키며 모든 위치에서의 거리 계산
        for(int interval = 1; interval <= INTERVAL; interval++){
            answer = Math.min(answer, getDistance(A.x + aD.x*interval, A.y+ aD.y*interval
                                                 ,C.x + cD.x*interval, C.y+ cD.y*interval));
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}
