package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11037
// -
public class _29_Solution_1 {
    static final int MAX = 987_654_321;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<Integer> numberList = new ArrayList<>();
        boolean[] used = new boolean[10];
        makeNonOverlappingNumber(9, 0, used, 0, numberList);
        Collections.sort(numberList);

        while(true){
            String input = in.readLine();
            if(input == null || input.equals("")) break;

            int N = Integer.parseInt(input);
            if(N >= MAX) sb.append("0\n");
            else{
                int left = 0;
                int right = numberList.size()-1;
                while(left <= right){
                    int mid = (left + right) / 2;
                    if(numberList.get(mid) <= N) left = mid + 1;
                    else right = mid - 1;
                }
                sb.append(numberList.get(left)).append("\n");
            }
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static void makeNonOverlappingNumber(int limit, int step, boolean[] used, int num, List<Integer> numberList) {
        if(step > 0) {
            numberList.add(num);
            if(limit <= step) return;
        }

        for(int plus = 1; plus < 10; plus++){
            if(used[plus]) continue;
            used[plus] = true;
            makeNonOverlappingNumber(limit, step+1, used, num * 10 + plus, numberList);
            used[plus] = false;
        }
    }
}