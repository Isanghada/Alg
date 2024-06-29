package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30405
// -
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = 200000;
        List<Integer> list = new ArrayList<>();
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());
            for(int i = 1; i <= k; i++){
                if(i == 1 || i == k) list.add(Integer.parseInt(st.nextToken()));
                else st.nextToken();
            }
        }

        Collections.sort(list);

        // 정답 반환
        sb.append(list.get(N-1));
        System.out.println(sb);
    }
}