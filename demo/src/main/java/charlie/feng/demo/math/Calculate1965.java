package charlie.feng.demo.math;

import org.paukov.combinatorics3.Generator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

enum Operator2 {
    ADD("+"),
    MINUS("-"),
    TIME("*"),
    DIVIDE("/"),
    POWER("^");

    public final String symbol;

    Operator2(String symbol) {
        this.symbol = symbol;
    }

    public static double calculate(Operator2 op, Object d1, Object d2) {
        return switch (op) {
            case ADD -> toDouble(d1) + toDouble(d2);
            case MINUS -> toDouble(d1) - toDouble(d2);
            case TIME -> toDouble(d1) * toDouble(d2);
            case DIVIDE -> toDouble(d1) / toDouble(d2);
            case POWER -> Math.pow(toDouble(d1), toDouble(d2));
        };
    }

    public String getFormula(String origin, Object operands) {
        return switch (this) {
            case ADD -> " ( " + origin + " + " + operands + " ) ";
            case MINUS -> " ( " + origin + " - " + operands + " ) ";
            case TIME -> "( " + origin + " * " + operands + " ) ";
            case DIVIDE -> "( " + origin + " / " + operands + " ) ";
            case POWER -> "( " + origin + " ^ " + operands + " ) ";
        };
    }

    public String toString() {
        return this.symbol;
    }

    public static double toDouble(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        } else {
            return (Double) obj;
        }
    }
}

/**
 * 用1，9，6，5四个数字算1到100.顺序不能变。
 * 可以用
 * * 加减乘除
 * * 负号
 * * 根号
 * * 阶乘
 * * 无限循环小数符号
 * * 幂
 * * 两个数字连接 (不允许两个表达式连接)
 * * 任意多个括号
 */
public class Calculate1965 {

    public static final Map<Integer, String> resultMap = new TreeMap<>();

    public static void main(String[] args) {

        List<Object> order = List.of(123, 132, 213, 231, 321); //312 is identical with 132, so ignore
        List<Object> num1 = List.of(1, -1, 1.0 / 9, -1.0 / 9);
        List<Object> num2 = List.of(9, -9, 3, -3, 6, -6, 720, -720, 1, -1);
        List<Object> num3 = List.of(6, -6, 720, -720, 6.0 / 9, -6.0 / 9);
        List<Object> num4 = List.of(5, -5, 120, -120, 5.0 / 9, -5.0 / 9);
        List<List<Object>> nums = List.of(num1, num2, num3, num4, order);

        Generator.cartesianProduct(nums).stream().forEach(numbers -> Generator.permutation(Operator2.values()).withRepetitions(3).stream().forEach(operators -> calculate3(numbers, operators)));

        order = List.of(12, 21);
        num1 = List.of(19, -19);
        num2 = List.of(6, -6, 720, -720, 6.0 / 9, -6.0 / 9);
        num3 = List.of(5, -5, 120, -120, 5.0 / 9, -5.0 / 9);
        nums = List.of(num1, num2, num3, order);
        Generator.cartesianProduct(nums).stream().forEach(numbers -> Generator.permutation(Operator2.values()).withRepetitions(2).stream().forEach(operators -> calculate2(numbers, operators)));

        num1 = List.of(1, -1, 1.0 / 9, -1.0 / 9);
        num2 = List.of(96, -96);
        num3 = List.of(5, -5, 120, -120, 5.0 / 9, -5.0 / 9);
        nums = List.of(num1, num2, num3, order);
        Generator.cartesianProduct(nums).stream().forEach(numbers -> Generator.permutation(Operator2.values()).withRepetitions(2).stream().forEach(operators -> calculate2(numbers, operators)));

        num1 = List.of(1, -1, 1.0 / 9, -1.0 / 9);
        num2 = List.of(9, -9, 3, -3, 6, -6, 720, -720, 1, -1);
        num3 = List.of(65, -65);
        nums = List.of(num1, num2, num3, order);
        Generator.cartesianProduct(nums).stream().forEach(numbers -> Generator.permutation(Operator2.values()).withRepetitions(2).stream().forEach(operators -> calculate2(numbers, operators)));

        resultMap.forEach((key, value) -> System.out.println(key + "  : " + value));
    }

    public static void calculate3(List<Object> operands, List<Operator2> operators) {

        Integer orderInt = (Integer) operands.get(operators.size() + 1);
        double result = 0;
        double resultTemp;
        switch (orderInt) {
            case 123 -> {
                result = toDouble(operands.get(0));
                result = Operator2.calculate(operators.get(0), result, operands.get(1));
                result = Operator2.calculate(operators.get(1), result, operands.get(2));
                result = Operator2.calculate(operators.get(2), result, operands.get(3));
            }
            case 132 -> {
                result = toDouble(operands.get(0));
                result = Operator2.calculate(operators.get(0), result, operands.get(1));
                resultTemp = toDouble(operands.get(2));
                resultTemp = Operator2.calculate(operators.get(2), resultTemp, operands.get(3));
                result = Operator2.calculate(operators.get(1), result, resultTemp);
            }
            case 213 -> {
                result = toDouble(operands.get(1));
                result = Operator2.calculate(operators.get(1), result, operands.get(2));
                result = Operator2.calculate(operators.get(0), operands.get(0), result);
                result = Operator2.calculate(operators.get(2), result, operands.get(3));
            }
            case 231 -> {
                result = toDouble(operands.get(1));
                result = Operator2.calculate(operators.get(1), result, operands.get(2));
                result = Operator2.calculate(operators.get(2), result, operands.get(3));
                result = Operator2.calculate(operators.get(0), operands.get(0), result);
            }
            case 321 -> {
                result = toDouble(operands.get(2));
                result = Operator2.calculate(operators.get(2), result, operands.get(3));
                result = Operator2.calculate(operators.get(1), operands.get(1), result);
                result = Operator2.calculate(operators.get(0), operands.get(0), result);
            }
            default -> {
                // do nothing, 312 will be identical with 132 so skip
            }
        }

        pushResult(operands, operators, result);
    }

    public static void calculate2(List<Object> operands, List<Operator2> operators) {

        int orderInt = (int) operands.get(operators.size() + 1);
        double result = 0;
        switch (orderInt) {
            case 12 -> {
                result = toDouble(operands.get(0));
                result = Operator2.calculate(operators.get(0), result, operands.get(1));
                result = Operator2.calculate(operators.get(1), result, operands.get(2));
            }
            case 21 -> {
                result = toDouble(operands.get(1));
                result = Operator2.calculate(operators.get(1), result, operands.get(2));
                result = Operator2.calculate(operators.get(0), operands.get(0), result);
            }
            default -> {
                // do nothing
            }
        }

        pushResult(operands, operators, result);
    }

    private static void pushResult(List<Object> operands, List<Operator2> operators, double result) {
        long resultInt = Math.round(result);
        if (-100 <= resultInt && resultInt <= 100 && (Math.abs(result - resultInt) < 0.00000000001)) {
            int resultAbs =(int) Math.abs(resultInt);
            if (resultMap.get(resultAbs) == null) {
                resultMap.put(resultAbs, operators.size() == 3 ? getFormula3(operands, operators) : getFormula2(operands, operators));
            }

        }
    }

    public static String getFormula3(List<Object> operands, List<Operator2> operators) {

        int orderInt = (int) operands.get(operators.size() + 1);
        String formula = "";
        String formulaTemp;
        switch (orderInt) {
            case 123 -> {
                formula = String.valueOf(operands.get(0));
                formula = operators.get(0).getFormula(formula, operands.get(1));
                formula = operators.get(1).getFormula(formula, operands.get(2));
                formula = operators.get(2).getFormula(formula, operands.get(3));
            }
            case 132 -> {
                formula = String.valueOf(operands.get(0));
                formula = operators.get(0).getFormula(formula, operands.get(1));
                formulaTemp = String.valueOf(operands.get(2));
                formulaTemp = operators.get(2).getFormula(formulaTemp, operands.get(3));
                formula = operators.get(1).getFormula(formula, formulaTemp);
            }
            case 213 -> {
                formula = String.valueOf(operands.get(1));
                formula = operators.get(1).getFormula(formula, operands.get(2));
                formula = operators.get(0).getFormula(String.valueOf(operands.get(0)), formula);
                formula = operators.get(2).getFormula(formula, operands.get(3));
            }
            case 231 -> {
                formula = String.valueOf(operands.get(1));
                formula = operators.get(1).getFormula(formula, operands.get(2));
                formula = operators.get(2).getFormula(formula, operands.get(3));
                formula = operators.get(0).getFormula(String.valueOf(operands.get(0)), formula);
            }
            case 321 -> {
                formula = String.valueOf(operands.get(2));
                formula = operators.get(2).getFormula(formula, operands.get(3));
                formula = operators.get(1).getFormula(String.valueOf(operands.get(1)), formula);
                formula = operators.get(0).getFormula(String.valueOf(operands.get(0)), formula);
            }
            default -> {
            }
        }

        return formula;
    }

    public static String getFormula2(List<Object> operands, List<Operator2> operators) {

        int orderInt = (int) operands.get(operators.size() + 1);
        String formula = "";
        switch (orderInt) {
            case 12 -> {
                formula = String.valueOf(operands.get(0));
                formula = operators.get(0).getFormula(formula, operands.get(1));
                formula = operators.get(1).getFormula(formula, operands.get(2));
            }
            case 21 -> {
                formula = String.valueOf(operands.get(1));
                formula = operators.get(1).getFormula(formula, operands.get(2));
                formula = operators.get(0).getFormula(String.valueOf(operands.get(0)), formula);
            }
            default -> {
            }
        }
        return formula;
    }

    public static double toDouble(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        } else {
            return (Double) obj;
        }
    }
}
