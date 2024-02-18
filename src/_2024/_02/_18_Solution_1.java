package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.acmicpc.net/problem/18428
// - 브루트포스 : 장애물을 설치할 수 있는 모든 경우 탐색!
public class _18_Solution_1 {
    // 노드 클래스 : 좌표를 담을 클래스
    public static class Node{
        int row;    // 행 좌표
        int col;    // 열 좌표
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString(){
            return "[ "+this.row +", "+this.col +" ]";
        }
    }
    public static int N;            // 복도 크기
    public static char[][] BOARD;   // 복도 배열
    // 이동 변수
    public static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static List<Node> TEACHERLIST;   // 선생님의 좌표 리스트
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 복도 크기
        N = Integer.parseInt(in.readLine());

        // 복도 초기화
        BOARD = new char[N][N];
        // 선생님 리스트 초기화
        TEACHERLIST = new ArrayList<>();
        // 복도 입력
        for(int r = 0; r < N; r++){
            String[] input = in.readLine().split(" ");
            for(int c = 0; c < N; c++) {
                BOARD[r][c] = input[c].charAt(0);
                // 선생님인 경우 리스트에 추가
                if(BOARD[r][c] == 'T') TEACHERLIST.add(new Node(r, c));
            }
        }
        // 정답 초기화
        String answer = "NO";
        // 장애물 설치 비트 마스트!
        int[] bitmask = new int[N*N];
        // 끝 좌표 3개에 장애물 설치
        for(int idx = bitmask.length-3; idx < bitmask.length; idx++) bitmask[idx] = 1;
        do{
            // 장애물 좌표
            List<Node> barrierList = new ArrayList<>();
            // 장애물 좌표 추가
            for(int idx = 0; idx < bitmask.length; idx++){
                if(bitmask[idx] == 1) barrierList.add(new Node(idx / N, idx % N));
            }
//            System.out.println(barrierList);
            // 장애물로 변경! : 선생님이나 학생이 있는 경우 불가능
            if(changeBoard(barrierList, 'O')){
//                for(char[] b : BOARD){
//                    System.out.println(Arrays.toString(b));
//                }
                // 감시를 숨을 수 있는 경우 정답 변경 후 종료
                if(isPossible()) {
                    answer = "YES";
                    break;
                }
            }
            // 장애물 원래대로 복구
            changeBoard(barrierList, 'X');
//            System.out.println();
//            for(char[] b : BOARD){
//                System.out.println(Arrays.toString(b));
//            }
//            System.out.println("--------------");
        // 다음 장애물 위치로 변경
        }while(nextPermutation(bitmask));

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static boolean isPossible() {
        for(Node teacher : TEACHERLIST){
//            System.out.println(teacher.row+", "+teacher.col);
            for(int[] d : DELTA){
                Node cur = new Node(teacher.row, teacher.col);
                while(cur.row >= 0 && cur.row < N && cur.col >= 0 && cur.col < N){
//                    System.out.print("[ "+cur.row+", "+cur.col+" ], ");
                    if(BOARD[cur.row][cur.col] == 'S') return false;
                    else if(BOARD[cur.row][cur.col] == 'O') break;
                    cur.row += d[0];
                    cur.col += d[1];
                }
//                System.out.println();
            }
        }

        return true;
    }

    private static boolean changeBoard(List<Node> barrierList, char state) {
        for(Node barrier : barrierList){
            if(BOARD[barrier.row][barrier.col] == 'X' ||
                    BOARD[barrier.row][barrier.col] == 'O' ){
                BOARD[barrier.row][barrier.col] = state;
            }else return false;
        }

        return true;
    }

    private static boolean nextPermutation(int[] bitmask) {
        int i = bitmask.length-1;
        while(i > 0 && bitmask[i-1] >= bitmask[i]) i--;
        if(i == 0) return false;

        int j = bitmask.length-1;
        while(bitmask[i-1] >= bitmask[j]) j--;
        swap(bitmask, i-1, j);

        int k = bitmask.length-1;
        while(i < k) swap(bitmask, i++, k--);

        return true;
    }

    private static void swap(int[] bitmask, int i, int j) {
        int temp = bitmask[i];
        bitmask[i] = bitmask[j];
        bitmask[j] = temp;
    }
}
