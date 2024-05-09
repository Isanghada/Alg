package _2024._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19639
// - 그리디 : 적 처치부터 시작하여 아이템먹는 것을 진행!
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_05/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int X = Integer.parseInt(st.nextToken());   // 적의 수
        int Y = Integer.parseInt(st.nextToken());   // 아이템의 수
        int M = Integer.parseInt(st.nextToken());   // 최대 체력

        // 체력 최대 변화량
        final int MAX_CHANGE = M / 2;

        // 적 정보 : 최대 피해량은 MAX_CHANGE로 제한
        int[] enemyArr = new int[X+1];
        for(int i = 1; i <= X; i++) enemyArr[i] = Math.min(MAX_CHANGE, Integer.parseInt(in.readLine()));

        // 아이템 정보 : 최대 회복량은 MAX_CHANGE로 제한
        int[] itemArr = new int[Y+1];
        for(int i = 1; i <= Y; i++) itemArr[i] = Math.min(MAX_CHANGE, Integer.parseInt(in.readLine()));

        int enemyIdx = 1;   // 적 인덱스
        int itemIdx = 1;    // 아이템 인덱스
        int curHP = M;      // 현재 체력

        // 적 처치부터 체력 회복을 차례로 진행
        // - 적 처지가 가능한 경우 적 처치!
        // - 적 처치가 불가능하면 체력 회복!
        while(true){
            // 적에게 피해를 받아도 죽지 않을 때 적 처치
            if(enemyIdx <= X && curHP > MAX_CHANGE){
                sb.append(-enemyIdx).append("\n");
                curHP -= enemyArr[enemyIdx++];
            // 체력 회복!
            }else if(itemIdx <= Y && curHP <= MAX_CHANGE){
                sb.append(itemIdx).append("\n");
                curHP += itemArr[itemIdx++];
            }else break;
        }
        // 남은 적 모두 진행
        while(enemyIdx <= X){
            sb.append(-enemyIdx).append("\n");
            curHP -= enemyArr[enemyIdx++];
        }
        // 남은 체력 회복 모두 진행
        while(itemIdx <= Y){
            sb.append(itemIdx).append("\n");
            curHP += itemArr[itemIdx++];
        }

        // 정답 출력
        // - 체력이 0 이하이면 불가능하므로 0 출력
        // - 가능하다면 순서 출력
        if(curHP <= 0) System.out.println(0);
        else System.out.println(sb);
    }
}
