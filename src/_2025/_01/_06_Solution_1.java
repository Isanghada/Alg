package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2285
// - 참고 : https://naknak.tistory.com/31
// - 그리디 : 위치, 인구수를 기준으로 정렬한 후 전체 인구의 절반이 포함되는 위치 탐색!
public class _06_Solution_1 {
    // 도시 클래스
    static class City implements Comparable<City>{
        int x;  // 위치(좌표)
        int a;  // 인구수
        public City(int x, int a){
            this.x = x;
            this.a = a;
        }
        // 1. 위치 기준 오름차순
        // 2. 인구수 기준 오름차순
        @Override
        public int compareTo(City o) {
            if(this.x == o.x) return this.a - o.a;
            return this.x - o.x;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        // 도시의 수
        int N = Integer.parseInt(in.readLine());
        
        // 전체 인원수
        long total = 0;
        
        // 도시 정보 입력
        City[] cities = new City[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            cities[i] = new City(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            total += cities[i].a;
        }

        // 도시 정보 정렬
        Arrays.sort(cities);

        // 인원수 누적합
        long sum = 0;
        for(int i = 0; i < N; i++){
            sum += cities[i].a;
            // 전체 인구수의 절반 이상인 경우 좌표 출력 후 종료
            if(sum >= (total/2)){
                sb.append(cities[i].x);
                break;
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}
