package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/33576
// - 누적합 : 벽 내구도의 누적합을 남아있는 벽의 위치를 활용해 체크하며 게산!
public class _02_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 자습실 구역
        int M = Integer.parseInt(st.nextToken());   // 벽의 개수
        int Q = Integer.parseInt(st.nextToken());   // 학생 수

        long[] W = new long[N+2];     // 위치별 벽 내구도 누적합!
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine());
            int w = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            W[w] += d;
        }

        for(int i = 1; i <= N+1; i++) W[i] += W[i-1];


        int left = 0;
        int right = N+1;
        // 학생별 결과 출력!
        while(Q-- > 0){
            int p = Integer.parseInt(in.readLine());    // 학생 위치
            if(p <= left || p >= right) {
                sb.append("0\n");
                continue;
            }

            long leftMove = W[p] - W[left];
            long rightMove = W[right] - W[p-1];

            if(leftMove < rightMove || (leftMove == rightMove && p <= (N-p+1))){
                left = p;
                sb.append(leftMove);
            }else{
                right = p;
                sb.append(rightMove);
            }
            sb.append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
