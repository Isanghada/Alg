package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3019
// - 브루투포스 : 블록의 모든 경우 탐색!
public class _08_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int C = Integer.parseInt(st.nextToken());   // 열 길이
        int P = Integer.parseInt(st.nextToken());   // 블록 번호

        // 보드 정보
        int[] board = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 블록 정보
        List<Block> blockList = setBlockList(P);

        // 정답 초기화
        int answer = 0;
        // 모든 블록 탐색
        for(Block block : blockList){
            // 가능한 모든 위치 탐색
            for(int limit = block.size; limit <= C; limit++){
                // 가능 여부 플래그
                boolean flag = true;
                // 블록 시작 높이
                int value = board[limit-block.size];
                for(int c = limit - block.size, idx = 0; c < limit; c++, idx++){
                    // 가능한 경우
                    if((board[c] - value) == block.shape[idx]) continue;
                    // 불가능한 경우
                    flag = false;
                    break;
                }
                // 가능하다면 정답 증가
                if(flag) answer++;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    private static class Block {
        int size;
        int[] shape;
        public Block(int size, int[] shape){
            this.size = size;
            this.shape = shape;
        }
    }

    private static List<Block> setBlockList(int p){
        List<Block> result = new ArrayList<>();

        if(p == 1){
            result.add(new Block(1, new int[]{0}));
            result.add(new Block(4, new int[]{0, 0, 0, 0}));
        }else if(p == 2){
            result.add(new Block(2, new int[]{0, 0}));
        }else if(p == 3){
            result.add(new Block(3, new int[]{0, 0, 1}));
            result.add(new Block(2, new int[]{0, -1}));
        }else if(p == 4){
            result.add(new Block(3, new int[]{0, -1, -1}));
            result.add(new Block(2, new int[]{0, 1}));
        }else if(p == 5){
            result.add(new Block(3, new int[]{0, 0, 0}));
            result.add(new Block(3, new int[]{0, -1, 0}));
            result.add(new Block(2, new int[]{0, 1}));
            result.add(new Block(2, new int[]{0, -1}));
        }else if(p == 6){
            result.add(new Block(3, new int[]{0, 0, 0}));
            result.add(new Block(3, new int[]{0, 1, 1}));
            result.add(new Block(2, new int[]{0, 0}));
            result.add(new Block(2, new int[]{0, -2}));
        }else if(p == 7){
            result.add(new Block(3, new int[]{0, 0, 0}));
            result.add(new Block(3, new int[]{0, 0, -1}));
            result.add(new Block(2, new int[]{0, 0}));
            result.add(new Block(2, new int[]{0, 2}));
        }

        return result;
    }
}
