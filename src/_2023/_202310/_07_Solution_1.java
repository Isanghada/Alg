package _2023._202310;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/1836
// - 모든 타일에 대해 반복해서 전수 조사를 통해 1개씩 제거
//   - 사전순으로 반환하기 위해 알파벳순으로 타일 정렬 후 진행
//   - 1개의 타일 쌍을 제거하면 다시 처음부터 확인 => 새로운 타일을 제거할 수도 있으므로
public class _07_Solution_1 {
    // 타일 클래스
    public class Tile{
        int r1,c1,r2,c2;    // 타일의 좌표 (r1, c1), (r2, c2)
        char alp;   // 타일의 알파벳
        boolean isRemove;   // 제거 여부

        public Tile(char alp, int r1, int c1, int r2, int c2){
            this.alp = alp;
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.isRemove = false;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb
                .append("[alp=")
                .append(alp)
                .append(", (r1, c1)=(")
                .append(r1).append(", ").append(c1)
                .append("), (r2, c2)=(")
                .append(r2).append(", ").append(c2)
                .append(")]\n");
            return sb.toString();
        }
    }
    // 게임판
    public static char[][] board;
    // 타일의 종류와 좌표를 담을 map
    public static Map<Character, Tile> tileMap;
    // 정답
    public static String answer;
    public String solution(int m, int n, String[] board) {
        // 정답 초기화
        answer = "IMPOSSIBLE";
        // 타일을 담을 map 초기화
        tileMap = new HashMap<>();
        // 게임판 입력 => char[][]로 변환
        this.board = new char[board.length][board[0].length()];
        for(int idx = 0; idx < board.length; idx++) this.board[idx] = board[idx].toCharArray();

        // 게임판을 확인하며 각 타일 정보 입력
        for(int r = 0; r < this.board.length; r++){
            for(int c = 0; c < this.board[0].length; c++){
                char word = this.board[r][c];
                // 막히거나 빈 공간이 아닌 경우 추가
                if(word != '*' && word != '.'){
                    // 알파벳을 키로 가지고 있을 경우 : map에 2번째 좌표 설정
                    if(tileMap.containsKey(word)){
                        tileMap.get(word).r2 = r;
                        tileMap.get(word).c2 = c;
                    // 키로 가지고 있지 않은 경우 : 새로운 키, 값 생성
                    }else{
                        tileMap.put(word, new Tile(word, r, c, -1, -1));
                    }
                }
            }
        }
        // map 정보를 list로 변환
        List<Tile> tileList = tileMap.values().stream().collect(Collectors.toList());
        // 알파벳 기준 오름차순 정렬
        Collections.sort(tileList, (o1, o2) -> {
            return o1.alp - o2.alp;
        });
//        System.out.println(tileList);

        // 제거된 타일의 수
        int removeCount = 0;
        // 정답을 임시로 담을 StringBuilder
        StringBuilder tempAnswer = new StringBuilder();
        // 모든 타일이 제거되지 않았다면 반복
        while(removeCount < tileList.size()){
            // 실패 플래그 초기화
            boolean isFail = true;
            // 타일 리스트를 전부 확인
            for(int idx = 0; idx <tileList.size(); idx++){
                // 현재 타일 정보
                Tile cur = tileList.get(idx);
                // 이미 제거된 경우 패스.
                if(cur.isRemove) continue;
                // 제거 가능한 경우
                if(removeCheck(cur)){
                    // 게임판에 정보 수정 : 해당 좌표를 빈 공간으로 변경
                    this.board[cur.r1][cur.c1] = '.';
                    this.board[cur.r2][cur.c2] = '.';
                    // 실패 플래그 변경
                    isFail = false;
                    // 제거된 타일 수 증가
                    removeCount++;
                    // 정답에 알파벳 추가
                    tempAnswer.append(cur.alp);
                    // 타일의 제거 여부 변경
                    cur.isRemove = true;
                    break;
                }
            }
//            for(char[] c : this.board) System.out.println(c);
//            System.out.println("-------------");
            // 제거할 수 있는 타일이 없는 경우 IMPOSSIBLE 반환
            if(isFail) {
//                for(char[] c : this.board) System.out.println(c);
//                System.out.println("-------------");
//                System.out.println(tempAnswer);
                return answer;
            }
        }

        // 임시 정답으로 정답 변경 후 반환
        answer = tempAnswer.toString();
        return answer;
    }

    // 제거 여부 확인 함수
    // - cur : 현재 타일 정보
    private boolean removeCheck(Tile cur) {
        // 행이 동일한 경우 : 열만 확인
        if(cur.r1 == cur.r2){
            int r = cur.r1;
            int cLast = Math.max(cur.c1, cur.c2);
            for(int c = Math.min(cur.c1, cur.c2) + 1; c < cLast; c++){
                if(board[r][c] != '.') return false;
            }
        // 열이 동일한 경우 : 행만 확인
        }else if(cur.c1 == cur.c2){
            int c = cur.c1;
            for(int r = cur.r1+1; r < cur.r2; r++){
                if(board[r][c] != '.') return false;
            }
        // 선분 2개를 쓰는 경우 확인
        }else{
            // 제거 가능 여부
            boolean isPossible = true;
            // 열 범위 계산 : 작은값을 stat로 큰 값을 end로
            int startC = 0;
            int endC = 0;
            if(cur.c1 < cur.c2){
                startC = cur.c1+1;
                endC = cur.c2;
            }else{
                startC = cur.c2+1;
                endC = cur.c1;
            }
            // 행 -> 열 이동
            for(int r = cur.r1+1; r <= cur.r2 && isPossible; r++){
                if(board[r][cur.c1] != '.') isPossible = false;
            }

            for(int c = startC; c < endC && isPossible; c++){
                if(board[cur.r2][c] != '.') isPossible = false;
            }
            // 제거 가능하다면 true 반환
            if(isPossible) return true;
            
            // 열 -> 행 이동
            isPossible = true;
            for(int c = startC; c < endC && isPossible; c++){
                if(board[cur.r1][c] != '.') isPossible = false;
            }
            for(int r = cur.r1; r < cur.r2 && isPossible; r++){
                if(board[r][cur.c2] != '.') isPossible = false;
            }
            // 제거 가능하다면 true 반환
            if(isPossible) return true;

            // 제거가 불가능한 경우 false 반환
            return false;
        }

        return true;
    }
}
