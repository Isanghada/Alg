package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9763
// - 브루트포스 : 친밀도 게산에서 3개의 마을 중 두번째 마을은 모든 계산에 활용되므로
//                두번째 마을을 기준으로 친밀도가 작은 마을 2개를 선택하여 계산.
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 마을 개수
        int N = Integer.parseInt(in.readLine());

        // 마을 위치 입력
        int[][] villageArr = new int[N][3];
        StringTokenizer st = null;
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < 3; c++) villageArr[r][c] = Integer.parseInt(st.nextToken());
        }

        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        // 기준 마을 : 두번째 마을
        for(int i = 0; i < N; i++){
            // 최소 친밀도 2개
            int min1 = 1000000, min2 = 1000000;
            for(int j = 0; j < N; j++){
                if(i != j){
                    // 현재 마을과 친밀도 계산
                    int d = Math.abs(villageArr[i][0]-villageArr[j][0]) +
                            Math.abs(villageArr[i][1]-villageArr[j][1]) +
                            Math.abs(villageArr[i][2]-villageArr[j][2]);

                    // 친밀도를 최소값으로 갱신
                    if(min1 > d){
                        min2 = min1;
                        min1 = d;
                    }else if(min2 > d) min2 = d;
                }
            }
            // 정답 최소값으로 갱신
            answer = Math.min(answer, min1 + min2);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
