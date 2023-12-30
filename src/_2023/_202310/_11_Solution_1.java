package _2023._202310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1043
// - UNION, FIND 활용
// - 진실을 아는 사람을 root로 두고 각 사람들이 root와 연결되는지 체크
// - 파티의 모든 사람이 root에 연결되지 않는다면! 거짓말을 할 수 잇음.
public class _11_Solution_1 {
    // 정답
    public static int answer;
    // 부모 노드 배열
    public static int[] parents;
    // 진실을 아는 사람 집합
    public static Set<Integer> truthSet;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202310/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정답 초기화
        answer = 0;
        // 사람과 파티 수 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());   // 사람 수
        int m = Integer.parseInt(st.nextToken());   // 파티 수

        // 부모 배열 초기화 : 자기 자신을 부모로 가짐
        parents = new int[n+1];
        for(int i = 1; i <= n; i++) parents[i] = i;

        // 진실을 아는 사람 입력
        st = new StringTokenizer(in.readLine());
        // 진실을 아는 사람의 수
        int isTruth = Integer.parseInt(st.nextToken());
        // 0명일 경우 : 파티의 수 반환
        if(isTruth == 0) sb.append(m);
        // 0명이 아닐 경우 : 각 파티를 돌며 진실을 아는 사람과 연결되는지 체크
        else {
            // 진실을 아는 사람 집합에 사람 추가
            truthSet = new HashSet<>();
            while(st.hasMoreTokens()) truthSet.add(Integer.parseInt(st.nextToken()));

            // 파티 리스트 초기화
            List<Integer>[] partyList = new ArrayList[m];
            for(int i = 0; i < m; i++){
                // 새로운 파티 생성
                partyList[i] = new ArrayList<>();
                // 파티 정보 입력
                st = new StringTokenizer(in.readLine());

                // 파티에 참석하는 사람의 수
                int partyPeopleCount = Integer.parseInt(st.nextToken());
                // 파티에 참가하는 사람(첫 번째 값)
                int x =  Integer.parseInt(st.nextToken());
                // 파티 리스트에 추가
                partyList[i].add(x);
                // 이후 모든 사람을 x에 연결
                for(int j = 1; j < partyPeopleCount; j++){
                    int  y = Integer.parseInt(st.nextToken());
                    // x, y를 연결
                    union(x,y);
                    // y를 파티 리스트에 추가
                    partyList[i].add(y);
                }
            }

            // 모든 파티의 참석자들이 진실을 아는 사람과 연결되는지 확인
            for(int i = 0; i < m; i++){
                // 플래그 초기화
                boolean flag = true;
                // 파티별 참석자 모두 확인
                for(int num : partyList[i])
                    // 진실을 아는 사람과 연결된 경우
                    if(truthSet.contains(find(num))){
                        // - flag 변경 및 종료
                        flag = false;
                        break;
                    }
                // 진실을 아는 사람과 연결되지 않은 경우 정답 증가
                if(flag) answer++;
            }

            // 정답 반환
            sb.append(answer);
        }
        System.out.println(sb);
    }
    // 부모 체크 함수 : 재귀를 통해 루트 노드 반환
    private static int find(int x){
        // 부모가 자기 자신인 경우(루트) x 반환
        if(parents[x] == x) return x;
        // 아닐 경우 루트 노드까지 재귀하여 반환
        return find(parents[x]);
    }

    // 유니온 함수 : x, y 집합을 연결시키는 함수
    private static void union(int x, int y) {
        // x, y의 루트 노드 계산
        int parentX = find(x);
        int parentY = find(y);
        // y의 루트가 진실을 아는 사람일 경우 값 스위치
        if(truthSet.contains(parentY)){
            int temp = parentX;
            parentX = parentY;
            parentY = temp;
        }
        // y의 루트를 x의 루트에 연결
        parents[parentY] = parentX;
    }
}
