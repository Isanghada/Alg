package _202310;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/64063
// - Map<방 번호, 배정할 번호>를 사용해 빠르게 제공할 방을 찾습니다.
//   - key가 사용자가 원하는 방, value가 사용자에게 실제로 배정될 방.
// - 유니온·파인드 개념을 활용해서 해결했습니다.
// - 이미 배정된 방을 원할 경우 value값으로 재귀 탐색해 새로운 방을 찾습니다.
// - 차후에 빠른 탐색을 위해 각 key 값의 value도 업데이트 진행!
public class _01_Solution_1 {
    // 원하는 번호마다 배정될 방 번호를 기록할 map
    public static Map<Long, Long> isUsed;
    public long[] solution(long k, long[] room_number) {
        // 배열 크기만큼 정답 배열 초기화
        long[] answer = new long[room_number.length];
        // 방 번호 Map 초기화
        isUsed = new HashMap<>();

        // 고객 배열 모두 탐색
        for(int idx = 0; idx < room_number.length; idx++){
            // 고객이 원하는 방 번호 입력
            long room = room_number[idx];
            // 이미 배정된 방일 경우
            if(isUsed.containsKey(room)){
                // 새로운 방 번호 탐색
                room = getNextRoom(room);
            }
            // 정답 추가
            answer[idx] = room;
            // 방 번호 key로 map에 추가
            isUsed.put(room, room+1);
//            System.out.println(Arrays.toString(answer));
        }

        // 정답 반환
        return answer;
    }

    // 방 탐색 함수 : 재귀를 통해 room에 대한 새로운 방 번호 반환
    // - room : 탐색할 방 번호
    private long getNextRoom(long room) {
//        System.out.println(isUsed);
        // Map에서 room을 키로 가지는 value 반환
        long next = isUsed.get(room);
        // 이미 배정된 방일 경우 재귀 탐색
        if(isUsed.containsKey(next)){
            next = getNextRoom(next);
        }
        // next로 value 업데이트
        isUsed.put(room, next+1);

        // 방 번호 반환
        return next;
    }
}
