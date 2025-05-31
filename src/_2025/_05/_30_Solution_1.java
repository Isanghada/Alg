package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18233
// - 그리디 : 선물을 줄 회원을 선택한 후 정확히 E개를 나눌 수 있는지 확인!
//              1. 조합을 통해 선물을 줄 회원 선택 : nextParmutation 활용
//              2. 최소 선물, 최대 선물의 개수를 구하여 E개가 포함되는지 확인
//              3. 가능하다면 각 회원에게 최소 선물로 설정 후 E개가 될 때까지 추가!
public class _30_Solution_1 {
    // 회원 클래스
    static class Member{
        int min;    // 최소 선물
        int max;    // 최대 선물
        public Member(int min, int max){
            this.min = min;
            this.max = max;
        }
    }
    static int N, P, E;
    static boolean FLAG;
    static int[] GIFTS;
    static Member[] MEMBERS;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 회원의 수
        P = Integer.parseInt(st.nextToken());   // 선물을 줄 회원의 수
        E = Integer.parseInt(st.nextToken());   // 러버덕 개수

        // 회원 초기화
        MEMBERS = new Member[N];
        // 선물 개수 초기화
        GIFTS = new int[N];
        // 회원 정보 입력
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(in.readLine());
            MEMBERS[n] = new Member(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 플래그 초기화
        FLAG = false;
        // 불가능한 경우
        if(N < P) sb.append(-1);
        else{
            // 순서 초기화 : P개 설정!
            int[] order = new int[N];
            for(int n = N - P; n < N; n++) order[n] = 1;
            // nextPermutation을 통해 모두 탐색
            do{
                // 선물을 줄 회원 리스트
                List<Integer> memberList = new ArrayList<>();
                int minSum = 0; // 최소 선물의 합
                int maxSum = 0; // 최대 선물의 합
                for(int n = 0; n < N; n++){
                    if(order[n] == 1){
                        minSum += MEMBERS[n].min;
                        maxSum += MEMBERS[n].max;
                        memberList.add(n);
                    }
                }
                // E개가 가능한 경우 개수 계산 후 종료
                if(minSum <= E && E <= maxSum){
                    // 최소 선물을 주고 남는 선물
                    int diff = E - minSum;
                    // 각 회원에게 차례로 선물 추가!
                    for(int member : memberList){
                        GIFTS[member] = MEMBERS[member].min;
                        if(diff != 0){
                            int addGift = Math.min(diff, MEMBERS[member].max - MEMBERS[member].min);
                            diff -= addGift;
                            GIFTS[member] += addGift;
                        }
                    }
                    FLAG = true;
                    break;
                }
            }while(nextPermutation(order, N));

            // 가능한 경우 선물 개수 반환
            if(FLAG){
                for(int n = 0; n < N; n++) {
                    sb.append(GIFTS[n]).append(" ");
                }
            }else sb.append(-1);
        }

        // 정답 반환
        System.out.println(sb);
    }

    private static boolean nextPermutation(int[] order, int n) {
        int i = n - 1;
        while(i > 0 && order[i-1] >= order[i]) i--;

        if(i == 0) return false;

        int j = n - 1;
        while(order[i-1] >= order[j]) j--;

        int temp = order[i-1];
        order[i-1] = order[j];
        order[j] = temp;
        Arrays.sort(order, i, n);

        return true;
    }
}