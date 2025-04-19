package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/24552
// - 누적합 : 여는 괄호, 닫는 괄호의 수를 누적합으로 계산
//              더 많은 괄호가 1개더 많아지는 시점에서 더 많은 괄호의 개수가 제거해도 되는 괄호의 수
public class _19_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 괄호 입력
        String input = in.readLine();
        int open = 0;   // 여는 괄호
        int close = 0;  // 닫는 괄호
        for(char parenthesis : input.toCharArray()){
            if(parenthesis == '(') open++;
            else close++;
        }

        // 문자열 길이
        final int length = input.length();
        // 누적합
        // - count[i][0] : i 인덱스까지 중 더 많은 괄호의 수
        // - count[i][1] : i 인덱스까지 중 더 작은 괄호의 수
        int[][] count = null;
        if(open < close) count = setCount(input, '(');
        else {
            // 닫는 괄호가 더 많은 경우 입력값의 역으로 계산!!
            StringBuilder reverseInput = new StringBuilder(input);
            count = setCount(reverseInput.reverse().toString(), ')');
        }

//        System.out.println(input+", "+open+", "+close);
//        for(int i = 0; i < length; i++) System.out.println(Arrays.toString(count[i]));

        for(int i = 0; i < length; i++){
            if(count[i][0] + 1 == count[i][1]){
                sb.append(count[i][1]);
                break;
            }
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static int[][] setCount(String input, char target) {
        int[][] count = new int[input.length()][2];
        for(int i = 0; i < input.length(); i++){
            // 이전 결과 저장
            if(i != 0){
                for(int j = 0; j < 2; j++) count[i][j] = count[i-1][j];
            }
            // 누적합 계산
            if(input.charAt(i) == target) count[i][0]++;
            else count[i][1]++;
        }
        return count;
    }
}
