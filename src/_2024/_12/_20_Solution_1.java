package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19942
// - 백트래킹 : 모든 조합을 찾아보며 최소값 탐색!
public class _20_Solution_1 {
    // 식품 구성 클래스
    public static class Ingredient{
        int protein;
        int fat;
        int carbohydrates;
        int vitamins;
        public Ingredient(){
            this(0, 0, 0, 0);
        }
        public Ingredient(int protein, int fat, int carbohydrates, int vitamins){
            this.protein = protein;
            this.fat = fat;
            this.carbohydrates = carbohydrates;
            this.vitamins = vitamins;
        }
    }
    // 식품 클래스
    public static class Food extends Ingredient{
        int price;
        public Food(){
            super();
            this.price = 0;
        }
        public Food(int protein, int fat, int carbohydrates, int vitamins, int price){
            super(protein, fat, carbohydrates, vitamins);
            this.price = price;
        }
        public Food(Ingredient ingredient, int price){
            super(ingredient.protein, ingredient.fat, ingredient.carbohydrates, ingredient.vitamins);
            this.price = price;
        }
        public void plusIngredient(Food food){
            this.protein += food.protein;
            this.fat += food.fat;
            this.carbohydrates += food.carbohydrates;
            this.vitamins += food.vitamins;
            this.price += food.price;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ protein=").append(this.protein)
                    .append(", fat=").append(this.fat)
                    .append(", carbohydrates=").append(this.carbohydrates)
                    .append(", vitamins=").append(this.vitamins)
                    .append(", price=").append(this.price)
                    .append(" ]");
            return sb.toString();
        }
    }
    public static int[] BIT;
    public static int ANSWER, BITMASK;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        BIT = new int[15];
        BIT[0] = 1;
        for(int i = 1; i < 15; i++) BIT[i] = BIT[i-1] << 1;

        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        Ingredient target = new Ingredient(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        );

        Food[] foods =new Food[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            foods[i] = new Food(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        ANSWER = 100_000;
        BITMASK = 0;
        backtracking(0, foods, target, 0, N);
        if(ANSWER == 100_000) ANSWER = -1;

        sb.append(ANSWER);
        if(ANSWER != -1){
            sb.append("\n");
            for(int i = 0; i < N; i++){
                if((BITMASK & BIT[i]) > 0) sb.append(i+1).append(" ");
            }
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static void backtracking(int step, Food[] foods, Ingredient target, int bitmask, int n) {
        if(step == n){
            Food sum = new Food();
            for(int i =0 ; i < n; i++){
                if((bitmask & BIT[i]) > 0){
                    sum.plusIngredient(foods[i]);
                }
            }

            if(check(sum, target)){
                if(ANSWER > sum.price){
                    ANSWER = sum.price;
                    BITMASK = bitmask;
                }else if(ANSWER == sum.price){
                    String result = bitToString(BITMASK);
                    String cur = bitToString(bitmask);

                    if(result.compareTo(cur) > 0) BITMASK = bitmask;
                }
            }

            return;
        }

        backtracking(step+1, foods, target, bitmask | BIT[step], n);
        backtracking(step+1, foods, target, bitmask, n);
    }

    private static String bitToString(int bitmask) {
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        while(idx < 15 && BIT[idx] <= bitmask){
            if((bitmask & BIT[idx]) > 0) sb.append(idx+1).append(" ");
            idx++;
        }

        return sb.toString().trim();
    }

    private static boolean check(Food sum, Ingredient target) {
        if(sum.protein >= target.protein &&
                sum.fat >= target.fat &&
                sum.carbohydrates >= target.carbohydrates &&
                sum.vitamins >= target.vitamins
        ) return true;
        return false;
    }
}
