package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12892
// -
public class _16_Solution_1 {
    static class Gift implements Comparable<Gift>{
        int price;
        int happy;
        public Gift(int price, int happy){
            this.price = price;
            this.happy = happy;
        }
        @Override
        public int compareTo(Gift g){
            return this.price - g.price;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Gift[] gifts = new Gift[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            gifts[i] = new Gift(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        Arrays.sort(gifts);

        long answer = 0;
        long sum = 0;
        int left = 0;
        int right = 0;
        while(right < N){
            if((gifts[right].price - gifts[left].price) < D){
                sum += gifts[right++].happy;
            }else{
                sum -= gifts[left++].happy;
            }
            answer = Math.max(answer, sum);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
