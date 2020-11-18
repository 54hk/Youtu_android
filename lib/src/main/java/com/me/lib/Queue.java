package com.me.lib;

public class Queue {
    public static void main(String[] args) {

        ArrayQuene arrayQuene = new ArrayQuene(3);
        arrayQuene.addQueue(1);
        arrayQuene.addQueue(3);
        arrayQuene.addQueue(5);
        arrayQuene.getAllQueue();
        System.out.println(arrayQuene.getQueue() + "---");
        System.out.println(arrayQuene.headQueue() + "----");

    }

    static class ArrayQuene {
        private int mMax;//最大容器
        private int mRead; //队列头的前一个
        private int front;//队列的最后一个
        private int[] mArr;

        public ArrayQuene(int max) {
            this.mMax = max;
            mArr = new int[max];
            mRead = -1;
            front = -1;
        }

        //是否满：队列的最后一个 = 最大容器
        public boolean isFull() {
            if (front == mMax) {
                return true;
            }
            return false;
        }

        //是否null：队列的最后一个 = 队列头的前一个
        public boolean isEmpty() {
            if (front == mRead) {
                return true;
            }
            return false;
        }

        //加入数据
        public void addQueue(int num) {
            if (isFull()) {
                return;
            }
            mRead++;
            mArr[mRead] = num;
        }

        //删除数据
        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("null -> 目前队列是空的");
            }
            front++;
            return mArr[front];
        }

        public int headQueue() {
            return mArr[front + 1];
        }

        public void getAllQueue() {
//            if (isEmpty()) {
//                throw new RuntimeException("null -> 目前队列是空的");
//            }
            for (int i = 0; i < mArr.length; i++) {
                System.out.println(i + " ---- " + mArr[i]);
            }
        }

    }
}
