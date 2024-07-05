package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/5904
// -
public class _05_Solution_1 {
    public static List<Integer> SIZE_LIST;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        SIZE_LIST = new ArrayList<>();
        SIZE_LIST.add(3);
        for(int idx = 1; SIZE_LIST.get(idx-1) < N; idx++){
            SIZE_LIST.add(SIZE_LIST.get(idx-1) * 2 + idx+3);
        }

        // 정답 반환
        sb.append(moo(N, SIZE_LIST.size()-1));
        System.out.println(sb);
    }

    private static char moo(int n, int idx) {
        char result;

        if(n == 1) result = 'm';
        else if(n <= 3) result ='o';
        else{
            if(n <= SIZE_LIST.get(idx-1)) result = moo(n, idx-1);
            else if(n > (SIZE_LIST.get(idx) - SIZE_LIST.get(idx-1))) {
                result = moo(n - SIZE_LIST.get(idx) + SIZE_LIST.get(idx-1), idx-1);
            }else{
                n -= SIZE_LIST.get(idx-1);
                if(n == 1) result = 'm';
                else result = 'o';
            }
        }

        return result;
    }
}
