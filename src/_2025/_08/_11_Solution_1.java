package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17026
// -
public class _11_Solution_1 {
    static class Point implements Comparable<Point> {
        int left;
        int right;
        public Point(int left, int right){
            this.left = left;
            this.right = right;
        }
        @Override
        public int compareTo(Point o) {
            int diff = Integer.compare(this.left, o.left);
            return diff == 0 ? Integer.compare(o.right, this.right) : diff;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        Point[] points = new Point[N];
        for(int n = 0; n < N; n++){
            int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            points[n] = new Point(input[0]-input[1], input[0]+input[1]);
        }

        Arrays.sort(points);

        int max = Integer.MIN_VALUE;
        int answer = 0;
        for(Point point : points){
            if(point.right > max){
                max = point.right;
                answer++;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
