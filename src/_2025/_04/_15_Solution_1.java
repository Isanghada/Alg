package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25542
// - 브루트포스 : 가능한 가게 후보의 이름을 모두 체크!
//                  1. 주어진 가게 이름 후보 중 1개를 선택하여
//                      각 자리를 모든 알파벳을 사용해 가능한 형태 모두 추가 : Set을 통해 중복 제거
//                  2. 다른 가게 이름 후보와 비교하여 1개 이하의 차이가 있는 것만 남기고 제거
//                  3. 남은 최종 후보 중 1개 출력, 없다면 "sCALL FRIEND"
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 가게 이름 후보의 수
        int L = Integer.parseInt(st.nextToken());   // 가게 이름 길이

        // 가게 이름 후보 입력
        String[] nameArr = new String[N];
        for(int i = 0; i < N; i++) nameArr[i] = in.readLine();

        // 최종 후보 Set
        Set<String> nameSet = new HashSet<>();
        // 가게 이름 후보 중 택 1
        char[] candidate = nameArr[0].toCharArray();

        // 가능한 모든 변형을 set에 추가
        for(int i = 0; i < L; i++){
            char before = candidate[i];
            // 각 자리를 A~Z로 바꾸어가며 추가
            for(char after = 'A'; after <= 'Z'; after++){
                candidate[i] = after;
                nameSet.add(String.valueOf(candidate));
            }
            candidate[i] =  before;
        }

        // 가게 이름 후보 삭제 : set에 추가된 후보 중 불가능한 경우 삭제
        // - 1개 초과의 차이를 보이는 경우 삭제
        for(int i = 1; i < N; i++){
            List<String> removeList = new ArrayList<>();
            for(String target : nameSet){
                if(!check(target, nameArr[i], L)) removeList.add(target);
            }

            for(String remove : removeList) nameSet.remove(remove);
        }

        if(nameSet.size() == 0) sb.append("CALL FRIEND");
        else{
            for(String answer : nameSet){
                sb.append(answer);
                break;
            }
        }

        // 정답 출력
        System.out.println(sb.toString().trim());
    }

    private static boolean check(String target, String name, int L) {
        int count = 0;
        for(int i = 0; i < L; i++){
            if(target.charAt(i) != name.charAt(i)) count++;
        }
        if(count <= 1) return true;
        else return false;
    }
}
