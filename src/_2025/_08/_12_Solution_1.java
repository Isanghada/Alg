package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14527
// -
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;

        Map<Integer, Integer> cowMap = new HashMap<>();
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(!cowMap.containsKey(y)) cowMap.put(y, 0);
            cowMap.put(y, cowMap.get(y)+x);
        }

        List<Integer> milks = new ArrayList<>(cowMap.keySet());
        Collections.sort(milks);

        int answer = 0;
        int left = 0;
        int right = milks.size()-1;
        while(left <= right){
            int leftMilk = milks.get(left);
            int rightMilk = milks.get(right);

            answer = Math.max(answer, milks.get(left) + milks.get(right));

            int leftCow = cowMap.get(leftMilk);
            int rightCow = cowMap.get(rightMilk);
            int minCount = Math.min(leftCow, rightCow);

            cowMap.put(leftMilk, leftCow - minCount);
            cowMap.put(rightMilk, rightCow - minCount);

            if(cowMap.get(leftMilk) == 0) left++;
            if(cowMap.get(rightMilk) == 0) right--;
        }


        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
