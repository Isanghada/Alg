package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1963
// - BFS : BFS를 통해 최소 횟수로 변경하는 경우 탐색!
public class _07_Solution_1 {
    public static final int MAX = 10000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;

        // 에라토스테네스의 체!
        boolean[] prime = sieveOfEratosthenes(MAX);

        // 테스트케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int origin = Integer.parseInt(st.nextToken());  // 원본 숫자
            int target = Integer.parseInt(st.nextToken());  // 타겟 숫자

            // 덱, 방문 배열, 횟수 초기화 
            Deque<Integer> deque = new LinkedList<>();
            boolean[] visited = new boolean[MAX];
            int[] count = new int[MAX];

            // 초기 정보 입력
            deque.add(origin);
            visited[origin] = true;

            // 덱이 비거나 타겟에 도달할 때가지 반복
            while(!deque.isEmpty() && !visited[target]){
                // 현재 숫자
                int cur = deque.pollFirst();
                // 바꿀 자리수
                for(int digit = 0; digit < 4; digit++){
                    // 바꿀 숫자
                    for(int num = 0; num < 10; num++){
                        // 첫 숫자가 0인 경우 패스
                        if(digit == 0 && num == 0) continue;
                        // 숫자 변경
                        int next = changeNumber(cur, digit, num);
                        // 소수이고, 방문하지 않은 경우 정보 갱신!
                        if(!prime[next] && !visited[next]){
                            deque.offerLast(next);
                            count[next] = count[cur]+1;
                            visited[next] = true;
                        }
                    }
                }
            }
            // 최소 변경 횟수 반환!
            sb.append(count[target]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
    private static int changeNumber(int origin, int digit, int change) {
        char[] string = String.valueOf(origin).toCharArray();
        string[digit] = (char) ('0'+change);

        StringBuilder numstr = new StringBuilder();
        for(char n : string) numstr.append(n);

        return Integer.valueOf(numstr.toString());
    }

    private static boolean[] sieveOfEratosthenes(int n) {
        boolean[] result = new boolean[n];
        result[0] = result[1] = true;

        for(int num = 2; num < n; num++){
            int value = num + num;
            while(value < n) {
                result[value] = true;
                value += num;
            }
        }

        return result;
    }
}
