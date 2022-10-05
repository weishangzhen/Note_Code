package Day04.Debug;

/**
 * @author Shangzhen Wei
 * @version 1.0
 */
public class Demo01 {
    public static void main(String[] args) {
        // Debug 逐行执行
        int num = 0;
        for (int i = 0; i < 5; i++) {
            num += i;
            System.out.println("i = " + i);
            System.out.println("num = " + num);
        }
        System.out.println("Exit");
    }
}
