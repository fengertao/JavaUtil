package charlie.feng.demo.math;

import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.stream.Stream;

enum Operator {
    ADD("+"),
    MINUS("-"),
    TIME("*"),
    DIVIDE("/"),
    MINUSR("--"),
    DIVIDER("//");

    public final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public static double calculate(Operator op, double d1, double d2) {
        return switch (op) {
            case ADD -> d1 + d2;
            case MINUS -> d1 - d2;
            case TIME -> d1 * d2;
            case DIVIDE -> d1 / d2;
            case MINUSR -> d2 - d1;
            case DIVIDER -> d2 / d1;
        };
    }

    public String getFormula(String origin, Operator operator, Integer operands) {
        return switch (operator) {
            case ADD -> origin + " + " + operands;
            case MINUS -> origin + " - " + operands;
            case TIME -> (origin.length() > 1 ? "( " + origin + " )" : origin) + " * " + operands;
            case DIVIDE -> origin + " / " + operands;
            case MINUSR -> operands + " - " + (origin.length() > 1 ? "( " + origin + " )" : origin);
            case DIVIDER -> operands + " / " + (origin.length() > 1 ? "( " + origin + " )" : origin);
        };
    }

    public String toString() {
        return this.symbol;
    }
}

public class Calculate24 {

    public static final int EXPECTED = 24;

    public static void main(String[] args) {
        Integer[] input = new Integer[]{3, 4, 6, 8};
        long count = Generator.permutation(input).simple().stream().flatMap(Calculate24::canGetResult).count();
        System.out.println("Number Of Resolutions: " + count);
    }

    public static Stream<List<Operator>> canGetResult(List<Integer> operands) {
        return Generator.permutation(Operator.values()).withRepetitions(3).stream()
                .filter(operators -> canGetResult(operands, operators));
    }

    public static boolean canGetResult(List<Integer> operands, List<Operator> operators) {
        double result = operands.get(0).doubleValue();
        result = Operator.calculate(operators.get(0), result, operands.get(1));
        result = Operator.calculate(operators.get(1), result, operands.get(2));
        result = Operator.calculate(operators.get(2), result, operands.get(3));
        if (Math.abs(result - EXPECTED) < 0.0001) {
            System.out.println(getFormula(operands, operators));
            return true;
        } else {
            return false;
        }
    }

    public static String getFormula(List<Integer> operands, List<Operator> operators) {
        String str = String.valueOf(operands.get(0));
        for (int i = 0; i <= 2; i++) {
            str = operators.get(i).getFormula(str, operators.get(i), operands.get(i + 1));
        }
        return str + " = " + EXPECTED;
    }
}
