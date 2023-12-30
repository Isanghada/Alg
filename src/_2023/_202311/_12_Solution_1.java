package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2877
// - 참고 : https://syh39.github.io/algorithm/algorithm_BOJ_2877
// - 4를 0, 7을 1로 생각하고 패턴을 그리면 아래와 같다.
//   - 1= 4 : 0         => (1+1)의 이진수 : 10      = (1)0  = 4
//   - 2= 7 : 1         => (2+1)의 이진수 : 11      = (1)1  = 7
//   - 3= 44 : 00       => (3+1)의 이진수 : 100     = (1)00 = 44
//   - 4= 47 : 01       => (4+1)의 이진수 : 101     = (1)01 = 47
//   - ...
//   - 여기서 가장 큰 자리수를 제외하고 남은 부분에서 0을 4, 1을 7로 치환하면 정답이 나오게 된다.

public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202311/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 구하고자 하는 작은 수의 순서 입력 + 1
        // - 패턴을 사용하기 위해 +1
        int K = Integer.parseInt(in.readLine()) + 1;
        // - 입력받은 값을 이진수로 변환
        String binary = Integer.toBinaryString(K);

        // 가장 큰 자리수를 제외하고 나머지 자리수 치환
        // - 0은 4, 1은 7
        for(int idx = 1; idx < binary.length(); idx++){
            sb.append(binary.charAt(idx) == '0' ? 4 : 7);
        }
        
        // 결과 반환
        System.out.println(sb);
    }
}
