package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23629
// - 구현 : 문자를 차례로 변경하여 계산
//          에러 처리!!!!!!!
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 입력
        String input = in.readLine();
        // 연산자 기준으로 값 분할!
        StringTokenizer st = new StringTokenizer(input, "+-x/=", true);
        // 숫자 모음
        Deque<String> number = new LinkedList<>();
        // 연산자 모음
        Deque<String> operator = new LinkedList<>();
        while(st.hasMoreTokens()){
            String value = st.nextToken();
            if(isOperator(value)) operator.offerLast(value);
            else number.offerLast(value);
        }
        // 숫자와 연산자의 개수가 다른 경우 Madness! 출력
        if(number.size() != operator.size()) sb.append("Madness!");
        else{
            // 정답
            try {
                long answer = getStringToNumber(number.pollFirst());
                // 식 변환 출력!
                sb.append(answer);
                while(!number.isEmpty()){
                    // 현재 숫자
                    try{
                        long num = getStringToNumber(number.pollFirst());
                        // 현재 연산자
                        String op = operator.pollFirst();

                        // 식 변환 출력
                        sb.append(op).append(num);

                        // 연산자에 따라 값 계산
                        if(op.equals("+")) answer += num;
                        else if(op.equals("-")) answer -= num;
                        else if(op.equals("x")) answer *= num;
                        else if(op.equals("/")) answer /= num;
                    }catch (Exception e){
                        sb.append("Madness!");
                        break;
                    }

                }
                // 식 변환 출력
                sb.append(operator.pollFirst()).append("\n");
                // 정답 출력
                sb.append(getNumberToString(answer));
            }
            catch (Exception e){
                sb.append("Madness!");
            }
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static String getNumberToString(long num) {
        String result = String.valueOf(num)
                .replaceAll("0", "ZERO")
                .replaceAll("1", "ONE")
                .replaceAll("2", "TWO")
                .replaceAll("3", "THREE")
                .replaceAll("4", "FOUR")
                .replaceAll("5", "FIVE")
                .replaceAll("6", "SIX")
                .replaceAll("7", "SEVEN")
                .replaceAll("8", "EIGHT")
                .replaceAll("9", "NINE");
        return result;
    }

    private static long getStringToNumber(String value) throws Exception {
        value = value.replaceAll("ONE", "1")
                .replaceAll("TWO", "2")
                .replaceAll("THREE", "3")
                .replaceAll("FOUR", "4")
                .replaceAll("FIVE", "5")
                .replaceAll("SIX", "6")
                .replaceAll("SEVEN", "7")
                .replaceAll("EIGHT", "8")
                .replaceAll("NINE", "9")
                .replaceAll("ZERO", "0");
        return Long.parseLong(value);
    }


    private static boolean isOperator(String value) {
        if(value.equals("+") ||
                value.equals("-") ||
                value.equals("x") ||
                value.equals("/") ||
                value.equals("=")
        ) return true;
        else return false;
    }
}
