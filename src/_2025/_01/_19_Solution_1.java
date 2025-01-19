package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15809
// -
public class _19_Solution_1 {

    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] armies = new int[N+1];
        int[] groups = new int[N+1];
        for(int n = 1; n <= N; n++) {
            armies[n] = Integer.parseInt(in.readLine());
            groups[n] = n;
        }

        while(M-- > 0){
            st = new StringTokenizer(in.readLine());
            int O = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            if(P > Q){
                int temp = P;
                P = Q;
                Q = temp;
            }
            int groupP = find(P, groups);
            int groupQ = find(Q, groups);

            if(O == 1){
                armies[groupP] += armies[groupQ];
                armies[groupQ] = 0;
                union(P, Q, groups);
            }else{
                if(armies[groupP] == armies[groupQ]){
                    armies[groupP] = 0;
                    armies[groupQ] = 0;
                }else if(armies[groupP] > armies[groupQ]){
                    armies[groupP] -= armies[groupQ];
                    armies[groupQ] = 0;
                    union(P, Q, groups);
                }else{
                    armies[groupQ] -= armies[groupP];
                    armies[groupP] = 0;
                    union(Q, P, groups);
                }
            }
        }

        boolean[] used = new boolean[N+1];
        List<Integer> answerList = new ArrayList<>();
        for(int n = 1; n <= N; n++){
            if(used[groups[n]] || armies[groups[n]] == 0) continue;
            used[groups[n]] = true;
            answerList.add(armies[groups[n]]);
        }

        Collections.sort(answerList);

        sb.append(answerList.size()).append("\n");
        for(int answer : answerList) sb.append(answer).append(" ");

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean union(int p, int q, int[] groups) {
        int groupP = find(p, groups);
        int groupQ = find(q, groups);

        if(groupP == groupQ) return false;
        groups[groupQ] = groups[groupP];
        return true;
    }

    private static int find(int target, int[] groups) {
        if(target == groups[target]) return groups[target];
        return groups[target] = find(groups[target], groups);
    }
}
