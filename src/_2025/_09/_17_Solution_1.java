package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10589
// -
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int k = 0;
        for(int i = 2; i <= M; i += 2){
            k++;
            sb.append(1).append(" ");
            sb.append(i).append(" ");
            sb.append(N).append(" ");
            sb.append(i).append("\n");
        }

        for(int i = 2; i <= N; i += 2){
            k++;
            sb.append(i).append(" ");
            sb.append(1).append(" ");
            sb.append(i).append(" ");
            sb.append(M).append("\n");
        }


        // 정답 출력
        System.out.println(k);
        System.out.println(sb.toString());
    }
}
