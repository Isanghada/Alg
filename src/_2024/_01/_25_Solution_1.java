package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25391
// - 그리디 : 심판 기준 내림차순의 K명은 무조건 수상하게 되어있으므로,
//            나머지 인원 중 M명을 주최자 기준 내림차순으로 선정
public class _25_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 설정값 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 인원수
        int M = Integer.parseInt(st.nextToken());   // 특별상 인원수
        int K = Integer.parseInt(st.nextToken());   // 본상 인원수

        // 점수 배열
        // - scoreArr[i][0] : i번째 인원의 주최자 점수
        // - scoreArr[i][1] : i번째 인원의 심판 점수
        int[][] scoreArr = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            scoreArr[i][0] = Integer.parseInt(st.nextToken());
            scoreArr[i][1] = Integer.parseInt(st.nextToken());
        }
        // 정답 초기화
        long answer = 0;
        
        // 심판 점수 기준 내림차순 정렬
        Arrays.sort(scoreArr, (o1, o2) ->{
            return o2[1] - o1[1];
        });
        
        // 본상 K명 선정 : 확인을 위해 Set에 주최자 점수 추가
        Set<Integer> award = new HashSet<>();
        for(int i = 0; i < K; i++) {
            answer += scoreArr[i][0];
            award.add(scoreArr[i][0]);
        }

        // 주최자 기준 내림차순 정렬
        Arrays.sort(scoreArr, (o1, o2) ->{
            return o2[0] - o1[0];
        });

        // 본상 이외에 나머지 인원 중 높은 점수 M명 선정
        for(int i = 0; i < N; i++){
            if(award.contains(scoreArr[i][0])) continue;

            answer += scoreArr[i][0];

            if(--M <= 0) break;
        }

        // 결과 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
