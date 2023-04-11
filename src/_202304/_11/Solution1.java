package _202304._11;

import java.util.Arrays;

public class Solution1 {
    public int solution(int n, String[] data) {
        int answer = 0;

        char[] order = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

        do{
            boolean flag = true;
            for(String check : data){
                char value1 = check.charAt(0);
                char value2 = check.charAt(2);
                char exp = check.charAt(3);
                int diff = check.charAt(4) - '0';

                int index1 = -1;
                int index2 = -1;
                for(int i = 0; i < 8; i++){
                    if(order[i] == value1) index1 = i;
                    if(order[i] == value2) index2 = i;
                    if(index1 != -1 && index2 != -1) break;
                }

                int curDiff = Math.abs(index1 - index2) - 1;

                if(exp == '>' && curDiff <= diff ||
                        exp == '=' && curDiff != diff ||
                        exp == '<' && curDiff >= diff
                ) {
                    flag = false;
                    break;
                }
            }
            if(flag) answer += 1;
        }while(nextPermutation(order));

        return answer;
    }

    // nextPermutation으로 다음 순열 구하기!!
    public boolean nextPermutation(char[] input){
        int n = 8;

        int i = n - 1;
        while(i > 0 && input[i - 1] > input[i]){
            i--;
        }

        if(i == 0) return false;

        int j = n - 1;
        while(j > i && input[i-1] > input[j]){
            j--;
        }

        char temp = input[i - 1];
        input[i-1] = input[j];
        input[j] = temp;

        Arrays.sort(input, i, n);

        return true;
    }
}
