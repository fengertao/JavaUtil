package charlie.feng.demo.math;

import com.google.common.math.LongMath;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 问题: 从A点到B点有n条横着的道路和n条纵着的道路。
 * 甲乙两人A点走到B点，如果两个人走过的路不交叉，一共有几种方案
 * <p>
 * 通项公式:
 * solutions = (C(2n-1, n)^2 - C(2n-1, n)*C(2n-2)*2)*2
 * <p>
 * n = 2    result = 6
 * n = 3    result = 40
 * n = 4    result = 350
 * n = 5    result = 3528
 * n = 6    result = 38808
 * n = 7    result = 453024
 * n = 8    result = 5521230
 * n = 9    result = 69526600
 */
public class CountTwoPath {

    public static final int n = 9;
    public static long solutions = 0;

    public static void main(String[] args) {
        arrangeRemainingPath(new int[n], 0, CountTwoPath::arrangeRemainingPathForB);
        validateSolutions();
    }

    public static void arrangeRemainingPath(int[] vt, int i, Consumer<int[]> callback) {
        if (i == n) {
            callback.accept(vt);
            return;
        }

        for (vt[i] = (i == 0 ? 0 : vt[i - 1] + 1); vt[i] <= n + i; vt[i]++) {
            arrangeRemainingPath(vt.clone(), i + 1, callback);
        }
    }

    public static void arrangeRemainingPathForB(int[] vtA) {
        arrangeRemainingPathForB(vtA, new int[n], 0, CountTwoPath::validatePath);
    }

    public static void arrangeRemainingPathForB(int[] vtA, int[] vtB, int i, BiConsumer<int[], int[]> callback) {
        if (i == n) {
            callback.accept(vtA, vtB);
            return;
        }

        for (vtB[i] = (i == 0 ? 0 : vtB[i - 1] + 1); vtB[i] <= n + i; vtB[i]++) {
            arrangeRemainingPathForB(vtA, vtB.clone(), i + 1, callback);
        }
    }

    public static void validatePath(int[] vtA, int[] vtB) {

        if ((0 < vtA[0]) || (vtB[n - 1] < n * 2 - 1)) {
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            if (vtB[i] < vtA[i + 1]) {
                return;
            }
        }
        solutions++;

/*For Debug
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(vtA[i] + " ");
        }
        sb.append("  ");
        for (int i = 0; i < n; i++) {
            sb.append(vtB[i] + " ");
        }
        System.out.println(sb.toString());
*/
    }

    private static void validateSolutions() {
        long i1 = LongMath.factorial(n * 2 - 1) / LongMath.factorial(n - 1) / LongMath.factorial(n);
        long i2 = LongMath.factorial(n * 2 - 2) / LongMath.factorial(n - 2) / LongMath.factorial(n);
        long expectedSolutions = i1 * (i1 - 2 * i2) * 2;
        if (expectedSolutions != solutions * 2) {
            System.err.println("Error: Expected solution not match with Simulated solution");
            System.err.println("Expected  Solutions : " + expectedSolutions);
            System.err.println("Simulated Solutions : " + solutions * 2);
        } else {
            System.out.println("Expected  Solutions : " + expectedSolutions);
            System.out.println("Simulated Solutions : " + solutions * 2);
        }
    }


}
