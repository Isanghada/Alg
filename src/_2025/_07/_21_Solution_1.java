package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

// https://www.acmicpc.net/problem/14906
// - 정규표현식 : 스럼프는 정규 표현식으로 스러피는 재귀를 통해 체크!
public class _21_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 개수
        int N = Integer.parseInt(in.readLine());

        sb.append("SLURPYS OUTPUT\n");
        while(N-- > 0){
            String word = in.readLine();        // 최초 문자열
            String slimp = "", slump = "";
            // 스림프, 스럼프 분리!
            for(int i = word.length() - 1; i >= 0; i--){
                if(word.charAt(i) == 'C' || word.charAt(i) == 'H'){
                    slimp = word.substring(0, i+1);
                    slump = word.substring(i+1);
                    break;
                }
            }

            // 스림프 + 스럼프로 구성되어있는지 체크
            sb.append(isSlimp(slimp) && isSlump(slump) ? "YES\n" : "NO\n");
        }
        sb.append("END OF OUTPUT\n");

        // 정답 출력
        System.out.println(sb);
    }

    // 스럼프 : 정규 표현식을 통해 체크
    private static boolean isSlump(String target) {
        Pattern pattern = Pattern.compile("((D|E)F+)+G");
        return pattern.matcher(target).matches();
    }

    // 스림프 : 재귀를 통해 체크
    private static boolean isSlimp(String target) {
        // 길이가 2인 경우 AH인지 체크
        if(target.length() == 2 && target.equals("AH")) return true;
        // 길이가 3 이상이고 마지막이 C인 경우
        else if(target.length() > 2
                && target.charAt(target.length()-1) == 'C'
                && target.charAt(0) == 'A'
        ){
            // 중간이 스림프인지 체크
            if(target.charAt(1) == 'B')  return isSlimp(target.substring(2, target.length()-1));
            // 중간이 스럼프인지 체크
            else return isSlump(target.substring(1, target.length()-1));
        }
        return false;
    }
}