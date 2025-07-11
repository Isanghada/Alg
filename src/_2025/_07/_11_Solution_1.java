package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/33666
// - 누적합 : 누적합을 통해 각 메시지가 전달되는 횟수 계산
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 사람 수
        int M = Integer.parseInt(st.nextToken());   // 준비된 메시지 수

        // 사람별 멘션 횟수 정보
        int[] P = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 평균 멘션 횟수
        int avg = getAvgMessage(P, N);

        // 각 메시지 전달 횟수
        int[] counts = new int[M+1];
        // 매크로 플래그
        boolean flag = true;
        for(int i = 0; i < N; i++){
            if(P[i] <= avg){
                // 서로 다른 멘션에 같은 메시지가 전달될 경우 flag 갱신 후 종료
                if(P[i] > M) {
                    flag = false;
                    break;
                }
                counts[0] += (P[i] / M) + 1;
                counts[P[i] % M]--;
            }else{
                counts[0]++;
                counts[1]--;
            }
        }
        
        // 매크로가 아닌 경우
        if(flag){
            // 누적합을 통해 메시지 전달 횟수 계산
            for(int i = 1; i < M; i++) counts[i] += counts[i-1];
            // System.out.println(Arrays.toString(counts));

            // 정답 출력
            for(int i = 0; i < M; i++) sb.append(counts[i]).append(" ");
        // 매크로인 경우 -1 출력
        } else sb.append(-1);

        // 정답 출력
        System.out.println(sb.toString().trim());
    }

    private static boolean check(int[] counts, int total, int m) {
        for(int i = 0; i < m; i++){
            if(counts[i] == 0 | counts[i] == total) continue;
            return true;
        }
        return false;
    }

    private static int getAvgMessage(int[] p, int n) {
        long avg = 0;
        int count = 0;

        for(int i = 0; i < n; i++){
            if(p[i] > 1) {
                avg += p[i];
                count++;
            }
        }
        if(count == 0) return 0;
        avg /= count;

        return (int)avg;
    }
}
