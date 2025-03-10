package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1897
// - BFS : 각 단어에서 가능한 다음 단어를 차례로 탐색!
public class _10_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int D = Integer.parseInt(st.nextToken());   // 단어의 수
        String start = st.nextToken();              // 시작 단어

        // 사전 정보 입력
        String[] words = new String[D];
        for(int i = 0; i < D; i++) words[i] = in.readLine();
        // 길이 기준으로 정렬!
        Arrays.sort(words, Comparator.comparingInt(String::length));
        // System.out.println(Arrays.toString(words));

        // 정답 반환
        // - bfs를 통해 가장 긴 단어 탐색
        sb.append(bfs(words, start, D));
        System.out.println(sb);
    }

    private static String bfs(String[] words, String start, int d) {
        // 시작 단어
        int idx = 0;
        while(!words[idx].equals(start)) idx++;

        // deque 초기화
        Deque<Integer> deque = new LinkedList<>();
        boolean[] visited = new boolean[d];

        deque.offerLast(idx);
        visited[idx] = true;

        while(!deque.isEmpty()){
            idx = deque.pollFirst();

            // 다음 단어 탐색
            for(int next = idx + 1; next < d; next++){
                // 이미 체크한 단어인 경우 패스
                if(visited[next]) continue;
                // - 길이 차이가 1인 경우 : 다음 단어가 될 수 있음!
                if(words[idx].length() + 1 == words[next].length()){
                    // 추가된 알파벳의 수
                    int count = 0;
                    for(int a = 0, b = 0;a < words[idx].length() && b < words[next].length();){
                        if(words[idx].charAt(a) != words[next].charAt(b)) count++;
                        else a++;
                        b++;
                    }
                    // 가능한 경우 : deque 추가!
                    if(count <= 1){
                        deque.offerLast(next);
                        visited[next] = true;
                    }
                // 길이 차이가 1 초과인 경우 : 종료!
                }else if(words[idx].length() + 1 < words[next].length()) break;
            }
        }

        return words[idx];
    }
}
