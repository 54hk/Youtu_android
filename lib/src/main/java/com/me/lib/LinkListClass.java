package com.me.lib;

public class LinkListClass {

    public static void main(String[] args) {

    }

    //定义一个SingleLinkedList,来管理我们的heroNode节点
    class SingleLinkedList {
        //首先创建一个头节点，不需动，不需要添加任何数据（作用就是代表单链表的头）
        private HeroNode head = new HeroNode(0, "");


        /**
         * 增加节点到单向链表
         * <p>
         * 思路：不考虑排列序号
         * 1.找到当前列表的最后一个节点
         * 2.将最后的一个节点指向一个新的节点
         */
        public void addLinked(HeroNode heroNode) {
            //因为head节点不能动，我们需要辅助节点
            HeroNode temb = head;
            //遍历，找到最后
            while (true) {
                //如果temb.next == null 说明找到链表的最后
                if (temb.next == null) {
                    break;
                }
                //否则，将temb往后移，进行下一个节点
                temb = heroNode.next;
            }
            //退出while循环，说明已经找到来最后一个节点
            //将最后的节点.next 指向 最新的节点
            temb.next = heroNode;
        }

        //思路：考虑到序号（跟后一个节点做比较）
        public void addOrderLinked(HeroNode heroNode) {
            HeroNode temb = head;
            boolean flag = false; // 是否有此数据
            while (true) {
                //已经链表尾部啦
                if (temb.next == null) {
                    return;
                }
                //位置找到就在，temb到后面插入
                if (temb.next.no > heroNode.no) {
                    break;
                } else if (temb.next.no == heroNode.no) {//说明增加到no编号已经存在
                    flag = true;
                    break;
                }
                temb = temb.next; // 后移，遍历
            }

            if (flag) {
                System.out.println("此链表由此数据");
            } else {
                // 将temb.next节点 - > 用于新节点.next
                heroNode.next = temb.next;
                //将temb当前节点.next =>直引到herNode节点
                temb.next = heroNode;
            }
        }

        /**
         * 更新节点到数据
         */
        public void updateLinked(HeroNode heroNode) {
            HeroNode temb = head.next;
            boolean flag = false;//根据no字段判断是否含有此节点

            while (true) {
                //已经到链尾啦
                if (temb == null) {
                    break;
                }

                if (temb.no == heroNode.no) {
                    flag = true;
                }
                temb = temb.next;
            }
            if (flag) { //对应上no序列，修改值
                temb.next = heroNode.next;
            } else {
                System.out.println("没有此节点到数据");
            }
        }

        //删除一个节点
        public void del(HeroNode heroNode) {
            HeroNode temb = head.next;
            boolean isflag = false;
            while (true) {
                if (temb == null) {
                    break;
                }
                if (temb.no == heroNode.no) {
                    isflag = true;
                }
                temb = temb.next;
            }

            if (isflag) {
                temb.next = temb.next.next;
            }

        }

        /**
         * 遍历节点到单向链表
         */
        public void list() {
            //因为head节点不能动，我们需要一个辅助节点
            HeroNode temb = head.next;
            while (true) {
                //已经到达啦链表的末尾
                if (temb.next == null) {
                    return;
                }
                System.out.println("当前节点的数据 - > " + temb.toString());
                //将tembe移动到下一个节点
                temb = temb.next;
            }
        }

    }


    //  定一个heroNode，每一个heroNode对象就是一个节点
    static class HeroNode {
        public int no;
        public String name;
        public HeroNode next; // 指向下一个节点

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }


        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
