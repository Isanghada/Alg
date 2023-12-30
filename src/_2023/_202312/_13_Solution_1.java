package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1911
// - 그리디 : 물 웅덩이 시작 위치 기준 오름차순 정렬하여 계산!
public class _13_Solution_1 {
    // 물 웅덩이 좌표를 담을 클래스 : 오름차순 정렬 적용
    public static class Point implements Comparable<Point>{
        int start;  // 시작 위치
        int end;    // 끝 위치(미포함)

        public Point(int start, int end){
            this.start = start;
            this.end = end;
        }

        // 시작 위치 기준 오름차순 정렬
        @Override
        public int compareTo(Point o) {
            return this.start - o.start;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 물 웅덩이의 수
        int L = Integer.parseInt(st.nextToken());   // 널판지의 길이
        
        // 물 웅덩이 입력
        Point[] arr = new Point[N];
        for(int i = 0; i < N ;i++){
            st = new StringTokenizer(in.readLine());
            arr[i] = new Point(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        // 물 웅덩이 정렬
        Arrays.sort(arr);

        int answer = 0; // 정답 초기화
        int end = 0;    // 널판지가 끝난 위치
        for(Point cur : arr){
            // 이전 널판지 끝보다 시작 위치가 높다면 현재 시작 위치로 변경
            if(cur.start > end) end = cur.start;

            // 현재 끝 위치가 end 이상이라면 범위만큼 널판지 추가!
            if(cur.end >= end){
                int range = cur.end - end;  // 범위 계산
                int count = ceil(range, L); // 널판지의 개수
                end += count * L;   // 끝 위치 갱신
                answer += count;    // 널판지 추가
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    // 올림 계산 함수 : range / l의 올림 결과 반환
    private static int ceil(int range, int l) {
        // (몫) + (나머지가 0이 아닌 경우 1 증가)
        return (range / l) + (range % l != 0 ? 1 : 0);

    }
}
