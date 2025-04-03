package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23254
// - DP : 광고를 본 경우, 광고를 보지 않은 경우의 최대값을 갱신해나가면 탐색
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        // 선택지의 수
        int N = Integer.parseInt(in.readLine());

        // 정답 초기화
        // - answers[0] : 광고를 보지 않은 경우의 최대 사람 수
        // - answers[1] : 광고를 본 경우의 최대 사람 수
        int[] answers = new int[]{1, 1};
        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            String command1 = st.nextToken();   // 선택지
            String command2 = st.nextToken();   // 선택지

            // 광고를 본 경우
            // - 광고를 보지 않은 경우의 최대값
            // - 광고를 본 경우에 새로운 선택지 중 최대값
            if(answers[1] > 0) answers[1] = Math.max(answers[0], Math.max(calculation(answers[1], command1), calculation(answers[1], command2)));

            // 광고를 보지 않은 경우
            // - 광고를 보지 않은 경우에 새로운 선택지 중 최대값
            if(answers[0] > 0) answers[0] = Math.max(calculation(answers[0], command1), calculation(answers[0], command2));

            // 두 경우 모두 0이하인 경우 종료
            if(answers[0] <= 0 && answers[1] <= 0) break;
        }

        // 두 경우 중 최대값 선택
        int answer = Math.max(answers[0], answers[1]);

        // 정답 반환
        // - 0 이하인 경우 실패, 0 초과인 경우 성공
        sb.append(answer <= 0 ? "ddong game" : answer);
        System.out.println(sb);
    }

    private static int calculation(int num, String command) {
        int result = num;

        char operation = command.charAt(0);
        int operand = Integer.parseInt(command.substring(1));

        if(operation == '+') result += operand;
        else if(operation == '-') result -= operand;
        else if(operation =='*') result *= operand;
        else result /= operand;

        return result;
    }
}
