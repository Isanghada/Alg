package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3096
// - 조합 : 왼쪽 마을 2개를 선택하고 조합 가능한 오른쪽 마을 개수 계산
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 마을의 수
        int M = Integer.parseInt(st.nextToken());   // 배의 수

        // 연결 정보 초기화
        // - citySet[left] : left에 연결된 right마을 정보
        boolean[][] cityBit = new boolean[N+1][N+1];

        // 배 연결!
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int left = Integer.parseInt(st.nextToken());    // 왼쪽 마을
            int right = Integer.parseInt(st.nextToken());   // 오른쪽 마을
            
            cityBit[left][right] = true;
        }

        // 정답 초기화
        long answer = 0;
        // 왼쪽 마을1 선택
        for(int a = 1; a <= N; a++){
            // 왼쪽 마을2 선택
            for(int b = a+1; b <= N; b++){
                // 두 마을에 모두 연결된 오른쪽 마을의 수
                int count = 0;
                for(int right = 1; right <= N; right++){
                    if(cityBit[a][right] && cityBit[b][right]) count++;
                }
                // count 중에 2개를 선택하는 개수 : count! / (count-2)!2!
                answer += ((count * (count - 1)) / 2);
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
