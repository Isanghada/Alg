package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7868
// - 브루트포스 : 모든 경우의 수를 구하여 i인덱스의 값 반환
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long p1 = Long.parseLong(st.nextToken());       // 소인수1
        long p2 = Long.parseLong(st.nextToken());       // 소인수2
        long p3 = Long.parseLong(st.nextToken());       // 소인수3
        long index = Long.parseLong(st.nextToken());    // index 값

        // 모든값 계산 : double로 게산하여 최대값을 넘는 경우는 MAX로 고정
        List<Long> numberList = new ArrayList<>();
        for(int i = 0; i < 60; i++){
            double a = Math.pow(p1, i);
            for(int j = 0; j < 60; j++){
                if(i+j > 60) break;
                double b = Math.pow(p2, j);
                for(int k = 0; k < 60; k++){
                    if(i+j+k > 60) break;
                    numberList.add((long) (a * b *Math.pow(p3, k)));
                }
            }
        }
        // 값 정렬
        Collections.sort(numberList);
//        System.out.println(numberList);
        sb.append(numberList.get((int)index));
        System.out.println(sb);
    }
}
