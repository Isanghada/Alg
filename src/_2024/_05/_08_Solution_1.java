package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12908
// - 플로이드 와샬 : 모든 정점에 갈 수 있는 최소 시간 계산!
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[][] nodeArr = new int[8][2];        // 노드 정보
        long[][] distanceArr = new long[8][8];    // 거리 정보

        // 시작 정보
        nodeArr[0] = getPoint(new StringTokenizer(in.readLine()), 2);
        // 도착 정보
        nodeArr[7] = getPoint(new StringTokenizer(in.readLine()), 2);

        // 거리 초기화
        for(int i = 0; i < 8; i++) Arrays.fill(distanceArr[i], Integer.MAX_VALUE);
        // 각 거리 계산!
        distanceArr[0][7] = getTime(nodeArr[0], nodeArr[7]);
        distanceArr[7][0] = distanceArr[0][7];
        for(int i = 1; i < 7; i += 2){
            StringTokenizer st = new StringTokenizer(in.readLine());
            nodeArr[i] = getPoint(st, 2);   // 포탈1
            nodeArr[i+1] = getPoint(st, 2); // 포탈2

            distanceArr[i][i+1] = Math.min(getTime(nodeArr[i], nodeArr[i+1]), 10);
            distanceArr[i+1][i] = distanceArr[i][i+1];
        }
    
        // 모든 정점간의 시간 계산
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++)
                distanceArr[r][c] = Math.min(distanceArr[r][c], getTime(nodeArr[r], nodeArr[c]));
        }

        // 각 정점을 지나쳐가는 최소 시간 계산
        for(int k = 0; k < 8; k++){
            for(int r = 0; r < 8; r++){
                for(int c = 0; c < 8; c++){
                    distanceArr[r][c] = Math.min(distanceArr[r][c], distanceArr[r][k]+distanceArr[k][c]);
                }
            }
        }

//        for(int d[] : distanceArr){
//            System.out.println(Arrays.toString(d));
//        }

        // 정답 출력
        sb.append(distanceArr[0][7]);
        System.out.println(sb);
    }

    private static int getTime(int[] start, int[] end) {
        return Math.abs(end[0] - start[0]) + Math.abs(end[1] - start[1]);
    }

    private static int[] getPoint(StringTokenizer st, int index) {
        int[] result = new int[index];
        for(int i = 0; i < index; i++){
            result[i] = Integer.parseInt(st.nextToken());
        }
        return result;
    }
}
