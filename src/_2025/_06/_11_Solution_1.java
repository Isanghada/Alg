package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14610
// - 그리디 : 1. 각 참가자 정보를 입력하며 맞춘 문제의 수가 가능한지 화인
//            2. 각 참가자 정보가 이상한지 확인
//                  - 참가자별 알 수 없음(-1) 이후 정답 여부(1, 0)가 나오는지
//                  - 문제별 정답 여부(1, 0) 이후 알 수 없음(-1)이 나오는지
//            3. 모든 문제가 최소 한명 이상이 맞췄는지 확인
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean answer = true;
        boolean[] check = new boolean[M+1];

        int[][] board = new int[N+1][M+1];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            int correct = 0;
            int unknown = 0;
            for(int m = 0; m <= M; m++) {
                board[n][m] = Integer.parseInt(st.nextToken());
                if(board[n][m] != -1) {
                    correct += board[n][m];
                    if(board[n][m] == 1) check[m] = true;
                }
                else unknown++;
            }
            correct -= board[n][0];

            // System.out.println(Arrays.toString(board[n])+" | "+correct+", "+ unknown);

            if(board[n][0] == 0 || board[n][0] == M
                    || correct > board[n][0]
                    || (correct + unknown < board[n][0])) {
                answer = false;
                break;
            }
        }

        for(int n = 1; n <= N && answer; n++){
            boolean flag = true;
            for(int m = 1; m <= M; m++){
                if(board[n][m] == -1) flag = false;
                if(!((board[n][m] >= 0 && flag) || (board[n][m] < 0 && !flag))) {
                    answer = false;
                    break;
                }
            }
        }

        for(int m = 1; m <= M && answer; m++){
            boolean flag = true;
            for(int n = 1; n <= N; n++){
                if(board[n][m] >= 0) flag = false;
                if(!((board[n][m] >= 0 && !flag) || (board[n][m] < 0 && flag))) {
                    answer = false;
                    break;
                }
            }
        }

        // System.out.println(Arrays.toString(check));

        for(int n = 1; n <= N && answer; n++){
            int m = 1;
            int correct = 0;
            while(m <= M && board[n][m] != -1) {
                correct += board[n][m];
                m++;
            }

            if(m <= M){
                int count = board[n][0] - correct;
                while(m <= M && count > 0){
                    if(check[m] == false){
                        check[m] = true;
                        count--;
                    }
                    m++;
                }
            }
        }

        if(answer){
            for(int m = 1; m <= M; m++){
                if(check[m] == false){
                    answer = false;
                    break;
                }
            }
        }

        sb.append(answer ? "YES" : "NO");


        // 정답 출력
        System.out.println(sb.toString());
    }
}
