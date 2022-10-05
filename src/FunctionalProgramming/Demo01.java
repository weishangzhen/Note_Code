package FunctionalProgramming;

import javax.print.attribute.standard.PrinterName;
import java.util.function.IntPredicate;

/**
 * @author Shangzhen Wei
 * @version 1.0
 */

public class Demo01 {

    public static void main(String[] args) {
        // 使用匿名内部类
        printNum(value -> value %2 == 0);
        System.out.println("---------------");
        // Lambda
        printNum(value -> value %2 == 0);
        System.out.println("---------------");
        printNum((int value) -> {
            return value % 2 == 0;
        });
    }

    public static void printNum(IntPredicate predicate) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for(int i : arr) {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }
}
