package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16987
// - 브루트포스 : 가능한 모든 경우 탐색!
//                각 계란으로 다른 모든 계란을 치는 경우 차례로 계산
public class _24_Solution_1 {
    // 계란의 수, 정답
    public static int N, ANSWER;
    // 계란 정보 배열
    public static EGG[] EGGS;
    // 계란 클래스
    public static class EGG{
        int hp;     // 내구도
        int weight; // 무게
        public EGG(int hp, int weight){
            this.hp = hp;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 계란 개수
        N = Integer.parseInt(in.readLine());
        // 계란 정보 입력
        EGGS = new EGG[N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            EGGS[i] = new EGG(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 정답 초기화
        ANSWER = 0;
        // 브루트포스 : 재귀를 통해 모든 경우 탐색
        getMaxCount(0);

        // 정답 출력
        sb.append(ANSWER);
        System.out.println(sb);
    }

    private static void getMaxCount(int cur) {
        // 마지막 계란까지 검사한 경우 계란 개수 계산 후 최대값 갱신
        if(cur == N){
            int count = 0;
//            System.out.print("[ ");
            for(int i = 0; i < N; i++) {
//                System.out.print(EGGS[i].hp+" ");
                if(EGGS[i].hp <= 0) count++;
            }
//            System.out.println(" ]");
            ANSWER = Math.max(ANSWER, count);
        // 이미 깨진 계란인 경우 : 다음으로 재귀
        }else if(EGGS[cur].hp <= 0) getMaxCount(cur+1);
        else{
            // 모든 계란을 대상으로 탐색
            for(int target = 0; target < N; target++){
                // 대상 계란과 타겟 계란이 다른 경우만 탐색
                if(cur != target){
                    // 타겟 계란이 깨지지 않은 경우
                    // - 각 계란의 내구도 감소 후 재귀
                    if(EGGS[target].hp > 0){
                        EGGS[cur].hp -= EGGS[target].weight;
                        EGGS[target].hp -= EGGS[cur].weight;
                        getMaxCount(cur+1);
                        EGGS[cur].hp += EGGS[target].weight;
                        EGGS[target].hp += EGGS[cur].weight;
                    // 타겟 계란이 깨진 경우: 재귀
                    }else getMaxCount(cur+1);
                }
            }
        }
    }
}
