package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/27532
// - 브루트포스 : 가능한 모든 R에 대해 초기값을 게산하여 시계의 개수 탐색
public class _26_Solution_1 {
    public static final int MAX_TIME = 60 * 12;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 기록 개수
        int N = Integer.parseInt(in.readLine());
        // 기록한 시간 : 정수형으로 변환!
        int[] checkTime = new int[N];
        for(int i = 0; i < N; i++) checkTime[i] = getTimeToInt(in.readLine());

        // 시계의 수
        int answer = N;
        // 초기 시간값 set
        Set<Integer> originTime = null;
        // r에 대해 모든 일지에서 초기값 계산하여 개수 비교!
        for(int r = 1; r <= MAX_TIME; r++){
            originTime = new HashSet<>();

            for(int index = 0; index < N; index++) {
                originTime.add((checkTime[index] - (r * index) + (MAX_TIME * index)) % MAX_TIME);
            }

            answer = Math.min(answer, originTime.size());
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    public static int getTimeToInt(String time){
            String[] split = time.split(":");
            int result = (Integer.parseInt(split[0]) - 1) * 60;
            result += Integer.parseInt(split[1]);
            return result;
    }
}
