package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2938
// -
public class _21_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Deque<Integer>[] deque = new Deque[3];
        for(int i = 0; i < 3; i++) deque[i] = new LinkedList<>();

        for(int v : arr) deque[v % 3].add(v);
        if(deque[0].size() > (N+1)/ 2 || deque[0].isEmpty() && !deque[1].isEmpty() && !deque[2].isEmpty()){
            sb.append(-1);
        }else{
            while(!deque[1].isEmpty()){
                if(deque[0].size() > 1) sb.append(deque[0].pollFirst()).append(" ");
                sb.append(deque[1].pollFirst()).append(" ");
            }

            if(!deque[0].isEmpty()) sb.append(deque[0].pollFirst()).append(" ");
            while(!deque[2].isEmpty()){
                sb.append(deque[2].pollFirst()).append(" ");
                if(!deque[0].isEmpty()) sb.append(deque[0].pollFirst()).append(" ");
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}