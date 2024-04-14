package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/26092
// - 수학 : 시간 복잡도를 정확히 계산하고 하기
//   1. 소인수 구하기 : 소인수가 될 수 있는 경우는 sqrt(x)! 따라서 O(sqrt(x))
//   2. 조상 만들기 : 최소 2이상의 수로 나누어 지므로 O(log x)
//   3. 공통 조상 구하기 : a의 조상 개수를 A, b의 조상 개수를 B라고 한다면
//                         a의 모든 조상을 이분 탐색을 통해 b의 조상에 포함되는지 확인. 따라서 O(AlogB)
//                         + Set을 통해 확인한다면 O(A) -> 정렬이 필요하다면 O(AlogA)
// https://www.grepiu.com/post/9
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        long a = Long.parseLong(st.nextToken());    // 정수 a
        long b = Long.parseLong(st.nextToken());    // 정수 b

//        // A의 조상 정점
//        List<Long> A = getParentList(a);
//        // B의 조상 정점
//        List<Long> B = getParentList(b);
        // A의 조상 정점
        Set<Long> setA = getParentSet(a);
        // B의 조상 정점
        Set<Long> setB = getParentSet(b);

//        System.out.println(A);
//        System.out.println(B);

        //
//        for(long x : A){
//            if(binarySearch(x, B)){
//                sb.append(x);
//                break;
//            }
//        }
        for(long x : setA){
            if(setB.contains(x)){
                sb.append(x);
                break;
            }
        }

        System.out.println(sb.toString());
    }
    private static void getParents(Collection<Long> result, long x){
        result.add(x);
        final long LIMIT = (long) Math.sqrt(x);

        long div = 2;
        while(x > 0 && div <= LIMIT){
            if(x % div == 0){
                x /= div;
                result.add(x);
            }else div++;
        }
        result.add(1L);
    }
    private static Set<Long> getParentSet(long x){
        Set<Long> result = new LinkedHashSet<>();

        getParents(result, x);

        return result;
    }
    private static List<Long> getParentList(long x){
        List<Long> result = new ArrayList<>();

        getParents(result, x);

        return result;
    }
    private static boolean binarySearch(long x, List<Long> numbers){
        int left = 0;
        int right = numbers.size() - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(numbers.get(mid) == x) return true;
            else if(numbers.get(mid) > x) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}

