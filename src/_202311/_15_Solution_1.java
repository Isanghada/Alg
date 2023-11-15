package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9241
// - 감염 전, 감염 후를 비교해 동일한 부분을 찾고 나머지 길이로 계산
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 감염 전 DNA
        char[] before = in.readLine().toCharArray();
        // 감염 후 DNA
        char[] after = in.readLine().toCharArray();

        // 앞의 동일한 부분
        int start = 0;
        // 작은 길이 계산
        int limit = Math.min(before.length, after.length);
        // 앞 부분에서부터 동일한 길이 계산
        while(start < limit && before[start] == after[start]) start++;

        // 뒤의 동일한 부분
        int end = 0;
        // 뒷 부분에서부터 동일한 길이 계산
        while(end < limit && before[before.length - end - 1] == after[after.length - end - 1]) end++;

        // 동일한 부분이 길이가 작은 DNA의 길이 이상일 경우
        if(start + end >= limit){
            // 감염 전의 길이가 길 경우 : 0(삽입되지 않고 삭제만 된 경우)
            if(before.length > after.length) sb.append(0);
            // 감염 후의 길이가 길 경우 : (감염 후 길이) - (감염 전 길이) (길이 차이만큼 삽입)
            else sb.append(after.length-before.length);
        // 동일한 부분이 길이가 작은 DNA의 길이 미만일 경우
        // - (감염 후의 길이) - (동일한 부분) : 동일한 부분을 제외하고 삽입
        }else sb.append(after.length - end - start);

        // 정답 출력
        System.out.println(sb);
    }
}
