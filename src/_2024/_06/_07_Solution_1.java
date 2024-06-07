package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2115
// - 구현 : 각 행과 열에 대해 가능한 그림의 수 계산!
public class _07_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());   // 세로(행) 길이
        int N = Integer.parseInt(st.nextToken());   // 가로(열) 길이
        
        // 비트값
        int[] bit = new int[4];
        bit[0] = 1;
        for(int i = 1; i < 4; i++) bit[i] = bit[i-1] << 1;

        // 갤러리 지도
        char[][] grid = new char[M][N];
        for(int r = 0; r < M; r++){
            String input = in.readLine();
            for(int c = 0; c< N ; c++) grid[r][c] = input.charAt(c);
        }
//        for(char[] g : grid) System.out.println(g);

        // 정답
        int answer = 0;
        // 그림 사용 여부를 위한 비트마스킹!
        // - 0 : 오른쪽에 그림 사용
        // - 1 : 왼쪽에 그림 사용
        // - 2 : 아래쪽에 그림 사용
        // - 3 : 위쪽에 그림 사용
        int[][] map = new int[M][N];
        for(int r = 1; r < M-1; r++){
            for(int c = 1; c < N-1; c++){
                if(grid[r][c] == '.'){
                    if(grid[r][c+1] == '.'){
                        if(grid[r+1][c] == 'X' && grid[r+1][c+1] == 'X' && ((map[r][c] & bit[0]) == 0)){
                            map[r][c] |= bit[0];
                            map[r][c+1] |= bit[0];
                            answer++;
                        }
                        if(grid[r-1][c] == 'X' && grid[r-1][c+1] == 'X' && ((map[r][c] & bit[1]) == 0)){
                            map[r][c] |= bit[1];
                            map[r][c+1] |= bit[1];
                            answer++;
                        }
                    }
                    if(grid[r+1][c] =='.'){
                        if(grid[r][c+1] == 'X' && grid[r+1][c+1] == 'X' && ((map[r][c] & bit[2]) == 0)){
                            map[r][c] |= bit[2];
                            map[r+1][c] |= bit[2];
                            answer++;
                        }
                        if(grid[r][c-1] == 'X' && grid[r+1][c-1] == 'X' && ((map[r][c] & bit[3]) == 0)){
                            map[r][c] |= bit[3];
                            map[r+1][c] |= bit[3];
                            answer++;
                        }
                    }
                }
            }
        }

//        for(int[] r : map){
//            for(int v : r) {
//                StringBuilder b = new StringBuilder(Integer.toBinaryString(v));
//                if(b.length() < 4) while(b.length() < 4) b.insert(0, 0);
//                b.append(" ");
//                System.out.print(b);
//            }
//            System.out.println();
//        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
