package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18116
// - 유니온-파인드 : 유니온 파인드를 활용해 각 그룹 체크!
public class _17_Solution_1 {
    // 최대값
    public static final int MAX = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        // 명령 횟수
        int N= Integer.parseInt(in.readLine());

        // 그룹 체크
        int[] groups = new int[MAX+1];
        // 그룹의 부품 개수
        int[] counts = new int[MAX+1];
        for(int i = 0; i <= MAX; i++) {
            groups[i] = i;
            counts[i] = 1;
        }

        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            // 명령
            String command = st.nextToken();
            if(command.equals("I")){
                // 같은 그룹의 부품!
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(b < a){
                    int temp = a;
                    a = b;
                    b = temp;
                }

                // union : 그룹 합치기!
                union(a, b, groups, counts);
            }else{
                // 그룹의 부품 개수 반환
                int c = Integer.parseInt(st.nextToken());
                sb.append(counts[find(c, groups)]).append("\n");
            }
        }
        
        // 정답 출력
        System.out.println(sb.toString());
    }

    private static void union(int a, int b, int[] groups, int[] counts) {
        int groupA = find(a, groups);
        int groupB = find(b, groups);

        if(groupA != groupB) {
            counts[groupA] += counts[groupB];
            groups[groupB] = groupA;
        }
    }

    private static int find(int target, int[] groups) {
        if(groups[target] == target) return target;
        else return groups[target] = find(groups[target], groups);
    }
}
