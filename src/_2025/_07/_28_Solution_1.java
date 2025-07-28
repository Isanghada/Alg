package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/30105
// -
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] positions = new int[N];
        Set<Integer> positionSet = new HashSet<>();
        for(int i = 0; i < N; i++){
            positions[i] = Integer.parseInt(st.nextToken());
            positionSet.add(positions[i]);
        }

        List<Integer> answerList = new ArrayList<>();
        Set<Integer> checkSet = new HashSet<>();
        for(int i = 1; i < N; i++){
            int diff = positions[i] - positions[0];

            if(!checkSet.contains(diff)){
                if(isPossible(positionSet, diff)) answerList.add(diff);
                checkSet.add(diff);
            }
        }

        Collections.sort(answerList);

        sb.append(answerList.size()).append("\n");
        for(int answer : answerList) sb.append(answer).append(" ");

        // 정답 출력
        System.out.println(sb);
    }

    private static boolean isPossible(Set<Integer> positionSet, int k) {
        for(int position : positionSet){
            if(positionSet.contains(position + k) || positionSet.contains(position - k)) continue;
            return false;
        }
        return true;
    }
}
