package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// https://www.acmicpc.net/problem/2668
// - DFS : 각 인덱스가 사이클을 가지는지 확인
public class _28_Solution_1 {
    // 정답 인덱스를 담을 리스트
    public static ArrayList<Integer> list;
    // 방문 배열
    public static boolean[] visited;
    // 숫자 배열
    public static int[] numArr;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202311/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정수의 수
        int N = Integer.parseInt(in.readLine());

        // 숫자 배열
        numArr = new int[N+1];
        for(int i = 1; i <= N; i++) numArr[i] = Integer.parseInt(in.readLine());

        // 리스트 초기화
        list = new ArrayList<>();
        // 방문 배열 초기화
        visited = new boolean[N+1];
        for(int i = 1; i <= N; i++){
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        // 정답 반환
        sb.append(list.size()).append("\n");
        Collections.sort(list);
        for(int num : list) sb.append(num).append("\n");
        System.out.println(sb);
    }

    private static void dfs(int start, int target) {
        // 방문하지 않은 곳일 경우
        if(!visited[numArr[start]]){
            // 방문 표시
            visited[numArr[start]] = true;
            // 다음 좌표로 방문
            dfs(numArr[start], target);
            // 방문 해제
            visited[numArr[start]] = false;
        }
        // 동일한 경우 리스트에 추가
        if(numArr[start] == target) list.add(target);
    }
}
