package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2262
// - 그리디 : 가장 등수가 낮은 쪽부터 차례로 순서 결정
//            선택한 선수 기준 왼쪽, 오른쪽 중 차이가 적은 쪽 선택
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 선수의 수
        int N = Integer.parseInt(in.readLine());
        
        // 선수 정보 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        List<Integer> players = new ArrayList<>();
        while(st.hasMoreTokens()) players.add(Integer.parseInt(st.nextToken()));

        // 정답 초기화
        int answer = 0;
        // 탐색할 등수 : 낮은 등수부터 선택
        int rank = N;
        for(int i = 1; i < N; i++){
            // rank 등수인 선수의 인덱스 탐색
            int idx = players.indexOf(rank);
            
            // 가장 왼쪽에 있는 경우 : 오른쪽과 대결 
            if(idx == 0) answer += players.get(idx) - players.get(idx+1);
            // 가장 오른쪽에 있는 경우 : 왼쪽과 대결
            else if(idx == (players.size() - 1)) answer += players.get(idx) - players.get(idx-1);
            // 왼쪽과 오른쪽 중 차이가 적은 쪽과 대결
            else answer += Math.min(
                                players.get(idx) - players.get(idx-1),
                                players.get(idx) - players.get(idx+1)
                           );
            players.remove(idx);
            rank--;
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}

