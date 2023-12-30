package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://www.acmicpc.net/problem/1038
// - 완전 탐색(브루트포스) : 모든 감소하는 수를 계산하고 알맞은 값 반환!
//   - 가장 작은 감소하는 수 0, 가장 큰 감소하는 수 9876543210
//   - 감소하는 수는 1023개이므로 모두 구하는 방식 사용!
public class _23_Solution_1 {
    public static List<Long> decreasingNumbers;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202310/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N번째 감소하는 수
        int N = Integer.parseInt(in.readLine());

        // 모든 감소하는 수 계산 : 재귀 활용
        decreasingNumbers = new ArrayList<>();
        // - 0 ~ 9를 가장 앞 자리로 가지는 감소하는 수 계산
        for(int start = 0; start < 10; start++){
            getDecreasingNumber(start);
        }
        // 감소하는 수 오름차순 정렬
        Collections.sort(decreasingNumbers);
        // 정답 반환
        // System.out.println(decreasingNumbers);
        // - 감소하는 수의 개수가 N초과일 경우 알맞은 감소하는 수 반환
        // - N이하일 경우 알맞은 감소하는 수가 없으므로 -1 반환
        sb.append(decreasingNumbers.size() > N ? Long.toString(decreasingNumbers.get(N)) : -1);
        System.out.println(sb);
    }

    // 감소하는 수 계산 함수
    // - num : 현재 감소하는 수
    private static void getDecreasingNumber(long num) {
        // 리스트에 감소하는 수 추가
        decreasingNumbers.add(num);
        // 일의 자리 숫자를 limit으로 설정
        int limit = (int) (num % 10);
        // - 일의 자리에 0 ~ limit(미포함)의 숫자를 차례로 추가!
        for(int n = 0; n < limit; n++){
            getDecreasingNumber(num * 10 + n);
        }
    }
}
