package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/1512
// - 브루트포스 : 가능한 주기 모두 탐색하여 최소값 반환
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 가능한 주기의 최대값
        int M = Integer.parseInt(in.readLine());
        // dan 문자열
        String dna = in.readLine();

        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        // 가능한 문자
        char[] alpArr = new char[]{'A', 'C', 'G', 'T'};
        // 각 주기별 문자 개수를 담을 Map
        Map<Character, Integer> dnaMap = new HashMap<>();
        // M부터 1까지 모든 주기 탐색
        for(int m = M; m > 0; m--){
            // 임시 정답 초기화
            int tempAnswer = 0;
            // 0 ~ m까지 모든 주기 시작 지점 탐색
            for(int start = 0; start < m; start++){
                int total = 0;  // 현재 주기의 모든 단어 수
                int max = 0;    // 현재 주기의 최대 사용 단어 수
                // dnaMap 초기화
                for(char alp : alpArr) dnaMap.put(alp, 0);
                // 시작 지점부터 주기 확인
                for(int part = start; part < dna.length(); part += m){
                    total++;
                    char cur = dna.charAt(part);
                    dnaMap.put(cur, dnaMap.get(cur) + 1);
                    max = Integer.max(max, dnaMap.get(cur));
                }
                // 변환 개수만큼 증가
                tempAnswer += (total - max);
            }
            // 정답 최소값으로 갱신
            answer = Math.min(answer, tempAnswer);
        }
        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
