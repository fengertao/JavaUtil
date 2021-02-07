package charlie.feng.demo.math;

import org.paukov.combinatorics3.Generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 题目：幸运球队列
 * 有4个红球，2个绿球，5个蓝球，在桌上排列。如果排列从左到右红绿蓝球连续出现，称为幸运排列
 * 请问这样的幸运排列一共有多少种?
 * 答案：((4*2*5)*9!-(4*2*5)*(3*1*4)*7!/2)/(4!*2!*5!) = 2310
 */
public class NumberOfLuckyBallSeq {
    public static void main(String[] args) {
        Map<String, Integer> ballMap = Map.of("r", 4, "g", 2, "b", 5);

        List<String> balls = ballMap.entrySet()
                .stream()
                .collect(ArrayList::new, (list, ele) -> list.addAll(Collections.nCopies(ele.getValue(), ele.getKey())), List::addAll);
        List<List<String>> result = Generator.permutation(balls)
                .simple()
                .stream()
                .filter(NumberOfLuckyBallSeq::isLuckSeq)
                .collect(Collectors.toList());
        System.out.println(result.size());
    }

    static boolean isLuckSeq(List<String> seq) {
        return String.join("", seq).contains("rgb");
    }
}