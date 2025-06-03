package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32029
// - 그리디 : 과제를 기한을 기준으로 정렬 후 각 과제 전에 잠을 자는 경우 모두 탐색
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 과제의 수
        int A = Integer.parseInt(st.nextToken());   // 과제 완료 시간
        int B = Integer.parseInt(st.nextToken());   // 단축 기준 시간

        // 과제 정보 입력
        int[] T = new int[N+1];
        st = new StringTokenizer(in.readLine());
        for(int n = 1; n <= N; n++) T[n] = Integer.parseInt(st.nextToken());

        // 과제 정렬
        Arrays.sort(T);

        int answer = 0;
        // 과제 단축 시간!
        for(int a = 0; a < A; a++){
            // 잠을 자는 기준!
            for(int n = 1; n <= N; n++){
                answer = Math.max(answer, getCount(N, T, A, A - a, n, B * a));
            }
        }


        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static int getCount(int n, int[] t, int beforeTime, int afterTime, int sleepIdx, int sleepTime) {
        int count = 0;
        int time = 0;

        for(int i = 1; i < sleepIdx; i++){
            if(time + beforeTime <= t[i]){
                time += beforeTime;
                count++;
            }
        }

        time += sleepTime;
        for(int i = sleepIdx; i <= n; i++){
            if(time + afterTime <= t[i]){
                time += afterTime;
                count++;
            }
        }

        return count;
    }
}
