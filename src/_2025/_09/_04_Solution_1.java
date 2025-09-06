package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1570
// - 구현 :
public class _04_Solution_1 {
    // 좌표 클래스
    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static final int[][] DELTA = new int[][]{{1, 0}, {0, 1}};
    static final String[] COMMAND = new String[] {"R", "U"};
    static int N;
    static Point SEJUN, MINE;
    static StringBuilder ANSWER;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());

        // 명령의 길이
        N = Integer.parseInt(st.nextToken());

        // 세준 좌표
        SEJUN = new Point(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        );

        // 지뢰 좌표
        MINE = new Point(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        );

        ANSWER = new StringBuilder();

        int moveX = MINE.x - SEJUN.x;
        int moveY = MINE.y - SEJUN.y;

        if(moveX < 0 || moveY < 0) ANSWER.append(-1);
        else if(moveX + moveY <= N){
            N -= (moveX + moveY);
            while(moveX-- > 0) ANSWER.append('R');
            while(moveY-- > 0) ANSWER.append('U');
            while(N-- > 0) ANSWER.append('R');
        } else if(moveX == 0){
            while(N-- > 0) ANSWER.append('U');
        }else if(moveY == 0){
            while(N-- > 0) ANSWER.append('R');
        } else{
            for(int countR = 1, countU = N - 1; countR < N; countR++, countU--){
                int[] infoR = new int[]{moveX / countR, moveX % countR};
                int[] infoU = new int[]{moveY / countU, moveY % countU};

                if(check(infoR, infoU)){
                    String command = getCommand(countR, countU, infoR, infoU);

                    if(ANSWER.length() == 0) ANSWER.append(command);
                    else if (ANSWER.toString().compareTo(command) > 0) {
                        ANSWER.setLength(0);
                        ANSWER.append(command);
                    }
                }
            }
            if(ANSWER.length() == 0) ANSWER.append(-1);
        }

        // 정답 출력
        System.out.println(ANSWER.toString());
    }

    private static String getCommand(int countR, int countU, int[] infoR, int[] infoU) {
        StringBuilder target = new StringBuilder();

        if(infoR[0] == infoU[0]){
            countR -= infoR[1];
            countU -= infoU[1];

            while(infoR[1]-- > 0) target.append('R');
            while(infoU[1]-- > 0) target.append('U');
            while(countR-- > 0) target.append('R');
            while(countU-- > 0) target.append('U');
        }else if(infoR[0] > infoU[0]){
            while(countR-- > 0) target.append('R');
            while(countU-- > 0) target.append('U');
        }else{
            countR -= infoR[1];
            while(infoR[1]-- > 0) target.append('R');
            while(countU-- > 0) target.append('U');
            while(countR-- > 0) target.append('R');
        }

        return target.toString();
    }

    private static boolean check(int[] infoR, int[] infoU) {
        if(infoR[0] == infoU[0] ||
                (infoR[0] - infoU[0] == 1 && infoR[1] == 0) ||
                (infoU[0] - infoR[0] == 1 && infoU[1] == 0)
        ) return true;
        return false;
    }
}
