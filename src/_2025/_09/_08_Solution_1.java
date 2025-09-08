package _2025._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3190
// - 구현 : 덱을 활용해 중력 큐 구현
public class _08_Solution_1 {
    static class GravityQueue{
        // 0 :  뒤 - 앞
        // 1 :  뒤
        //      앞
        // 2 :  앞 - 뒤
        // 3 :  앞
        //      뒤
        int type;
        int ballCount;
        int wallCount;
        Deque<Boolean> deque;
        public GravityQueue(){
            this.type = 0;
            this.ballCount = 0;
            this.wallCount = 0;
            this.deque = new LinkedList<>();
        }
        public void pushB(){
            if((this.type == 1 && this.wallCount == 0) ||
                    (this.type == 3)
            ) return;
            this.deque.offerLast(true);
            this.ballCount++;
        }
        public void pushW(){
            this.deque.offerLast(false);
            this.wallCount++;
        }
        public void rotateL(){
            this.type--;
            if(this.type < 0) this.type += 4;

            if(this.type == 1) {
                while(!this.deque.isEmpty() && this.deque.peekFirst()) {
                    this.deque.pollFirst();
                    this.ballCount--;
                }
            }
            else if(this.type == 3) {
                while(!this.deque.isEmpty() && this.deque.peekLast()) {
                    this.deque.pollLast();
                    this.ballCount--;
                }
            }
        }

        public void rotateR(){
            this.type++;
            if(this.type > 3) this.type = 0;

            if(this.type == 1) {
                while(!this.deque.isEmpty() && this.deque.peekFirst()) {
                    this.deque.pollFirst();
                    this.ballCount--;
                }
            }
            else if(this.type == 3) {
                while(!this.deque.isEmpty() && this.deque.peekLast()) {
                    this.deque.pollLast();
                    this.ballCount--;
                }
            }
        }
        public void pop(){
            if(this.deque.isEmpty()) return;
            if(this.deque.pollFirst()) this.ballCount--;
            else this.wallCount--;

            if(this.type == 1) {
                while(!this.deque.isEmpty() && this.deque.peekFirst()) {
                    this.deque.pollFirst();
                    this.ballCount--;
                }
            }
            else if(this.type == 3) {
                while(!this.deque.isEmpty() && this.deque.peekLast()) {
                    this.deque.pollLast();
                    this.ballCount--;
                }
            }
        }
        public int countB(){ return this.ballCount; }
        public int countW(){ return this.wallCount; }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_09/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(in.readLine());
        GravityQueue gravityQueue = new GravityQueue();

        StringTokenizer st = null;
        while(Q-- > 0){
            st = new StringTokenizer(in.readLine());
            String command = st.nextToken();

            if(command.equals("pop")) gravityQueue.pop();
            else{
                char type = st.nextToken().charAt(0);
                if(command.equals("push")){
                    if(type == 'b') gravityQueue.pushB();
                    else gravityQueue.pushW();
                }else if(command.equals("rotate")){
                    if(type == 'l') gravityQueue.rotateL();
                    else gravityQueue.rotateR();
                }else{
                    if(type == 'b') sb.append(gravityQueue.countB()).append("\n");
                    else sb.append(gravityQueue.countW()).append("\n");
                }
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}
