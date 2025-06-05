package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17430
// -
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            int N = Integer.parseInt(in.readLine());

            Set<Integer> xSet = new HashSet<>();
            Set<Integer> ySet = new HashSet<>();
            for(int n = 0; n < N; n++){
                st = new StringTokenizer(in.readLine());
                xSet.add(Integer.parseInt(st.nextToken()));
                ySet.add(Integer.parseInt(st.nextToken()));
            }

            if(N == (long)xSet.size() * ySet.size()) sb.append("BALANCED\n");
            else sb.append("NOT BALANCED\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
