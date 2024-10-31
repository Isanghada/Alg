package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24041
// - 이분 탐색 : 날짜를 기준으로 이분 탐색을 실행하고, 세균이 G 이하인지 확인!
public class _31_Solution_1 {
    // 음식 클래스
    public static class Food{
        int S;  // 세균
        int L;  // 유통기한
        int O;  // 중요도
        public Food(int S, int L, int O){
            this.S = S;
            this.L = L;
            this.O = O;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_31_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 음식의 수
        int G = Integer.parseInt(st.nextToken());   // 최대 세균의 수
        int K = Integer.parseInt(st.nextToken());   // 제외할 수 있는 재료의 수

        List<Food> type0 = new ArrayList<>();   // 중요한 재료
        List<Food> type1 = new ArrayList<>();   // 중요하지 않은 재료
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int S = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int O = Integer.parseInt(st.nextToken());

            Food food = new Food(S, L, O);
            if(O == 0) type0.add(food);
            else type1.add(food);
        }
        
        // 정답 초기화
        long answer = 0;
        // 범위 : 0 ~ 2_000_000_000
        long left = 0;
        long right = 2_000_000_000;
        while(left <= right){
            // 날짜 계산
            long day = (left + right) / 2;
//            System.out.println(day);
            // 세균의 합 계산
            long sum = sumOfS(type0, day, 0)+ sumOfS(type1, day, K);
            // 가능한 경우 정답 갱신 후 left 갱신
            if(sum <= G){
                answer = day;
                left = day+1;
            // 불가능한 경우 right 갱신
            }else right = day - 1;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    // 세균의 합 함수 : k개 제외!
    private static long sumOfS(List<Food> foodList, long day, int k) {
        // 재료별 세균 리스트
        List<Long> sList = new ArrayList<>();
        for(Food food : foodList) sList.add(food.S * Math.max(1L, day - food.L));

        // 제외할 재료가 있는 경우 정렬!
        if(k != 0) Collections.sort(sList);

        // k개를 제외한 나머지 값의 합 계산
        long sum = 0;
        for(int i = sList.size() - k - 1; i >= 0; i--) sum += sList.get(i);

        return sum;
    }
}