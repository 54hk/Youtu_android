package com.me.lib;

public class CircleQueue {
    public static void main(String[] args) {

    }

    public class CirCleArray {
        int mMax;
        int rear;
        int front;
        int[] mArr;

        public CirCleArray(int max) {
            mMax = max;
            mArr = new int[max];
            front = 0;
            rear = 0;
        }

        //是否满：(rear + 1)/max = front
        public boolean isFull() {
            if ((rear + 1) % mMax == front) {
                return true;
            }
            return false;
        }

        //是否null：队列的最后一个 = 队列头的前一个
        public boolean isEmpty() {
            if (front == rear) {
                return true;
            }
            return false;
        }

        //加入数据
        public void addQueue(int num) {
            if (isFull()) {
                return;
            }
            mArr[rear] = num;
            rear = (rear + 1) / 3;
        }


        //删除数据
        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("null -> 目前队列是空的");
            }
            int frontValue = mArr[front];
            front = (front + 1) / 3;
            return frontValue;
        }

        public void getAllQueue() {
//            if (isEmpty()) {
//                throw new RuntimeException("null -> 目前队列是空的");
//            }
            for (int i = front; i < front + have(); i++) {
                System.out.println(i + " ---- " + mArr[i]);
            }
        }

        public int have() {
            return (rear + mMax + front) % mMax;
        }
    }
}
