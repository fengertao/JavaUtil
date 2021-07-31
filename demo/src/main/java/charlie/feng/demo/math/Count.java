package charlie.feng.demo.math;

import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用任意多个1，2，3，4组成五位数，其中没有33或者44的数有多少个
 */
public class Count {

    public static void main(String[] args) {
        List<List<Integer>> result = Generator.permutation(1,2,3,4)
                .withRepetitions(5)
                .stream()
                .filter(Count::isValidList)
                .toList();
        System.out.println(result.size());
    }

    public static boolean isValidList(List<Integer> iList) {
        int last = 0;
        for (Integer i : iList) {
            if ((i == 3) && (last == 3)) return false;
            if ((i == 4) && (last == 4)) return false;
            last = i;
        }
        return true;
    }
}
