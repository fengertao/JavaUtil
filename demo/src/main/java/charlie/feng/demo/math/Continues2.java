package charlie.feng.demo.math;

import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 某班有43名同学，学号分别是1到43号。从其中任意挑选4名同学参加数学竞赛，其中至少有2名同学的学号相邻的概率是多少？
 * 公式解1: 费马法 C(43,4) - C (40, 4)
 * 公式解2：C(42,3)*3-C(41,2)*3+C(40,1)
 */
public class Continues2 {
    public static void main(String[] args) {
        long result = Generator.combination(IntStream.range(1, 44).boxed().collect(Collectors.toList())).simple(4).stream().filter(Continues2::containsContinues).count();
        System.out.println(result);
    }

    public static boolean containsContinues(List<Integer> numbers) {
        numbers.sort(Integer::compareTo);
        Integer[] nA = numbers.toArray(new Integer[0]);
        return nA[0] + 1 == nA[1] || nA[1] + 1 == nA[2] || nA[2] + 1 == nA[3];
    }
}
