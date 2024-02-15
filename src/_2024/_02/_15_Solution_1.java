package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

// https://www.acmicpc.net/problem/2251
// - DFS : 모든 경우 탐색!
//         TreeSet을 통해 정렬된 값을 차례로 반환
public class _15_Solution_1 {
    public static int[] bottleArr;
    public static boolean[][] check;
    public static Set<Integer> ANSWER;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 병 크기 입력
        bottleArr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();
        // 방문 체크용 배열
        check = new boolean[201][201];

        // 정답 초기화
        ANSWER = new TreeSet<>();
        // 초기 상태 설정
        dfs(0, 0, bottleArr[2]);
        // 정답 반환
        for(int water : ANSWER) sb.append(water).append(" ");

        // 정답 출력
        System.out.println(sb.toString().trim());
    }

    private static void dfs(int a, int b, int c) {
        // 이미 체크한 상태이면 패스
        if(check[a][b]) return;
        // 상태 체크!
        check[a][b] = true;

        // a가 0인 경우 정답 TreeSet에 추가!
        if(a == 0) ANSWER.add(c);

        int AB = a+b;
        //  a에서 b로 이동!
        if(AB > bottleArr[0]) dfs(bottleArr[0], AB - bottleArr[0], c);
        else dfs(AB, 0, c);
        // b에서 a로 이동
        if(AB > bottleArr[1]) dfs(AB - bottleArr[1], bottleArr[1], c);
        else dfs(0, AB, c);

        int AC = a+c;
        // c에서 a로 이동
        if(AC > bottleArr[0]) dfs(bottleArr[0], b, AC - bottleArr[0]);
        else dfs(AC, b, 0);
        // a에서 c로 이동
        dfs(0, b, AC);

        int BC = b+c;
        // c에서 b로 이동
        if(BC > bottleArr[1]) dfs(a, bottleArr[1], BC - bottleArr[1]);
        else dfs(a, BC, 0);
        // b에서 c로 이동
        dfs(a, 0, BC);
    }
}
