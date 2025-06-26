package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/33983
// - 그리디 : 규칙에 부합하게끔 체크하려고 했으나..(실패)
public class _26_Solution_1 {
    static final String PURE = "pure", MIX = "mix";
    static final int H = 'H'-'A', O = 'O' - 'A', ALP = 'Z' - 'A';
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        char[] material = in.readLine().toCharArray();

        int[] count = new int[ALP];
        for(char m : material) count[m-'A']++;

        // 물질 구조가 순수한 물이 될 수 있는지 체크
        if(count[H] == count[O] * 2){
            if(material[0] == 'H' && material[N-1] == 'H'){
                boolean flag = true;
                for(int i = 1; i < N; i++) {
                    if (material[i] == 'O' && material[i - 1] == 'O') {
                        flag = false;
                        break;
                    }
                }
                if(flag) sb.append(PURE);
                else sb.append(MIX);
            }else sb.append(MIX);
        }else sb.append(MIX);

        // 정답 반환
        System.out.println(sb);
    }
}
