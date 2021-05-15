package charlie.feng.demo.math;

import com.google.common.math.IntMath;

/**
 * a,b,c 最小公倍数20200, 则a,b,c的组合有多少种？（交换顺序重复算同一种）
 */
public class LCG {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,4,8,5,10,20,40,25,50,100,200,101,202,404,808,505,1010,2020,4040,2525,5050,10100,20200};
        int[] b = new int[]{1,2,4,8,5,10,20,40,25,50,100,200,101,202,404,808,505,1010,2020,4040,2525,5050,10100,20200};
        int[] c = new int[]{1,2,4,8,5,10,20,40,25,50,100,200,101,202,404,808,505,1010,2020,4040,2525,5050,10100,20200};
        int result = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < b.length; j++) {
                for (int k = j; k < c.length; k++) {
                    int lcg1 = a[i] * b[j] / IntMath.gcd(a[i], b[j]);
                    int lcg = lcg1 * c[k] / IntMath.gcd(lcg1, c[k]);
                    if (lcg == 20200) {
                        System.out.println(a[i] + " " + b[j] + " " + c[k]);
                        result++;
                    }
                }
            }

        }
        System.out.println("result is : " + result);
    }
}
