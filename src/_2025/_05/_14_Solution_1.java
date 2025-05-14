package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12099
// - 이분 탐색 : 매운맛, 단맛을 기준으로 정렬 후 이분 탐색을 통해
//                  각자 가능한 음식 체크하여 공통된 음식의 개수 반환
public class _14_Solution_1 {
    // 음식 클래스
    static class Food{
        int spicy;  // 매운맛
        int sweet;  // 단맛
        public Food(int spicy, int sweet){
            this.spicy = spicy;
            this.sweet = sweet;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[ spicy=").append(this.spicy)
                    .append(", sweet=").append(this.sweet)
                    .append(" ]");

            return sb.toString();
        }
        // Collection을 활용하기 위해 equals, hashCode 재정의
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Food){
                Food food = (Food) obj;

                return this.spicy == food.spicy
                        && this.sweet == food.sweet;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(this.spicy, this.sweet);
        }
    }
    static final int SPICY = 0, SWEET = 1;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 음식의 수
        int Q = Integer.parseInt(st.nextToken());   // 같이 먹는 기간

        // 음식 정보 입력
        Food[] foods = new Food[N];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(in.readLine());
            foods[n] = new Food(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 매운맛 기준 오름차순 정렬!
        Arrays.sort(foods, Comparator.comparingInt(f -> f.spicy));

//        System.out.println(Arrays.toString(spicyArr));
//        System.out.println(Arrays.toString(sweetArr));

        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            // 매운맛 범위
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            // 단맛 범위
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 정답 초기화
            int answer = 0;

            // 매운맛 시작 인덱스! : 이분 탐색으로 계산
            int idx = underBinarySearch(foods, u, N, SPICY);
            while(idx < N && foods[idx].spicy <= v){
                // 단맛 범위인 경우 answer 증가
                if(foods[idx].sweet >= x && foods[idx].sweet <= y) answer++;
                idx++;
            }

            sb.append(answer).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }

    // 하한
    private static int underBinarySearch(Food[] foodArr, int target, int n, int type) {
        int left = 0;
        int right = n-1;

        while(left <= right){
            int mid = (left + right) / 2;
            if((type == SPICY && foodArr[mid].spicy >= target)
                    || (type == SWEET && foodArr[mid].sweet >= target)) right = mid - 1;
            else left = mid + 1;
        }

        return left;
    }
}

