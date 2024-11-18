package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9252
// - DP : DP를 활용해 LCS(최장 공통 부분 수열) 탐색
public class _19_Solution_1 {
    // 노드 클래스
    public static class Node{
        int count;      // 길이
        String common;  // 공통 부분 수열
        public Node(int count){
            this(count, "");
        }
        public Node(int count, String common){
            this.count = count;
            this.common = common;
        }
        public Node(Node node){
            this(node.count, node.common);
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문자열 입력
        String A = "0" + in.readLine();
        String B = "0" + in.readLine();

        int lenA = A.length()-1;
        int lenB = B.length()-1;

        // dp 초기화
        Node[][] dp = new Node[lenA+1][lenB+1];
        for(int a = 0; a <= lenA; a++){
            for(int b = 0; b <= lenB; b++) dp[a][b] = new Node(0);
        }

//        System.out.println(A +", "+lenA);
//        System.out.println(B +", "+lenB);

        // 모든 경우 탐색
        for(int a = 1; a <= lenA; a++){
            for(int b = 1; b <= lenB; b++){
                // 이전 결과 중 길이가 긴 경우 선택
                if(dp[a-1][b].count > dp[a][b-1].count) dp[a][b] = new Node(dp[a-1][b]);
                else dp[a][b] = new Node(dp[a][b-1]);

                // 현재 문자가 동일하고 길이가 더 길 경우 갱신!
                if(A.charAt(a) == B.charAt(b) && dp[a-1][b-1].count >= dp[a][b].count){
                    dp[a][b] = new Node(dp[a-1][b-1].count + 1,
                                       dp[a-1][b-1].common + A.charAt(a));
                }
            }
        }

//        for(Node[] d : dp){
//            for(Node node : d) System.out.print(node.count+"("+node.common+") ");
//            System.out.println();
//        }

        sb.append(dp[lenA][lenB].count);
        if(dp[lenA][lenB].count > 0) sb.append("\n").append(dp[lenA][lenB].common);

        // 정답 출력
        System.out.println(sb.toString());
    }
}
