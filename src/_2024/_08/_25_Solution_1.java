package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20157
// - 해시 : 각 지점의 기본값을 계산하여 카운팅!
//          x, y를 최대 공약수로 나누면 기본형이 나오게 되고,
//          이 값을 비교하여 카운팅!
public class _25_Solution_1 {
    // 좌표 클래스 : 해시를 사용하기 위해 hashcode, equals 재정의!
    public static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point){
                Point p = (Point) obj;
                if(this.x == p.x && this.y == p.y) return true;
                else return false;
            }
            return super.equals(obj);
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 풍선의 수
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        // 풍선 정보!
        Map<Point, Integer> pointMap = new HashMap<>();
        while(--N >= 0){
            st = new StringTokenizer(in.readLine());
            // 좌표 입력
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 기본형으로 변형!
            int gcd = getGcd(Math.abs(x), Math.abs(y));
            x /= gcd;
            y /= gcd;
            Point p = new Point(x, y);

            // 좌표 카운팅
            if (!pointMap.containsKey(p)) pointMap.put(p, 0);
            pointMap.put(p, pointMap.get(p)+1);
        }

        // 정답 출력
        // - 최대값 반환!
        sb.append(Collections.max(pointMap.values()));
        System.out.println(sb);
    }

    private static int getGcd(int x, int y) {
        while(y != 0){
            int r = x % y;
            x = y;
            y = r;
        }
        return x;
    }
}