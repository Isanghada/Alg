package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/30090
// - 브루트포스 : 모든 경우 탐색!
// - 재귀를 통해 해결하는 것이 더 효율적일 듯?
public class _13_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 바이러스의 수
        int N = Integer.parseInt(in.readLine());

        String[] virusArray = new String[N];    // 바이러스 배열
        int[] virusIndex = new int[N];          // 바이러스 인덱스
        for(int i = 0; i < N; i++) {
            virusArray[i] = in.readLine();
            virusIndex[i] = i;
        }
        // 바이러스 정렬!
        Arrays.sort(virusArray);
        
        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        do{
            String input = virusArray[virusIndex[0]];
            boolean isSuccess = true;
            for(int next = 1; next < N; next++){
                String cur = virusArray[virusIndex[next]];
                boolean isPossible = false;
                for(int iIdx = Math.max(0, input.length() - cur.length());
                    iIdx < input.length();
                    iIdx++){
                    boolean flag = true;
                    int length = 0;
                    for(int point = 0; point < cur.length(); point++){
                        int idx = iIdx + point;
                        if(idx == input.length()) break;
                        if(input.charAt(idx) != cur.charAt(point)){
                            flag = false;
                            break;
                        }
                        length++;
                    }
                    if(flag) {
                        for(int idx = length; idx < cur.length(); idx++) input += cur.charAt(idx);
                        isPossible = true;
                        break;
                    }
                }
                if(!isPossible){
                    isSuccess = false;
                    break;
                }
            }
            if(isSuccess) answer = Math.min(answer, input.length());
        }while(nextPermutation(N, virusIndex));


        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }

    private static boolean nextPermutation(int n, int[] input) {
        int i = n - 1;

        while(i > 0 && input[i-1] >= input[i]) i--;

        if(i == 0)return false;

        int j = n - 1;
        while(input[i-1] >= input[j]) j--;
        swap(input, i-1, j);

        int k = n - 1;
        while(i < k) swap(input, i++, k--);

        return true;
    }

    private static void swap(int[] input, int i, int j) {
        int temp = input[i];

        input[i] = input[j];
        input[j] = temp;
    }
}
