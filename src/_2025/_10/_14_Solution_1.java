package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/13884
// -
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int P = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(P-- > 0){
            st = new StringTokenizer(in.readLine());
            int K = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            List<Integer> list = new ArrayList<>();
            for(int count = N; count > 0; count -= 10){
                st = new StringTokenizer(in.readLine());
                while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
            }

            List<Integer> sorted = new ArrayList<>(list);
            Collections.sort(sorted);

//            System.out.println(list);
//            System.out.println(sorted);
//            System.out.println("----------");

            int count = 0;
            for(int i = 0; i < N; i++){
                if(Integer.compare(list.get(i), sorted.get(count)) == 0) count++;
            }
            int answer = N - count;

            sb.append(K).append(" ").append(answer).append("\n");
        }


        // 정답 출력
        System.out.println(sb.toString().trim());
    }
}

