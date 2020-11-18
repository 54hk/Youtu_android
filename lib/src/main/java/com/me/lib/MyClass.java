package com.me.lib;


import java.util.Arrays;
import java.util.Stack;


public class MyClass {
    public static void main(String[] args) {
//        System.out.println("11111");
//        se();
//        int[] a = {66, 4, 6, 8, 99, 9, 2, 99};
//        System.out.println(a.toString() + "===" + Arrays.toString(a));
//        toSort(a);
        stackAdd();
//        LinkListClass.HeroNode temb = heroNode.next;
//        while (true){
//            if(temb == null){
//                return;
//            }
//            System.out.println(temb.no+"-----");
//            temb = temb.next;
//        }
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(4);
//        while (stack.size()>0){
//            System.out.println(stack.pop());
//        }
    }

    public static void addStack() {

    }


    public static void stackAdd() {
//        Stack<Integer> s1 = new Stack<>();
//        s1.push(5);
//        s1.push(2);
//        s1.push(6);
//        Stack<Integer> s2 = new Stack<>();
//        s2.push(3);
//        s2.push(1);
//        s2.push(4);
//        int sum = 0;
//        LinkListClass.HeroNode head = new LinkListClass.HeroNode(0, "");
//        while (!s1.empty() || !s2.empty()) {
//            if (!s1.empty())
//                sum += s1.pop();
//            if (!s2.empty())
//                sum += s2.pop();
//            System.out.println(sum +"===sum");
//            head.no = sum % 10;
//            System.out.println(head.no +"===head.no");
//
//        }
//        return head.no == 0 ? head.next : head;
        Stack<Integer> stackOne = new Stack<>();
        stackOne.push(8);
        stackOne.push(2);
        stackOne.push(6);
        Stack<Integer> stackTwo = new Stack<>();
        stackTwo.push(3);
        stackTwo.push(1);
        stackTwo.push(3);


        int sum = 0;
        while (!stackOne.empty() || !stackTwo.empty()) {

            if (!stackOne.empty()) {
                sum += stackOne.pop();
            }
            if (!stackTwo.empty()) {
                sum += stackTwo.pop();
            }
            System.out.println(sum + "----sum%10 == " + (sum % 10));
            sum /= 10;
            System.out.println("----sum /= 10 == " + (sum));
        }
    }


    //冒泡排序
    private static void toSort(int[] arr) {
        //拿第一个和其他数去比较，成功提前（小的在前，大的在后）
        for (int i = 0; i < arr.length - 1; i++) {
            for (int ii = i; ii < arr.length - 1; ii++) {
                System.out.println("+++++++   " + arr[ii + 1] + "--------" + arr[ii]);
                if (arr[ii + 1] < arr[ii]) {
                    System.out.println("~~~~~~   " + arr[ii + 1] + "--------" + arr[ii]);
                    int tem = arr[ii + 1];
                    arr[ii + 1] = arr[i];
                    arr[i] = tem;
                }
                System.out.println("2 - for --   " + Arrays.toString(arr).toString());
            }
        }
        System.out.println("1 + for+++=    " + Arrays.toString(arr).toString());
    }

    public static void se() {
        System.out.println("------");
    }

    //  二分查找
    private static int binarySearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length;
        int mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] < key)
                start = mid + 1;
            if (arr[mid] > key) {
                end = mid - 1;
            }
        }
        return -1;
    }

    //  快速排序
    void quick_sort(int a[], int l, int r) {
        if (l < r) {
            int i, j, x;

            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while (i < j && a[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if (i < j)
                    a[i++] = a[j];
                while (i < j && a[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quick_sort(a, l, i - 1); /* 递归调用 */
            quick_sort(a, i + 1, r); /* 递归调用 */
        }
    }

    public int str2Indexstr1(String mainStr, String subStr, int pos) {
        int mainPos = 0;
        int subPos = 0;
        while (mainPos < mainStr.length() && subPos < subStr.length()) {
            if (mainStr.toCharArray()[mainPos] == subStr.toCharArray()[subPos]) {
                mainPos++;
                subPos++;
            } else {
                mainPos = mainPos - subPos + 1;
                subPos = 0;
            }
        }
        if (subPos == subStr.length()) {
            return mainPos - subPos;
        } else
            return -1;
    }


    public int str2IndexStr3(String mainStr, String subStr) {
        int mainPos = 0;
        int subPos = 0;
        while (mainPos < mainStr.length() && subPos < subStr.length()) {
            if (mainStr.toCharArray()[mainPos] == subStr.toCharArray()[subPos]) {
                mainPos++;
                subPos++;
            } else {
                mainPos = mainPos - subPos + 1;
                subPos = 0;
            }
        }
        if (subPos == subStr.length()) {
            return mainPos - subPos;
        } else
            return -1;
    }

    //冒泡排序
    public void maoPaoSort(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    int temb = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temb;
                }
            }
            if (!flag) {
                break;
            } else
                flag = false;
        }
    }


}
