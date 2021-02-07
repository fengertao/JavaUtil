package charlie.feng.demo.math;

import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用任意多个1，2，3，4组成五位数，其中没有33或者44的数有多少个
 */
public class Count {

    public static void main(String[] args) {

        List<List<Integer>> result = Generator.combination(1, 2, 3, 4)
                .multi(5)
                .stream()
                .flatMap(combination -> Generator.permutation(combination)
                        .simple()
                        .stream()
                        .filter(Count::isValidList))
                .collect(Collectors.toList());
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

    public static void old(String[] args) {
        int count = 0;
        int[] iArray = new int[5];
        for (int i0 = 1; i0 <= 4; i0++) {
            iArray[0] = i0;
            for (int i1 = 1; i1 <= 4; i1++) {
                iArray[1] = i1;
                for (int i2 = 1; i2 <= 4; i2++) {
                    iArray[2] = i2;
                    for (int i3 = 1; i3 <= 4; i3++) {
                        iArray[3] = i3;
                        for (int i4 = 1; i4 <= 4; i4++) {
                            iArray[4] = i4;
                            if (isValidArray(iArray)) {
                                count++;
                                System.out.printf("%d  : %d %d %d %d %d\n", count, iArray[0], iArray[1], iArray[2], iArray[3], iArray[4]);
                            }
                        }

                    }
                }
            }
        }
    }

    public static boolean isValidArray(int[] iArray) {
        for (int i = 0; i <= 3; i++) {
            if (iArray[i] == 3 && iArray[i + 1] == 3)
                return false;
            if (iArray[i] == 4 && iArray[i + 1] == 4)
                return false;
        }
        return true;
    }

}
