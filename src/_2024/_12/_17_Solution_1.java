package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2800
// - 브루트포스 : 괄호의 개수가 최대 10개이므로 재귀를 통해 모든 경우 탐색!
public class _17_Solution_1 {
    public static boolean[] CHECK;
    public static Set<String> RESULT;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String cmd = in.readLine();

        Deque<Integer> deque = new LinkedList<>();
        List<int[]> braketList = new ArrayList<>();
        for(int i = 0; i < cmd.length(); i++){
            char c = cmd.charAt(i);
            if(c == '(') deque.offerLast(i);
            else if(c == ')') braketList.add(new int[]{deque.pollLast(), i});
        }

        RESULT = new TreeSet<>();
        CHECK = new boolean[cmd.length()];
        backtracking(0, braketList, cmd);

        // 정답 출력
        for(String result : RESULT) sb.append(result).append("\n");
        System.out.println(sb.toString());
    }

    private static void backtracking(int step, List<int[]> braketList, String cmd) {
        if(step == braketList.size()){
            StringBuilder sb = new StringBuilder();
            boolean flag = false;
            for(int i = 0; i < cmd.length(); i++){
                if(!CHECK[i]) sb.append(cmd.charAt(i));
                else flag = true;
            }
            if(flag) RESULT.add(sb.toString());

            return;
        }
        backtracking(step+1, braketList, cmd);

        CHECK[braketList.get(step)[0]] = true;
        CHECK[braketList.get(step)[1]] = true;
        backtracking(step+1, braketList, cmd);
        CHECK[braketList.get(step)[0]] = false;
        CHECK[braketList.get(step)[1]] = false;
    }
}
