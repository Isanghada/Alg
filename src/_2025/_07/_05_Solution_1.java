package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/24891
// -
public class _05_Solution_1 {
    static boolean IS_END;
    static boolean[] VISITED;
    static String[] WORDS, SELECTED;
    static int L, N;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        WORDS = new String[N];
        for(int i = 0; i < N; i++) WORDS[i] = in.readLine();

        Arrays.sort(WORDS);
        System.out.println(Arrays.toString(WORDS));

        IS_END = false;
        VISITED = new boolean[N];
        SELECTED = new String[L];

        backtracking(0);

        if(IS_END) for(String word : SELECTED) sb.append(word).append("\n");
        else sb.append("NONE");


        // 정답 반환
        System.out.println(sb);
    }

    private static void backtracking(int step) {
        if(step == L){
            if(check()) IS_END = true;
        }else{
            for(int i = 0; !IS_END && i < N; i++){
                if(VISITED[i]) continue;
                VISITED[i] = true;
                SELECTED[step] = WORDS[i];
                backtracking(step+1);
                VISITED[i] = false;
            }
        }
    }

    private static boolean check() {
        for(int i = 0; i < L; i++){
            for(int j = 0; j < L; j++){
                if(SELECTED[i].charAt(j) != SELECTED[j].charAt(i)) return false;
            }
        }

        return true;
    }
}
