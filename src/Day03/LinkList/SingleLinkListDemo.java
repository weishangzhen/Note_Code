package Day03.LinkList;

import java.util.Stack;

public class SingleLinkListDemo {
    public static void main(String[] args) {
        // 测试
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 顺序加
        /*
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        */
        // 乱序加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);

        singleLinkedList.showList();
        System.out.println();

        // 修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "卢宝", "小麒麟");
        HeroNode newHeroNode2 = new HeroNode(6, "bilibili", "B 站");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.showList();
        System.out.println();
        singleLinkedList.update(newHeroNode2);
        singleLinkedList.showList();

        // 删除节点的代码
        singleLinkedList.deleteNode(newHeroNode);
        System.out.println("删除后的情况");
        singleLinkedList.showList();

        // 求单链表的节点个数
        System.out.println(getLength(singleLinkedList.getHead()));

        // 倒数第K个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 2);
        System.out.println("res = " + res);

        // System.out.println("反转链表");
        // reverseList(singleLinkedList.getHead());
        // singleLinkedList.showList();

        System.out.println("反向打印");
        reversePrint(singleLinkedList.getHead());
        singleLinkedList.showList();

    }

    // 面试题
    // 栈 逆序打印 单链表 先进后出 的 特点
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        // 创建一个栈，将结点压入栈中
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        // 压栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 打印栈中的结点
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    // 面试题
    // 将单链表反转
    public static void reverseList(HeroNode head) {
        // 当链表为空 或者 只有 一个结点时 无需反转
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 定义一个辅助的指针，来遍历原先的链表
        HeroNode cur = head.next;
        HeroNode next = null; // 用来指向当前结点【cur】的下一个结点
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原来的链表，每遍历一个结点就将其取出，并放在reverseHead的头部
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    // 面试题
    // 获取单链表的节点的个数（如果是带头节点的链表，该需求不统计头节点）
    public static int getLength(HeroNode head) { // 传进来头节点
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    // 查找单链表中的倒数第k个结点
    // 思路
    // 1. 写方法 接收head结点，同时接收index作为获取倒数第K个结点的手段
    // 2. 先遍历链表，获取链表的长度 getLength
    // 3. 得到 length之后， 从链表的第一个开始遍历 （length - index）个得到
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int length = getLength(head);
        // 校验
        if (index <= 0 || index > length) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < length - index; i++) {
            temp = temp.next;
        }
        return temp;
    }
}

// 定义SingleLinkedList 管理英雄
class SingleLinkedList {

    // 初始化 头节点
    private HeroNode head = new HeroNode(0, "", "");
    // 添加节点到单向链表
    // 思路
    // 1. 找到当前链表的最后节点
    // 2. 将最后节点的Next 指向 新的节点
    public HeroNode getHead() {
        return head;
    }
    public void setHead(HeroNode head) {
        this.head = head;
    }
    public void add(HeroNode hero) {
        // 在head 节点不能移动的情况下，加入一个 辅助指针
        HeroNode temp = head;
        // 遍历链表，找到最后的next
        while (true) {
            // 找链表的最后
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = hero;
    }
    // 第二种方式添加英雄， 根据排名将英雄插入到指定位置，辅助指针，单向链表 添加新节点于temp之后
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false; //表示添加的编号是否已经存在，默认不存在
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.num > heroNode.num) { // 位置找到
                break;
            } else if (temp.next.num == heroNode.num) {
                flag = true;
                break;
            }
            temp = temp.next; //遍历
        }
        // 判断flag 的值 来确定是否能插入
        if (flag) {
            System.out.println("准备插入的英雄编号 " + heroNode.num + "已经存在, 不能添加");
        } else {
            // 不存在，可以插入
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
    // 修改节点的信息
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
        }
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到了该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.num == newHeroNode.num) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.println("未找到，不能修改");
        }
    }
    // 删除节点信息
    public void deleteNode(HeroNode deleteHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
        }
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到了该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.next.num == deleteHeroNode.num) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("未找到，不能删除");
        }
    }
    // 显示链表
    public void showList() {
        // 1. 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
// 首先定义HeroNode 每个HeroNode对象就是在链表里的一个节点
class HeroNode {
    public int num;
    public String name;
    public String nickName;
    public HeroNode next; // 指向下一个域

    public HeroNode(int heroNum, String heroName, String heroNickName) {
        this.num = heroNum;
        this.name = heroName;
        this.nickName = heroNickName;
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

