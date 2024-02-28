package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

// https://www.acmicpc.net/problem/2608
// - 구현 : (로마-아라비안)의 쌍을 Map으로 구현하여 계산!
public class _28_Solution_1 {
    // 로마 -> 아라비안 Map
    public static Map<String, Integer> MAP;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_28_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // (로마 -> 아라비안) 쌍 초기화
        initMap();

        // 합 초기화
        int sum = 0;
        // 로마 숫자를 아라비안 숫자로 변환하여 연산
        for(int i = 0; i < 2; i++) sum += romeToArabian(in.readLine().toCharArray());
        
        // 정답 반환
        // - sum을 로마 숫자로 변환하여 반환
        sb.append(sum).append("\n").append(arabianToRome(sum));
        System.out.println(sb);
    }
    // 로마 숫자 -> 아라비안 초기화 함수
    // - 활용되는 기호 모두 추가!
    private static void initMap() {
        MAP = new HashMap<>();
        MAP.put("I", 1);
        MAP.put("IV", 4);
        MAP.put("V", 5);
        MAP.put("IX", 9);
        MAP.put("X", 10);
        MAP.put("XL", 40);
        MAP.put("L", 50);
        MAP.put("XC", 90);
        MAP.put("C", 100);
        MAP.put("CD", 400);
        MAP.put("D", 500);
        MAP.put("CM", 900);
        MAP.put("M", 1000);
    }
    // 아라비안->로마 변환
    // - 큰 자릿수의 숫자부터 차례로 변환
    private static String arabianToRome(int sum) {
        StringBuilder rome = new StringBuilder();
        List<Entry<String, Integer>> list = new ArrayList<>(MAP.entrySet());
        Collections.sort(list, Entry.comparingByValue(Collections.reverseOrder()));
//        System.out.println(list);
        for(Entry<String, Integer> entry : list){
            int count = sum / entry.getValue();
            if(count != 0){
                while(count-- > 0) rome.append(entry.getKey());
                sum %= entry.getValue();
            }
        }

        return rome.toString();
    }

    // 로마->아라비안 변환
    // - 로마 숫자 처음부터 차례로 변환
    private static int romeToArabian(char[] rome) {
        int arabian = 0;
        for(int i = 0; i < rome.length; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(rome[i]);
            if((sb.charAt(0) == 'I' || sb.charAt(0) == 'X' || sb.charAt(0) == 'C')
                && i < (rome.length - 1)){
                sb.append(rome[i+1]);
                if(MAP.containsKey(sb.toString())) i++;
                else sb.deleteCharAt(sb.length()-1);
            }
            arabian += MAP.get(sb.toString());
        }

        return arabian;
    }
}
