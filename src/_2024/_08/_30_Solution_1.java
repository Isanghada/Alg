package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1062
// - 브루트포스 : K개의 글자를 선택한 후 가능한 단어의 개수 체크!
public class _30_Solution_1 {
    public static int ANSWER, N, K;
    public static boolean[] VISITED;
    public static String[] WORD_ARR;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());       // 단어의 개수
        K = Integer.parseInt(st.nextToken()) - 5;   // 가르칠 수 있는 글자의 수
                                                    // - a, c, i, n, t 5개는 무조건 포함!

        // 단어 정보 입력!
        WORD_ARR = new String[N];
        for(int n = 0; n < N; n++){
            WORD_ARR[n] = in.readLine()
                    .replaceAll("anta", "")
                    .replaceAll("tica", "");
        }

        // 글자의 수가 부족한 경우 0 반환
        if(K < 0) sb.append(0);
        // 모든 글자를 가르칠 수 있는 경우 N 반환
        else if(K == 21) System.out.println(N);
        else{
            // 방문 체크! : 글자 사용 가능 여부!
            VISITED = new boolean[26];
            VISITED['a'-'a'] = true;
            VISITED['c'-'a'] = true;
            VISITED['i'-'a'] = true;
            VISITED['n'-'a'] = true;
            VISITED['t'-'a'] = true;

            // 정답 초기화
            ANSWER = 0;
            // 백트래킹을 통해 모두 탐색!
            backtracking(0, 0);
            sb.append(ANSWER);
        }
        // 정답 반환
        System.out.println(sb);
    }

    private static void backtracking(int alp, int size) {
        // 가능한 만큼 글자를 가르친 경우 : 개수 체크!
        if(size == K){
            int count = 0;
            for(String word : WORD_ARR){
                boolean flag = true;
                for(char w : word.toCharArray()){
                    if(!VISITED[w - 'a']){
                        flag = false;
                        break;
                    }
                }
                if(flag)count++;
            }
            ANSWER = Math.max(ANSWER, count);
        }else{
            // 알파벳 선택!
            for(int i = alp; i < 26; i++){
                if(!VISITED[i]){
                    VISITED[i] = true;
                    backtracking(i+1, size+1);
                    VISITED[i] = false;
                }
            }
        }
    }
}