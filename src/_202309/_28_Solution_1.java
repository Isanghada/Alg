package _202309;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42894
// - 정렬을 활용한 전체 탐색
// - 블록 제거를 위해 블록별로 리스트에 추가 후 정렬.
// - 차례로 블록을 제거하기 위한 조건이 중요1
public class _28_Solution_1 {
    // 블록 범위를 담을 클래스
    // - 정렬을 사용하기 위해 Comparable 사용
    // - 직사각형 범위를 값으로 가짐
    public class Block implements Comparable<Block>{
        int num;    // 블록의 숫자
        int minRow; // 최소 행 값
        int maxRow; // 최대 행 값
        int minCol; // 최소 열 값
        int maxCol; // 최대 열 값

        public Block(int num, int minRow, int maxRow, int minCol, int maxCol){
            this.num = num;
            this.minRow = minRow;
            this.maxRow = maxRow;
            this.minCol = minCol;
            this.maxCol = maxCol;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ num = ")
                    .append(this.num)
                    .append(", ")
                    .append(this.minRow)
                    .append(", ")
                    .append(this.maxRow)
                    .append(", ")
                    .append(this.minCol)
                    .append(", ")
                    .append(this.maxCol)
                    .append("]");

            return sb.toString();
        }

        // 정렬 : 위에 다른 블록이 있다면 제거 불가능 => 위에 있는 블록부터 확인하기 위해 사용
        // 1. minRow로 오름차순 정렬
        // 2. maxRow로 오름차순 정렬
        @Override
        public int compareTo(Block o) {
            int diff = this.minRow - o.minRow;
            return diff == 0 ? (this.maxRow - o.maxRow) : diff;
        }
    }

    public static int[][] board;    // 블록 상태 배열
    public static int N;            // 배열의 크기
    public int solution(int[][] board) {
        // 정답 초기화
        int answer = 0;

        this.N = board.length;
        this.board = board;

        // 블록을 담을 리스트
        List<Block> blockList = new ArrayList<>();
        // 추가한 블록 확인용 배열
        boolean[] isUsed = new boolean[201];
        // 모든 좌표를 탐색하여 블록을 리스트에 추가
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                // 비었거나(0), 이미 추가한 경우 패스
                if(board[r][c] == 0 || isUsed[board[r][c]]) continue;

                // 블록 추가 체크
                isUsed[board[r][c]] = true;
                // 해당 블록의 범위를 확인하여 리스트에 추가
                blockList.add(getBlockState(board[r][c], r, c));
            }
        }

        // 블록 리스트 정렬 : 위에 존재하는 블록부터 확인하기 위함
        Collections.sort(blockList);
        System.out.println(blockList);

        // 모든 블록 차례로 제거 가능한지 확인
        for(Block cur : blockList){
            // 현재 블록 제거 가능 여부 확인 : 가능한 경우!
            if(check(cur)){
                // 정답 증가
                answer++;
                // 해당 범위 0으로 변환
                for(int r = cur.minRow; r <= cur.maxRow; r++){
                    for(int c = cur.minCol; c <= cur.maxCol; c++)
                        this.board[r][c] = 0;
                }
            }
        }

        return answer;
    }

    private boolean check(Block cur) {
        // 1. 직사각형 내에 다른 블록이 있는지 확인.
        // 2. 0이 있는 경우 위에 다른 블록이 막고 있는지 확인.
        for(int r = cur.minRow; r <= cur.maxRow; r++){
            for(int c = cur.minCol; c <= cur.maxCol; c++) {
                // 현재 블록의 num이 아닌 경우! 체크
                // - 0인 경우 다른 블록이 막고 있는지 확인
                // - 0이 아닌 경우 불가능
                if(this.board[r][c] != cur.num){
                    // 0인 경우는 다른 블록이 막고 있는지 확인
                    if(this.board[r][c] == 0){
                        int tempRow = r-1;
                        while(tempRow >= 0){
                            // 위에 다른 블록이 있다면 블록 제거 불가능
                            if(this.board[tempRow--][c] != 0) return false;
                        }
                        continue;
                    }
                    return false;
                }
            }
        }
        
        // 블록 제거가 가능한 경우
        return true;
    }

    // 블록 확인을 위한 이동 변수 : 상, 하, 좌, 우
    public static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 블록 범위 반환 함수 : BFS로 범위 탐색
    // - num : 해당 블록 숫자
    // - r : 시작 행
    // - c : 시작 열
    private Block getBlockState(int num, int r, int c) {
        // 블록 초기화
        Block block = new Block(num, r, r, c, c);

        // 덱, 방문 배열 초기화
        Deque<int[]> deque = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        // 초기값 설정
        deque.offerLast(new int[]{r, c});
        visited[r][c] = true;

        // 덱이 빌 때까지 반복
        while(!deque.isEmpty()){
            // 덱에서 좌표 반환
            int[] cur = deque.pollFirst();
            // 좌표값으로 상태 변경
            // - 행, 열의 최소, 최대에 따라 변경
            block.minRow = Math.min(block.minRow, cur[0]);
            block.maxRow = Math.max(block.maxRow, cur[0]);
            block.minCol = Math.min(block.minCol, cur[1]);
            block.maxCol = Math.max(block.maxCol, cur[1]);

            // 4방향으로 이동하여 추가
            for(int[] d : DELTA){
                // 새로운 좌표
                int[] next = new int[] {cur[0] + d[0], cur[1] + d[1]};

                // 아래의 경우 패스
                // - 범위를 벗어나는 경우
                // - 새 좌표의 값이 num과 다른 경우
                // - 이미 방문한 경우
                if(next[0] < 0 || next[0] >= N || next[1] < 0 || next[1] >= N ||
                        this.board[next[0]][next[1]] != num ||
                        visited[next[0]][next[1]]
                ) continue;

                // 덱에 새로운 좌표 추가
                deque.offerLast(next);
                visited[next[0]][next[1]] = true;
            }
        }

        return block;
    }
}
