package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18119
// - 비트마스킹 : 단어 마다 필요한 알파벳 체크, 기억하고 있는 알파벳 체크(Bit)
//                쿼리마다 기억하고 있는 알파벳을 갱신하고 단어가 가능한지 확인!
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 단어의 수
        int M = Integer.parseInt(st.nextToken());   // 쿼리의 수

        int[] alpBit = new int[27];
        alpBit[0] = 1;
        for(int i = 1; i < 27; i++) alpBit[i] = alpBit[i-1] << 1;

        // 단어 정보 입력
        int[] words = new int[N];
        for(int i = 0; i < N; i++){
            String word = in.readLine();
            for(char alp : word.toCharArray()) {
                words[i] |= alpBit[alp-'a'];
            }
        }

        // 모음 모음 => 잊지않는 알파벳
        Set<Character> vowel = new HashSet<>(Arrays.asList('a','e','i','o','u'));

        // 기억하고 있는 알파벳 : 초기 설정(모든 알파벳)
        int alphabetBit = alpBit[26] - 1;

        // 쿼리 체크!
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            // 쿼리 명령어
            // - 1 : 알파벳을 잊는다
            // - 2 : 알파벳을 기억해낸다
            int O = Integer.parseInt(st.nextToken());
            // 쿼리 명령을 실행할 알파벳
            char X = st.nextToken().charAt(0);

            // 1번 명령이고, 모음이 아닌 경우 알파벳 제거
            if(O == 1 && !vowel.contains(X)) alphabetBit &= (alpBit[26] - 1 - alpBit[X-'a']);
            // 2번 명령인 경우 알파벳 추가
            else if(O == 2) alphabetBit |= alpBit[X-'a'];

//            System.out.println(Integer.toBinaryString(alphabetBit));

            // 단어 체크!
            int count = 0;
            for(int i = 0; i < N; i++){
                int cur = alphabetBit & words[i];
//                System.out.println(Integer.toBinaryString(cur));
                if(cur == words[i]) count++;

            }
            sb.append(count).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
