package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5588
// -
public class _04_Solution_1 {
    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point){
                Point o = (Point) obj;

                if(this.x == o.x && this.y == o.y) return true;
            }
            return super.equals(obj);
        }
        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int M = Integer.parseInt(in.readLine());
        Set<Point> star = new HashSet<>();
        for(int m = 0; m < M; m++) star.add(getStar(in.readLine()));

        int N = Integer.parseInt(in.readLine());
        List<Point> pointList = new ArrayList<>();
        for(int n = 0; n < N; n++) pointList.add(getStar(in.readLine()));

        boolean flag = false;
        for(Point p : pointList){
            for(Point target : star){
                int[] delta = new int[]{target.x - p.x, target.y - p.y};

                Set<Point> pointSet = new HashSet<>();
                for(Point cur : pointList){
                    Point move = new Point(cur.x + delta[0], cur.y + delta[1]);
                    if(star.contains(move)) pointSet.add(move);
                }

                if(pointSet.size() == M){
                    flag = true;
                    sb.append(-delta[0]).append(" ").append(-delta[1]);
                }
            }
            if(flag) break;
        }


        // 정답 반환
        System.out.println(sb);
    }

    private static Point getStar(String input) {
        StringTokenizer st = new StringTokenizer(input);
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        return new Point(x, y);
    }

}
