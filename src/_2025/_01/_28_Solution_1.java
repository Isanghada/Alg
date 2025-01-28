package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://www.acmicpc.net/problem/2831
// - 그리디 : 춤을 출 쌍이 정해진 사람은 다른 사람과 쌍을 이룰 수 없다!
//            따라서, 각 성별의 사람을 유형별로 총 4개의 그룹으로 나누어 입력 후 정렬
//            쌍을 이룰 수 있는 유형끼리 투 포인터를 활용해 차례로 쌍 구성!
public class _28_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        List<Integer> plusMan = new ArrayList<>();      // 키가 큰 여자를 선호하는 남자
        List<Integer> minusMan = new ArrayList<>();     // 키가 작은 여자를 선호하는 남자
        List<Integer> plusWoman = new ArrayList<>();    // 키가 큰 남자를 선호하는 여자
        List<Integer> minusWoman = new ArrayList<>();   // 키가 작은 남자를 선호하는 여자

        // 남자 정보
        String[] men = in.readLine().split(" ");
        // 여자 정보
        String[] women = in.readLine().split(" ");

        // 유형별로 입력 : 음수인 경우 양수로 추가!
        for(int i = 0; i < N; i++){
            int man = Integer.parseInt(men[i]);
            int woman = Integer.parseInt(women[i]);

            if(man > 0) plusMan.add(man);
            else minusMan.add(-man);

            if(woman > 0) plusWoman.add(woman);
            else minusWoman.add(-woman);
        }

        // 오름차순 정렬
        Collections.sort(plusMan);
        Collections.sort(minusMan);
        Collections.sort(plusWoman);
        Collections.sort(minusWoman);

        // 정답 초기화
        int answer = 0;
        // 키가 큰 여자를 선호하는 남자, 키가 작은 남자를 선호하는 여자인 경우
        for(int m = 0, w = 0; m < plusMan.size() && w < minusWoman.size();){
            int man = plusMan.get(m);
            int woman = minusWoman.get(w);

            if(man < woman) {
                answer++;
                m++;
            }
            w++;
        }
        // 키가 작은 여자를 선호하는 남자, 키가 큰 남자를 선호하는 여자인 경우
        for(int m = 0, w = 0; m < minusMan.size() && w < plusWoman.size();){
            int man = minusMan.get(m);
            int woman = plusWoman.get(w);

            if(man > woman) {
                answer++;
                w++;
            }
            m++;
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
