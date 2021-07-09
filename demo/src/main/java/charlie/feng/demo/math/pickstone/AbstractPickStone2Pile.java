package charlie.feng.demo.math.pickstone;


import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;

/**
 * 取石子游戏。两选手轮流取石子
 * 假设：
 * 1. 不能取石子方为负
 * 2. 可以取子数量只和当前局面有关，和初始局面，取石子过程无关
 */
public abstract class AbstractPickStone2Pile {

    public static final int MAX_STONES = 60;
    /*
     * dp内 true表示后手必胜 (走完后进入本局面必胜)，false表示先手必胜
     */
    final boolean[][] dp = new boolean[MAX_STONES + 1][MAX_STONES + 1];


    /**
     * 构造函数
     * @param lastPickWin 如果取最后一手为胜，设lastPickWin为 true.如果取最后一手为负，设lastPickWin为 false.
     */
    public AbstractPickStone2Pile(boolean lastPickWin) {
        dp[0][0] = lastPickWin;
    }

    public void play() {

        for (int i = 1; i <= MAX_STONES; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = isWinPosition(i, j);
            }
        }
        printDp();
    }

    private void printDp() {
        System.out.print(" ");
        for (int i = 0; i <= MAX_STONES; i++) {
            System.out.print(i % 10);
        }

        for (int i = 0; i <= MAX_STONES; i++) {
            System.out.print("\n" + i % 10);
            for (int j = 0; j <= MAX_STONES; j++) {
                System.out.print(dp[i][j] ? "W" : "L");
            }
        }
    }

    public boolean isWinPosition(int i, int j) {
        List<ImmutablePair<Integer, Integer>> legalStones = getLegalMoves(i, j);
        for (ImmutablePair<Integer, Integer> pair : legalStones) {
            if ((i < pair.left) || (j < pair.right)) continue;
            if (dp[i - pair.left][j - pair.right]) {
                return false;
            }
        }
        dp[i][j] = true;
        dp[j][i] = true;
        return true;
    }

    abstract List<ImmutablePair<Integer, Integer>> getLegalMoves(int i, int j);
}

