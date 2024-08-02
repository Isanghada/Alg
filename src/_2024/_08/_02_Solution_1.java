package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14715
// - 참고 : https://steady-coding.tistory.com/244
// - 정수 : 소인수 분해를 통해 리프 노드의 개수 확인!
//          흉터가 최소가 되기 위해선 완전 이진 트리가 되어야 하므로,
//          리프 노드의 개수로 이를 역계산!
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 슬라임 에너지
        int K = Integer.parseInt(in.readLine());
        // 소인수 분해!
        int count = 1;
        for(int num = 2; num * num <= K; num++){
            while(K % num == 0 && num * num <= K){
                K /= num;
                count++;
            }
        }

        // 정답 반환
        sb.append((int)(Math.ceil(Math.log10(count) / Math.log10(2))));
        System.out.println(sb);
    }
}
