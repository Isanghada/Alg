package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28706
// - DP : 모듈러 연산을 통해 값을 7개로 제한하여 차례로 계산
public class _05_Solution_1 {
    // 명령 클래스
    public static class Command{
        char type;  // 연산 타입
        int num;    // 값
        public Command(char type, int num){
            this.type = type;
            this.num = num;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            // 연산의 수
            int N = Integer.parseInt(in.readLine());

            // 연산자 배열
            Command[][] commands = new Command[N][2];
            for(int n = 0; n < N; n++){
                st = new StringTokenizer(in.readLine());
                commands[n][0] = commandInput(st);
                commands[n][1] = commandInput(st);
            }
            // 결과를 담을 Set
            Set<Integer> dpSet = new HashSet<>();
            // 초기값 설정
            dpSet.add(1);

            // 각 연산에 따라 모든 결과를 dpSet에 저장
            for(Command[] c : commands) dpSet = DP(dpSet, c);
            // 마지막 계산 후 0이 있다면 가능, 없다면 불가능
            sb.append(dpSet.contains(0) ? "LUCKY\n" : "UNLUCKY\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static Set<Integer> DP(Set<Integer> dpSet, Command[] commands) {
        Set<Integer> result = new HashSet<>();

        for(Command c : commands){
            for(int val : dpSet){
                if(c.type == '+') result.add((val+c.num) % 7);
                else result.add((val * c.num) % 7);
            }
        }

        return result;
    }

    private static Command commandInput(StringTokenizer st) {
        Command result = new Command(
                st.nextToken().charAt(0),
                Integer.parseInt(st.nextToken())
        );
        return result;
    }
}
