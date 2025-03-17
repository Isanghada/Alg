package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1101
// - 그리디 : 각 박스가 조커인 경우에서 최소 이동 횟수 탐색
//            1. 박스에 2개 이상의 색상이 있다면 조커 박스로 이동
//            2. 박스에 색상이 1개만 존재하면 기본적으로는 이동하지 않음.
//               - 단, 다른 박스에 해당 색상만 존재하면 이동!
//                 (같은 색상은 조커 박스 혹은 1개의 박스에만 존재해야하기 때문)
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] boxArr = new int[N+1][M+1];
        int[] colorCount = new int[N+1];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            for(int m = 1; m <= M; m++){
                boxArr[n][m] = Integer.parseInt(st.nextToken());
                if(boxArr[n][m] > 0) colorCount[n]++;
            }
        }

        int answer = N;
        for(int joker = 1; joker <= N; joker++){
            int moveCount = 0;
            boolean[] visited = new boolean[M+1];
            for(int box = 1; box <= N; box++){
                if(joker == box || colorCount[box] == 0) continue;

                // 한 박스에 색상이 2개 이상인 경우 조커 박스로 이동!
                if(colorCount[box] > 1) moveCount++;
                // 한 박스에 색상이 1개인 경우 : 다른 박스에 해당 색상만 존재하면 이동!
                else{
                    for(int color = 1; color <= M; color++){
                        // 해당 색상이 나온 적 없는 경우 : 이동 X
                        if(boxArr[box][color] > 0 && !visited[color]){
                            visited[color] = true;
                            break;
                        // 해당 색상이 나온 적 있는 경우 : 이동 O
                        }else if(boxArr[box][color] > 0 && visited[color]){
                            moveCount++;
                            break;
                        }
                    }
                }
            }

            answer = Math.min(answer, moveCount);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
