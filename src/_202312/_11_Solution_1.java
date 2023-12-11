package _202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

// https://www.acmicpc.net/problem/1013
// - 정규 표현식을 활용한 패턴 매칭!
public class _11_Solution_1 {
    public static final String PATTERN = "((100+1+)|(01))+";
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202312/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        while(T-- > 0){
            String signal = in.readLine();
            sb.append(Pattern.matches(PATTERN, signal) ? "YES" : "NO").append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
