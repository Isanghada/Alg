package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2458
// - 플로이드 : 각 번호별로 연결되어있는지를 기준으로 순위를 확인할 수 있는지 체크!
// - DFS 방식 참고 : https://velog.io/@lifeisbeautiful/%EB%B0%B1%EC%A4%80-2458%EB%B2%88-%ED%82%A4-%EC%88%9C%EC%84%9C-Java
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 학생의 수
        int M = Integer.parseInt(st.nextToken());   // 비교 횟수

        // 비교 정보 입력
        boolean[][] check = new boolean[N+1][N+1];
        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            check[a][b] = true;
        }

        // 플로이드-와샬을 통해 모든 연결 탐색!
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(check[i][k] && check[k][j]) check[i][j] = true;
                }
            }
        }

        // 각 학생별로 식별가능한 정보 계산
        int[] count = new int[N+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(check[i][j] || check[j][i]) count[i]++;
            }
        }

        // 모든 학생 정보를 식별가능한 경우 정답 증가!
        int answer = 0;
        for(int n = 1; n <= N; n++) answer += (count[n] == (N-1)) ? 1 : 0;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
