package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25565
// - 조건 분기 : 다양한 분기를 직접 체크하도록 구현
public class _11_Solution_1 {
    // 좌표 클래스 : Set을 사용하기 위해 equals, hashCode 재정의
    //               행 -> 열 기준 오름차순 정렬 설정
    public static class Node implements Comparable<Node>{
        int row;
        int col;
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
        @Override
        public int compareTo(Node o){
            int diff = this.row - o.row;
            return (diff == 0 ? this.col - o.col : diff);
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(row).append(" ").append(col);
            return sb.toString();
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || this.getClass() != obj.getClass()) return false;
            Node n = (Node) obj;
            return (this.row == n.row && this.col == n.col);
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기
        int K = Integer.parseInt(st.nextToken());   // 씨앗 개수

        // 전체 씨앗 수
        int total = 0;
        int[][] board = new int[N+2][M+2];
        List<Node> nodeList = new ArrayList<>();
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                if(board[r][c] == 1) {
                    total++;
                    nodeList.add(new Node(r, c));
                }
            }
        }
        // 좌표 정렬
        Collections.sort(nodeList);
//        System.out.println(nodeList);
        // 정답 출력
        // - 씨앗이 2K개인 경우 : 모든 씨앗이 따로 심어진 경우이므로 0 반환
        // - 아닌 경우 : 상태에 따라 공통 부분 추출
        sb.append(total == 2 * K ? 0 : getCommonCount(board, nodeList, total, K, N, M));
        System.out.println(sb);
    }
    // 공통 씨앗 함수
    private static String getCommonCount(int[][] board, List<Node> nodeList, int total, int k, int n, int m) {
        StringBuilder sb = new StringBuilder();
        // 공통된 씨앗의 수는 2K개에 total을 제외한 개수
        sb.append(2*k-total).append("\n");
        // 전체 씨앗이 공통된 경우
        if(total == k) for(Node node : nodeList) sb.append(node).append("\n");
        else{
            // 1개만 공통된 경우 중 가로, 세로로 나눠 심은 경우
            if(total == (2*k - 1)) {
                for(Node node : nodeList){
                    // 가로, 세로에 모두 씨앗이 있으면 해당 위치가 공통된 부분
                    if( (board[node.row-1][node.col] == 1 || board[node.row+1][node.col] == 1) &&
                            (board[node.row][node.col-1] == 1 || board[node.row][node.col+1] == 1)
                    )   {
                        sb.append(node).append("\n");
                        return sb.toString();
                    }
                }
            }
            // 가로 혹은 세로 하나의 방향으로만 심은 경우
            // a의 좌표 계산 : 리스트에 좌표 중 처음부터 k개
            Set<Node> a = new HashSet<>();
            for(int i = 0; i < k; i++) a.add(nodeList.get(i));
            // b의 좌표 확인 : 리스트 좌표 중 마지막부터 k개
            // - a의 좌표와 공통된 경우 반환!
            for(int i = nodeList.size()-k; i < nodeList.size(); i++){
                if(a.contains(nodeList.get(i)))  sb.append(nodeList.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
