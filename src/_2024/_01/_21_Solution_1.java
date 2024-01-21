package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25585
// - 백트래킹 : 모든 경우 탐색!
public class _21_Solution_1 {
    public static int N, ANSWER;
    public static String isClear;
    public static int[][] MAP;
    public static boolean[] enemyVisited;
    public static List<Point> enemyList;

    public static class Point{
        int r;
        int c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(in.readLine());
        ANSWER = Integer.MAX_VALUE;
        isClear = "Undertaker";

        MAP = new int[N][N];
        Point start = null;
        enemyList = new ArrayList<>();
        for(int r = 0; r < N; r++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int c = 0; c < N; c++) {
                MAP[r][c] = Integer.parseInt(st.nextToken());
                if(MAP[r][c] == 2) start = new Point(r, c);
                else if(MAP[r][c] == 1) enemyList.add(new Point(r, c));
            }
        }

        if(start.r % 2 == start.c % 2){
            for(Point enemy : enemyList){
                if(enemy.r % 2 != enemy.c % 2){
                    isClear = "Shorei";
                    break;
                }
            }
        }else{
            for(Point enemy : enemyList){
                if(enemy.r % 2 == enemy.c % 2){
                    isClear = "Shorei";
                    break;
                }
            }
        }

        sb.append(isClear);
        if(isClear.equals("Undertaker")){
            enemyVisited = new boolean[enemyList.size()];

            backTracking(start, 0, 0);

            sb.append("\n").append(ANSWER);
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static void backTracking(Point cur, int total, int step) {
        if(step == enemyList.size()){
            ANSWER = Math.min(ANSWER, total);
        }else{
            for(int idx = 0; idx < enemyList.size(); idx++){
                if(enemyVisited[idx]) continue;
                enemyVisited[idx] = true;

                Point enemy = enemyList.get(idx);
                int time = Math.max(Math.abs(cur.r - enemy.r), Math.abs(cur.c - enemy.c));
                backTracking(enemy, total + time, step+1);
                enemyVisited[idx] = false;
            }
        }
    }
}