package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1999
// - DP : 주어진 범위 B에 따라 가능한 경우 모두 계산하고, 질문에 따라 답변 출력
public class _22_Solution_1 {
    // 노드 클래스
    static class Node{
        int max;    // 최대값
        int min;    // 최소값
        public Node(int value){
            this.max = value;
            this.min = value;
        }
        // max 설정
        public void setMax(int value){
            this.max = Math.max(this.max, value);
        }
        // min 설정
        public void setMin(int value){
            this.min = Math.min(this.min, value);
        }
        // 정답 계산 : 최대값 - 최소값
        public int getAnswer(){
            return this.max - this.min;
        }
    }
    
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행렬 크기
        int B = Integer.parseInt(st.nextToken());   // 부분 행렬 크기
        int K = Integer.parseInt(st.nextToken());   // 질문 개수

        int[][] map = new int[N][N];    // 행렬 초기화
        Node[][] dp = new Node[N][N];   // dp 초기화
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                dp[r][c] = new Node(map[r][c]);
            }
        }

        // 시작 인덱스 제한 : B 크기의 부분 행렬을 만들기 위해 가능한 최대 인덱스
        final int LIMIT = N - B;
        for(int r = 0; r <= LIMIT; r++){
            // 현재 행에서의 부분 행렬 범위
            int rangeR = r + B;
            for(int c = 0; c <= LIMIT; c++){
                // 현재 열에서의 부분 행렬 벙뮈
                int rangeC = c + B;
                // 부분 행렬의 모든 값 비교 후 최대, 최소 설정
                for(int subR = r; subR < rangeR; subR++){
                    for(int subC = c; subC < rangeC; subC++){
                        dp[r][c].setMax(map[subR][subC]);
                        dp[r][c].setMin(map[subR][subC]);
                    }
                }
            }
        }

        while(K-- > 0){
            st = new StringTokenizer(in.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1; // 부분 행렬 시작 행
            int col = Integer.parseInt(st.nextToken()) - 1; // 부분 행렬 시작 열

            // dp를 통해 계산된 값 출력
            sb.append(dp[row][col].getAnswer()).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString().trim());
    }
}
