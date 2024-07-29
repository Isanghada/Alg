package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/20437
// - 슬라이딩 윈도우 : 알파벳별 인덱스 리스트를 정리하고,
//                     K개가 포함될 때의 길이를 계산하며 정답 갱신!
public class _29_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_29_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 문자열
            char[] W = in.readLine().toCharArray();
            // 반복 포함 횟수
            int K = Integer.parseInt(in.readLine());

            // 알파벳별 인덱스 리스트
            List<Integer>[] alpIntexList = new List[26];
            for(int alp = 0; alp < 26; alp++) alpIntexList[alp] = new ArrayList<>();

            // 인덱스 정보 입력!
            for(int index = 0; index < W.length; index++) alpIntexList[W[index]-'a'].add(index);
            
            int min = 1000000;  // 최소값 초기화
            int max = 0;        // 최대값 초기화
            // 모든 알파벳 탐색!
            for(int alp = 0; alp < 26; alp++){
                // K개보다 적을 경우 패스
                if(alpIntexList[alp].size() < K) continue;
                int left = 0;
                int right = K-1;
                while(right < alpIntexList[alp].size()){
                    // 길이 계산!
                    int length = alpIntexList[alp].get(right) - alpIntexList[alp].get(left) + 1;
                    // 최소값, 최대값 갱신
                    min = Math.min(min, length);
                    max = Math.max(max, length);
                    left++;
                    right++;
                }
            }

            if(min == 100000 || max == 0) sb.append(-1);
            else sb.append(min).append(" ").append(max);
            sb.append("\n");
         }

        // 정답 반환
        System.out.println(sb);
    }
}