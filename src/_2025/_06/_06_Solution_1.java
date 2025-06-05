package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/32405
// - 누적합 : 순서대로 데미지의 누적합을 구하고, 마지막에 남는 플레이어 탐색
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] D = Arrays.stream(("0 "+in.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] H = Arrays.stream(("0 "+in.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        Deque<Integer> deque = new LinkedList<>();
        for(int n = 1; n <= N; n++) deque.offerLast(n);

        int damageSum = 0;
        int rotate = 0;
        while(deque.size() > 1){
            int len = deque.size();
            while(len-- > 0){
                int idx = deque.pollFirst();
                if(H[idx] != 0 && H[idx] > damageSum - D[idx] * rotate){
                    damageSum += D[idx];
                    deque.offerLast(idx);
                }else H[idx] = 0;
            }
            rotate++;
        }

        // 정답 출력
        sb.append(deque.pollFirst());
        System.out.println(sb);
    }
}
