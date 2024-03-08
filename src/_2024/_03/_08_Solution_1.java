package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/1599
// - 정렬 : 민식어의 순서를 Map으로 구성하고, 이를 이용해 정렬
//          이떄, ng의 경우 한 알파벳이므로 x로 치환하여 정렬 후 원래 알파벳으로 변환
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 민식어 순서 Map
        Map<Character, Integer> minsikMap = new HashMap<>();
        char[] minsik = new char[]{'a', 'b', 'k', 'd', 'e', 'g', 'h', 'i', 'l', 'm', 'n', 'x', 'o', 'p', 'r', 's', 't', 'u', 'w', 'y'};
        for(int order = 0; order < minsik.length; order++){
            minsikMap.put(minsik[order], order);
        }
//        System.out.println(minsikMap);

        // 단어 개수
        int N = Integer.parseInt(in.readLine());
        String[] wordArr = new String[N];
        // 단어 입력 : 입력할 때 ng는 x로 치환
        for(int i = 0; i < N; i++) wordArr[i] = in.readLine().replace("ng", "x");
        // 단어 정렬 : 민식어 순서 Map을 활용하여 정렬
        Arrays.sort(wordArr, (A, B) -> {
            int idxLimit = Math.min(A.length(), B.length());
            for(int idx = 0; idx < idxLimit; idx++){
                if(minsikMap.get(A.charAt(idx)) > minsikMap.get(B.charAt(idx))) return 1;
                else if(minsikMap.get(A.charAt(idx)) < minsikMap.get(B.charAt(idx))) return -1;
            }
            if(A.length() > B.length()) return 1;
            else if(A.length() < B.length()) return -1;
            else return 0;
        });

        // 정답 출력
        // - 출력 시 x를 ng로 변환 후 반환
        for(String word: wordArr) sb.append(word.replace("x", "ng")).append("\n");
        System.out.println(sb);
    }
}
