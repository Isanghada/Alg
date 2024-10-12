package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14718
// - 브루트포스 : 힘, 민첩, 지능을 가능한 모든 경우를 선택하여 확인!
public class _11_Solution_1 {
    // 스탯 클래스
    public static class Stat{
        int power;          // 힘
        int agility;        // 민첩
        int intelligence;   // 지능
        public Stat(int power, int agility, int intelligence){
            this.power = power;
            this.agility = agility;
            this.intelligence = intelligence;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 병사의 수
        int K = Integer.parseInt(st.nextToken());   // 목표 병사의 수

        // 병사 스탯 정보!
        Stat[] stats = new Stat[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            stats[i] = new Stat(
                    Integer.parseInt(st.nextToken()),   // 힘
                    Integer.parseInt(st.nextToken()),   // 민첩
                    Integer.parseInt(st.nextToken())    // 지능
            );
        }

        // 정답 초기화
        int answer = Integer.MAX_VALUE;

        // 힘, 민첩, 지능이 될 스탯 결정 후 이길 수 있는 병사의 수 탐색!
        // - 목표 병사 이상인 경우 정답 갱신!
        // - 인덱스 p : 힘 스탯 인덱스
        // - 인덱스 a : 민첩 스탯 인덱스
        // - 인덱스 i : 지능 스탯 인덱스
        for(int p = 0; p < N; p++){
            for(int a = 0; a < N; a++){
                for(int i = 0; i < N; i++){
                    int count = 0;
                    for(Stat s : stats){
                        if(stats[p].power >= s.power &&
                                stats[a].agility >= s.agility &&
                                stats[i].intelligence >= s.intelligence
                        ) count++;
                    }
                    if(count >= K) answer = Math.min(answer, stats[p].power+stats[a].agility+stats[i].intelligence);
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
