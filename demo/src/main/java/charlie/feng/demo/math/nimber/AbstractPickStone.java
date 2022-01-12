package charlie.feng.demo.math.nimber;

import charlie.feng.demo.math.MathUtil;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * 取石子游戏。两选手轮流取石子
 * 假设：
 * 1. 不能取石子方为负
 * 2. 可以取子数量只和当前局面有关，和初始局面，取石子过程无关
 */
public abstract class AbstractPickStone {

    public static int maxStones = 200;
    /*
     * 数组里的值为nimber，值为0时表示P position (previous player win).
     *
     * 数组0下标表示一堆全部取完的nimber.
     * 数组1-max stone下标表示还剩1到n个石子时的nimber
     * 数组max stone以上下标，给特殊规则的游戏备用。游戏应该注释该用途。
     */
    public final int[] nim = new int[maxStones * 2];

    public void play() {
        nim[0] = 0;
        if (this instanceof IPlayHookable) {
            ((IPlayHookable) this).onPrePlay();
        }
        for (int i = 1; i <= maxStones; i++) {
            nim[i] = calculateNim(i);
        }
        if (this instanceof IPlayHookable) {
            ((IPlayHookable) this).onPostPlay();
        }
        printNim();
    }

    protected void printNim() {
        System.out.println(IntStream.range(0, maxStones+1).mapToObj(i -> i%10).reduce("", (str, c) -> str + c , (a, b) -> a+b));
        System.out.println(Arrays.stream(nim).limit(maxStones+1).mapToObj(i ->  MathUtil.toBase36(i)).reduce("", (str, c) -> str + c , (a, b) -> a+b));
    }

    public int mex(Set<Integer> candidateNims) {
        return candidateNims.stream().reduce(0, (mex, next) -> {
            if (next.equals(mex)) {
                return mex + 1;
            } else {
                return mex;
            }
        });
    }

    abstract int calculateNim(int i);

}

