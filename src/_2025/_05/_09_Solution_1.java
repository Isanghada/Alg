package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5021
// -
public class _09_Solution_1 {
    static final long BLOOD = 2_251_799_813_685_248L;
    static class Parent{
        String father;
        String mother;
        public Parent(String father, String mother){
            this.father = father;
            this.mother = mother;
        }
        @Override
        public String toString(){
            return "[ father="+this.father+", mother="+this.mother+" ]";
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String royalFamily = in.readLine();

        Map<String, Parent> family = new HashMap<>();
        Map<String, Long> blood = new HashMap<>();

        while(N-- > 0){
            st = new StringTokenizer(in.readLine());
            String child = st.nextToken();
            String parentA = st.nextToken();
            String parentB = st.nextToken();

            family.put(child, new Parent(parentA, parentB));
            blood.put(child, -1L);
            blood.put(parentA, -1L);
            blood.put(parentB, -1L);
        }
        blood.put(royalFamily, BLOOD);

        // System.out.println(family);
        for(String name : blood.keySet()) dfs(name, blood, family);

        long maxBlood = 0;
        String answer = "";
        // System.out.println(blood);
        while(M-- > 0){
            String name = in.readLine();
            // System.out.println(name+", "+blood.get(name));
            if(blood.containsKey(name) && maxBlood < blood.get(name)){
                maxBlood = blood.get(name);
                answer = name;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString().trim());
    }

    private static long dfs(String name, Map<String, Long> blood, Map<String, Parent> family) {
        if(!family.containsKey(name) && blood.get(name) == -1) blood.put(name, 0L);

        if(blood.get(name) == -1L){
            long father = dfs(family.get(name).father, blood, family) / 2;
            long mother = dfs(family.get(name).mother, blood, family) / 2;

            blood.put(name, father + mother);
        }

        return blood.get(name);
    }
}
