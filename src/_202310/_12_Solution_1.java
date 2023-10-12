package _202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1016
// - min ~ max 범위가 1000000인 것을 너무 늦게 깨달았다..
// - 에라토스테네스의 체를 활용해 각 제곱수로 만들 수 있는 수를 체크하고,
//   제곱ㄴㄴ인 경우를 카운팅하여 결과 반환
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202310/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 최소값 입력
        long min = Long.parseLong(st.nextToken());
        // 최대값 입력
        long max = Long.parseLong(st.nextToken());

        // (최소 ~ 최대) 범위 계산
        long range = max - min + 1;
        // 최대값을 초과하지 않는 제곱근 계산
        long sqrtMax = (long)Math.sqrt(max);

        // 에라토스테네스의 체 초기화 : 범위를 기준으로 생성
        // - min => 인덱스 0
        // - max => 인덱스 max - min
        boolean[] squareNoNo = new boolean[(int)range];

        // 2부터 sqrtMax까지 모든 수를 체크
        for(long num = 2; num <= sqrtMax; num++){
            // 제곱수 계산
            long square = num * num;
            // 시작값 계산 : min을 square로 나눈 몫(나머지가 0이 아닌 경우 +1)
            long start = min % square == 0 ? min / square : min / square + 1;
            // idx부터 차례로 수를 올리면 idx + square가 max 이하인 경우 true로 변환
            for(long idx = start; idx * square <= max; idx++){
                squareNoNo[(int)((square * idx) - min)] = true;
            }
        }

        // 정답 초기화
        int answer = 0;
        // 에라토스테네스의 체 확인
        for(int num = 0; num < range; num++){
            // 제곱ㄴㄴ인 경우 정답 증가
            if(!squareNoNo[num]) answer++;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
