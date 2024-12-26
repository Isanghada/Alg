package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2539
// - 이분 탐색 : 색종이 크기를 이분 탐색으로 확인!
public class _26_Solution_1 {
    // 좌표 클래스
    private static class Point implements Comparable<Point>{
        int r;  // 행
        int c;  // 열
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Point p){
            return this.c - p.c;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int R = Integer.parseInt(st.nextToken());   // 행 크기
        int C = Integer.parseInt(st.nextToken());   // 열 크기

        int paperCount = Integer.parseInt(in.readLine());   // 사용할 수 있는 색종이 수
        int faultCount = Integer.parseInt(in.readLine());   // 잘못 칠해진 칸의 수

        // 잘못 칠해진 칸 리스트
        List<Point> faultList = new ArrayList<>();
        for(int i = 0; i < faultCount; i++){
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            faultList.add(new Point(r, c));
        }
        // 열 기준 오름차순 정렬
        Collections.sort(faultList);

        // 정답 반환
        // - 이분 탐색을 통해 최소 색종이 크기 계산
        sb.append(binarySearch(Math.max(R, C), paperCount, faultList));
        System.out.println(sb);
    }

    private static int binarySearch(int max, int paperCount, List<Point> faultList) {
        int left = 1;
        int right = max;
        while(left <= right) {
            int mid = (left + right) / 2;

            // 가능한지 체크!
            if(check(mid, paperCount, faultList))right = mid - 1;
            else left = mid + 1;
        }

        return left;
    }

    private static boolean check(int size, int paperCount, List<Point> faultList) {
        int count = 0;
        int prev = 0;
        for(Point p : faultList){
            if(p.r > size) return false;
            else if(prev == 0 || prev+size <= p.c){
                count++;
                prev = p.c;
                if(count > paperCount) return false;
            }
        }
        return true;
    }
}
