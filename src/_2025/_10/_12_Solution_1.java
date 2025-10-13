package _2025._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26075
// -
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_10/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[] S = in.readLine().toCharArray();
        char[] T = in.readLine().toCharArray();

        List<Integer> sList = new ArrayList<>();
        List<Integer> tList = new ArrayList<>();
        for(int i = 0; i < S.length; i++){
            if(S[i] == '1') sList.add(i);
            if(T[i] == '1') tList.add(i);
        }

        long answer = 0;
        for(int i = 0; i < sList.size(); i++) answer += Math.abs(sList.get(i)-tList.get(i));

        long dist = answer >> 1;
        if((answer & 1) == 1) answer = (dist * dist + (dist + 1) * (dist + 1));
        else answer =2 * dist * dist;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
