package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20127
// - 에드혹 : 오름차순, 내림차순이 될 수 있는 경우를 구하고 가능한 경우 중 최소 K 계산
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int asc = kOfAsc(A, N);
        int desc = kOfDesc(A, N);

        if(asc == -1 && desc == -1) sb.append(-1);
        else if(asc != -1 && desc != -1) sb.append(Math.min(asc, desc));
        else if(asc != -1) sb.append(asc);
        else sb.append(desc);


        // 정답 출력
        System.out.println(sb.toString());
    }

    private static int kOfDesc(int[] a, int n) {
        List<Integer> descCountList = new ArrayList<>();

        int idx = 1;
        int count = 1;
        while(idx < n){
            if(a[idx-1] < a[idx]){
                descCountList.add(count);
                count = 1;
            }else count++;
            idx++;
        }
        descCountList.add(count);

        if(descCountList.size() == 1) return 0;
        else if(descCountList.size() == 2) return a[0] <= a[n-1] ? descCountList.get(0) : -1;
        return -1;
    }

    private static int kOfAsc(int[] a, int n) {
        List<Integer> ascCountList = new ArrayList<>();

        int idx = 1;
        int count = 1;
        while(idx < n){
            if(a[idx-1] > a[idx]){
                ascCountList.add(count);
                count = 1;
            }else count++;
            idx++;
        }
        ascCountList.add(count);

        if(ascCountList.size() == 1) return 0;
        else if( ascCountList.size() == 2) return a[0] >= a[n-1] ? ascCountList.get(0) : -1;
        return -1;
    }
}

