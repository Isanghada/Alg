package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/1148
// -
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<int[]> countOfAlp = getInput(in);
        List<int[]> countOfPuzzle = getInput(in);

        for(int[] puzzle : countOfPuzzle){
            StringBuilder minAlp = new StringBuilder();
            StringBuilder maxAlp = new StringBuilder();
            int min = Integer.MAX_VALUE;
            int max = 0;

            for(int i = 0; i < ALP_SIZE; i++){
                if(puzzle[i] == 0) continue;
                char alp = (char) ('A'+i);
                int count = getCount(puzzle, countOfAlp, alp);

                if(min == count) minAlp.append(alp);
                else if(min > count){
                    min = count;
                    minAlp.setLength(0);
                    minAlp.append(alp);
                }

                if(max == count) maxAlp.append(alp);
                else if(max < count){
                    max = count;
                    maxAlp.setLength(0);
                    maxAlp.append(alp);
                }
            }
            sb.append(minAlp.toString()).append(" ").
                    append(min).append(" ").
                    append(maxAlp.toString()).append(" ").
                    append(max).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static int getCount(int[] puzzle, List<int[]> countOfAlp, char target) {
        int count = 0;
        for(int[] alp : countOfAlp){
            if(alp[target-'A'] == 0) continue;
            boolean flag = true;
            for(int i = 0; i < ALP_SIZE; i++){
                if(alp[i] > puzzle[i]){
                    flag = false;
                    break;
                }
            }
            if(flag) count++;
//            System.out.println(Arrays.toString(puzzle));
//            System.out.println(Arrays.toString(alp));
//            System.out.println(count);
//            System.out.println("--------");
        }
        return count;
    }

    private static final int ALP_SIZE = 26;
    private static List<int[]> getInput(BufferedReader in) throws Exception {
        List<int[]> result = new ArrayList<>();
        while(true){
            String word = in.readLine();
            if(word.equals("-") || word.equals("#")) break;

            int[] counts = new int[ALP_SIZE];
            for(int i = 0; i < word.length(); i++) counts[word.charAt(i) - 'A']++;
            result.add(counts);
        }
        return result;
    }
}
