package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/6137
// - 투 포인터 : 양 끝에서부터 사전순으로 체크하여 더 빠른 경우 선택
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 길이
        int N = Integer.parseInt(in.readLine());
        // 문자 정보 입력
        char[] words = new char[N];
        for(int i = 0; i < N; i++) words[i] = in.readLine().charAt(0);

        // 문자열 T 리스트
        List<Character> wordList = new ArrayList<>();
        
        // 투 포인터 초기화
        int left = 0;
        int right = N-1;
        while(left <= right){
            //System.out.println(left+", "+right+", "+words[left]+", "+words[right]);
            // 왼쪽이 작은 경우
            if(words[left] < words[right]) wordList.add(words[left++]);
            // 오른쪽이 작은 경우
            else if(words[left] > words[right]) wordList.add(words[right--]);
            // 같은 경우
            else{
                // 문자열이 다른 경우까지 체크하여 true인 경우 왼쪽
                if(check(words, left, right)) wordList.add(words[left++]);
                // false인 경우 오른쪽
                else wordList.add(words[right--]);
            }
        }

        // 문자열 출력!
        int idx = 0;
        do{
            sb.append(wordList.get(idx++));
            if(idx % 80 == 0) sb.append("\n");
        }while(idx < N);

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean check(char[] words, int left, int right) {
        while(left < right){
            if(words[left] > words[right]) return false;
            else if(words[left] < words[right]) return true;
            left++;
            right--;
        }
        return true;
    }
}
