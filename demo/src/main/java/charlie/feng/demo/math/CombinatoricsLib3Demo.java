package charlie.feng.demo.math;

import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.PermutationGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CombinatoricsLib3Demo {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(CombinatoricsLib3Demo.class);

        logger.info("五选三组合,不可重复，应该输出C(5,3)=10个结果");
        Generator.combination("1", "2", "3", "4", "5")
                .simple(3)
                .stream()
                .forEach(str -> logger.info(str.toString()));

        logger.info("四选三组合,可以重复，应该输出4+C(4,2)+C(4,3)=20个结果");
        Generator.combination("1", "2", "3", "4")
                .multi(4)
                .stream()
                .forEach(str -> logger.info(str.toString()));

        logger.info("三选三全排列,原始输入无重复，应该输出3!=6个结果");
        Generator.permutation("1", "2", "3")
                .simple()
                .forEach(str -> logger.info(str.toString()));

        logger.info("三选三全排列,原始输入有重复，应该输出3!/2!=3个结果");
        Generator.permutation("1", "2", "2")
                .simple()
                .forEach(str -> logger.info(str.toString()));

        logger.info("三选三全排列,原始输入2有重复，但两个2视为不一样的2，应该输出3!=6个结果");
        Generator.permutation("1", "2", "2")
                .simple(PermutationGenerator.TreatDuplicatesAs.IDENTICAL)
                .forEach(str -> logger.info(str.toString()));

        logger.info("四选四全排列,原始输入有重复，应该输出4!/(2!*2!)=6个结果");
        Generator.permutation("1", "1", "2", "2")
                .simple()
                .stream()
                .forEach(str -> logger.info(str.toString()));

        logger.info("四选二排列,原始输入无重复，应该输出P(4,2)=12个结果");
        //实现原理：先四选二组合，再对每个组合进行全排列。
        List<List<String>> kPermutationResult = Generator.combination("1", "2", "3", "4")
                .simple(2)
                .stream()
                .flatMap(combination -> Generator.permutation(combination)
                        .simple()
                        .stream()
                )
                .collect(Collectors.toList());
        logger.info(kPermutationResult.toString());

        logger.info("四选二排列,原始输入无重复，应该输出P(4,2)=12个结果");
        Generator.combination("1", "2", "3", "4")
                .simple(2)
                .stream()
                .forEach(combination -> Generator.permutation(combination)
                        .simple()
                        .forEach(str -> logger.info(str.toString())));

        logger.info("两个原始输入，输出长度3的结果，允许重复使用原始输入，应该输出2^=8个结果");
        Generator.permutation("1", "2")
                .withRepetitions(3)
                .stream()
                .forEach(str -> logger.info(str.toString()));

        logger.info("3个元素的子集，应该有8个元素");
        Generator.subset("1", "2", "3")
                .simple()
                .stream()
                .forEach(str -> logger.info(str.toString()));

        logger.info("整数5有7种分解形式");
        Generator.partition(5)
                .stream()
                .forEach(str -> logger.info(str.toString()));

        logger.info("数组的笛卡尔积");
        Generator.cartesianProduct(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6))
                .stream()
                .forEach(str -> logger.info(str.toString()));
    }


}
