package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.acmicpc.net/problem/5527
// - DP : 간 구간을 구하고, 해당 구간을 바꿧을 때 길이를 비교하여 최대값 계산
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] lights = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> groups = new ArrayList<>();
        int answer = 0;
        int count = 1;
        for(int i = 1; i < N; i++){
            if(lights[i] != lights[i-1]) count++;
            else{
                groups.add(count);
                count = 1;
            }
        }
        groups.add(count);

        for(int i = 0; i < groups.size(); i++){
            count = groups.get(i);
            if(i != 0) count += groups.get(i-1);
            if(i != groups.size()-1) count += groups.get(i+1);

            answer = Math.max(answer, count);
        }
        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}