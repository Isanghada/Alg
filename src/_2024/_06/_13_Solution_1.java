package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11067
// - 정렬 : 산책로의 모든 경로가 주어지므로 정렬하게 되면 순서대로 표현할 수 있다.
public class _13_Solution_1 {
    // 카페 클래스
    public static class Cafe implements Comparable<Cafe>{
        int x;  // x 좌표
        int y;  // y 좌표
        public Cafe(int x, int y){
            this.x = x;
            this.y = y;
        }
        // x 기준 오름차순 정렬, y기준 오름차순 정렬
        @Override
        public int compareTo(Cafe o){
            int diff = this.x - o.x;
            return (diff == 0 ? this.y - o.y : diff);
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(this.x).append(" ").append(this.y);
            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        while(T-- > 0){
            // 카페의 수
            int N = Integer.parseInt(in.readLine());

            // 카페 리스트
            List<Cafe> cafeList = new ArrayList<>();
            // 초기값 설정 : 시작 지점이 (0, 0)이므로 y좌표가 동일하게 설정
            cafeList.add(new Cafe(-1, 0));
            // 카페 입력
            for(int n = 0; n < N; n++){
                st = new StringTokenizer(in.readLine());
                cafeList.add(new Cafe(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                ));
            }

            // 카페 정렬 : x 기준 오름차순, y 기준 오름차순
            Collections.sort(cafeList);

            // 경로 순서대로 표현하기 위해 부분 정렬
            int idx = 1;
            while(idx <= N){
                // x가 동일한 경우 경로 진행!
                if(cafeList.get(idx).x == cafeList.get(idx-1).x) idx++;
                // x가 다르고 y가 동일한 경우 경로 진행
                else if(cafeList.get(idx).y == cafeList.get(idx-1).y){
                    idx++;
                // 진행할 수 없는 경로인 경우
                }else{
                    // 시작 인덱스 설정
                    int cur = idx;
                    // x가 동일한 경우 모두 탐색
                    while(idx <= N && cafeList.get(cur).x == cafeList.get(idx).x) idx++;
                    // 동일한 x를 가지는 서브 리스트 설정
                    List<Cafe> subList = cafeList.subList(cur, idx);
                    // 서브 리스트를 역정렬하여 경로 설정!
                    // - 이동할 수 있는 경로가 주어지므로 x가 동일할 때,
                    //   y기준 오름차순 혹은 내림차순한 결과가 경로이다.
                    //   기본적으로 (x기준 오름차순 -> y기준 오름차순)으로 되어있으므로,
                    //   서브 리스트 부분만 역정렬하여 경로를 설정한다.
                    Collections.reverse(subList);
                }
            }
            // 카페 번호 입력
            st = new StringTokenizer(in.readLine());
            int M = Integer.parseInt(st.nextToken());
            // 카페 좌표 출력
            while(st.hasMoreTokens()){
                sb.append(cafeList.get(Integer.parseInt(st.nextToken()))).append("\n");
            }
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
