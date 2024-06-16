package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/19940
// -
public class _16_Solution_1 {
    public static int COUNT, N;
    public static int[] ANSWER;
    public static int[] CALCULATE = new int[] {60, 10, -10, 1, -1};
    public static Map<Integer, int[]> DP;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_16_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        DP = initDP(60, 5);
//        for(int i : DP.keySet()){
//            System.out.println(i+", "+Arrays.toString(DP.get(i)));
//        }

        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            N = Integer.parseInt(in.readLine());

            ANSWER = new int[5];
            ANSWER[0] = N / 60;
            N %= 60;

            for(int i = 0; i < 5; i++) {
                ANSWER[i] += DP.get(N)[i];
                sb.append(ANSWER[i]).append(" ");
            }
            sb.append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static Map<Integer, int[]> initDP(int size, int n) {
        Map<Integer, int[]> result = new HashMap<>();
        result.put(0, new int[n]);

        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(0);
//        int count = 0;
        while(!deque.isEmpty()){
            int cur = deque.pollFirst();
//            System.out.println((count++ )+". "+cur+", "+Arrays.toString(result.get(cur)));
//            if(count++ == 100) break;
            for(int i = 4; i >= 0; i--){
                int next = cur + CALCULATE[i];
                if(next < 0 || next > size) continue;

                int[] temp = new int[n];
                for(int j = 0; j < n; j++)temp[j] = result.get(cur)[j];
                temp[i]++;

                if(!result.containsKey(next) || isChange(result.get(next), temp)) {
                    result.put(next, temp);
                    deque.offerLast(next);
                }
            }

        }

        return result;
    }

    private static boolean isChange(int[] origin, int[] cur) {
        int countOrigin = 0, countCur = 0;
        StringBuilder stringOrigin = new StringBuilder();
        StringBuilder stringCur = new StringBuilder();
        for(int i = 0; i < origin.length; i++){
            countOrigin += origin[i];
            stringOrigin.append(origin[i]);
            countCur += cur[i];
            stringCur.append(cur[i]);
        }
        if(countOrigin == countCur){
//            System.out.println(stringOrigin +", "+stringCur+"-------------/"
//                    +stringOrigin.toString().compareTo(stringCur.toString()));
            return (stringOrigin.toString().compareTo(stringCur.toString()) > 0);
        }
        else {
            return (countCur < countOrigin);
        }

    }
}
