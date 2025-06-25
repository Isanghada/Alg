package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/31091
// - 이분 탐색 : 거짓말하는 사람의 수를 기준으로 이분 탐색을 통해 가능한지 확인
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();

        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++){
            int k = Integer.parseInt(st.nextToken());
            if(k > 0) plus.add(k);
            else minus.add(k);
        }

        Collections.sort(plus);
        Collections.sort(minus);

        List<Integer> answers = new ArrayList<>();
        for(int lier = 0; lier <= N; lier++){
            int count = 0;
            count += minus.size() - upperBoundBinarySearch(minus, -lier);
            count += plus.size() - upperBoundBinarySearch(plus, lier);

            if(lier == count) answers.add(lier);
        }

        sb.append(answers.size()).append("\n");
        for(int lier : answers) sb.append(lier).append(" ");

        // 정답 출력
        System.out.println(sb);
    }

    private static int upperBoundBinarySearch(List<Integer> target, int lier) {
        int left = 0;
        int right = target.size() - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(target.get(mid) > lier) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
}