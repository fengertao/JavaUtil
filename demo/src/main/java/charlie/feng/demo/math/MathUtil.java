package charlie.feng.demo.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MathUtil {

    /**
     * get all factors of n, include 1 and n
     *
     * @param n integer to be factored
     * @return factors of n,sorted from small to big
     */
    public static List<Integer> getAllFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        int upperLimit = (int) (Math.sqrt(n));
        for (int i = 1; i <= upperLimit; i += 1) {
            if (n % i == 0) {
                factors.add(i);
                if (i != n / i) {
                    factors.add(n / i);
                }
            }
        }
        Collections.sort(factors);
        return factors;
    }

    private static void addFactorIfPrime(List<Integer> factors, int n) {
        if (getAllFactors(n).size() == 2) {
            factors.add(n);
        }
    }

    /**
     * Get all prime factors of n, optional include "1"
     *
     * @param n        integer to be factored
     * @param include1 whether include "1" in the result
     * @return prime factors of n,sorted from small to big
     */

    public static List<Integer> getAllPrimeFactors(int n, boolean include1) {
        List<Integer> factors = new ArrayList<>();
        int upperLimit = (int) (Math.sqrt(n));
        for (int i = 2; i <= upperLimit; i += 1) {
            if (n % i == 0) {
                addFactorIfPrime(factors, i);
                if (i != n / i) {
                    addFactorIfPrime(factors, n / i);
                }
            }
        }
        if (factors.size() == 0) {
            addFactorIfPrime(factors, n);
        }
        if (include1) {
            factors.add(1);
        }

        Collections.sort(factors);
        return factors;
    }

    /**
     * Encode integer n to base 36.
     * which is 0,1,...9,A,B,...,Z
     *
     * @param n integer to be encoded
     * @return base 36 encoded char.
     */
    public static char toBase36(int n) {
        if (n < 0) {
            return '-';
        } else if (n < 10) {
            return (char) (n + '0');
        } else if (n < 36) {
            return (char) (n - 10 + 'A');
        } else {
            return '?';
        }
    }
}
