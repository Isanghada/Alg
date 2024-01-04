package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17178
// - 스택 : 기본 순서와 정렬 순서를 비교하여 차례로 진행!
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 정렬 변수 : 알파벳 기준 오름차순 정렬, 숫자 기준 오름차순 정렬
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 알파벳 비교
                int diff = o1.charAt(0)-o2.charAt(0);
                // 동일할 경우 : -(하이픈) 뒤의 숫자 비교
                if(diff == 0) {
                    int num1 = Integer.parseInt(o1.split("-")[1]);
                    int num2 = Integer.parseInt(o2.split("-")[1]);
                    return num1 - num2;
                }
                return diff;
            }
        };

        // 줄의 개수
        int N = Integer.parseInt(in.readLine());
        
        List<String> origin = new LinkedList<>();   // 기본 순서 리스트
        List<String> sort = new ArrayList<>();      // 정렬 순서 리스트
        // 줄 정보 입력
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j = 0; j < 5; j++){
                String cur = st.nextToken();
                origin.add(cur);
                sort.add(cur);
            }
        }
        // 순서 정렬!
        Collections.sort(sort, comparator);
//        System.out.println(origin);
//        System.out.println(sort);
        // 대기 줄 덱 : 스택으로 활용
        Deque<String> wait = new LinkedList<>();
        final int LIMIT = N * 5;    // 최대 인덱스 설정
        int originIdx = 0;  // 기본 순서 인덱스
        int sortIdx = 0;    // 정렬 순서 인덱스
        // 기본 순서 인덱스가 끝일 때까지 반복
        while(originIdx < LIMIT){
            // 기본과 정렬 순서가 동일한 경우 : 입장!
            if(origin.get(originIdx) == sort.get(sortIdx)){
                // 기본, 정렬 인덱스 증가
                originIdx++;
                sortIdx++;
            // 동일하지 않은 경우 : 대기 줄과 확인!
            }else{
                // 대기줄이 없거나 대기줄의 끝과 정렬 순서가 동일하지 않은 경우
                if(wait.isEmpty() || wait.peekLast() != sort.get(sortIdx))
                    // 대기줄에 기본 순서 추가!
                    wait.offerLast(origin.get(originIdx++));
                // 동일할 경우
                else{
                    // 대기줄 입장!
                    wait.pollLast();
                    // 정렬 순서 증가
                    sortIdx++;
                }
            }
        }
        // 정렬 순서 인덱스가 끝일 때까지 반복
        while(sortIdx < LIMIT){
            // 대기줄과 비교하여 동일한 경우 : 입장
            if(wait.peekLast() == sort.get(sortIdx)){
                wait.pollLast();
                sortIdx++;
            // 다를 경우 : 종료
            }else break;
        }
        // 정답 반환
        // - 대기줄에 사람이 없는 경우 : 성공
        // - 대기줄에 사람이 남은 경우 : 실패
        sb.append(wait.isEmpty() ? "GOOD" : "BAD");
        System.out.println(sb);
    }
}
