package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/6581
// - 문자열 : 단어별로 구분하여 처리! 공백, 탭, 개행 한 문자씩 처리해야하므로 StringToknizer 활용
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 모든 문자열 리스트 입력
        List<String> inputList = in.lines().collect(Collectors.toList());
        // 현재 줄에 추가된 문자열 인덱스
        int idx = 0;
        for(String input : inputList){
            // StringTokenizer의 delimiter는 한 문자씩 처리 되므로 각 부분으로 구분!
            StringTokenizer st = new StringTokenizer(input, " \t\n");
            // 남은 단어가 있다면 처리
            while(st.hasMoreTokens()){
                // 현재 단어
                String cur = st.nextToken();
                // <br>인 경우 : 개행 처리, 인덱스 갱신
                if(cur.equals("<br>")){
                    sb.append("\n");
                    idx = 0;
                    continue;
                // <hr>인 경우 : 개행+줄 처리, 인덱스 갱신
                }else if(cur.equals("<hr>")){
                    if(idx != 0) sb.append("\n");
                    for(int i = 0; i < 80; i++)sb.append("-");
                    sb.append("\n");
                    idx = 0;
                    continue;
                // 현재 단어를 추가할 때 80을 넘길 경우 : 개행 처리, 인덱스 갱신
                }else if(idx + cur.length()+(idx == 0 ? 0 : 1) > 80){
                    sb.append("\n");
                    idx = 0;
                // 현재 단어를 추가할 수 있는 경우 : 공백 추가
                }else if(idx != 0) sb.append(" ");
                // 현재 단어 추가
                sb.append(cur);
                // 인덱스 갱신
                idx += cur.length() + (idx==0 ? 0 : 1);
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}
