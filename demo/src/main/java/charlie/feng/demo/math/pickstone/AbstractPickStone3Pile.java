package charlie.feng.demo.math.pickstone;


import java.util.List;

/**
 * 取石子游戏。两选手轮流取石子
 * 假设：
 * 1. 不能取石子方为负
 * 2. 可以取子数量只和当前局面有关，和初始局面，取石子过程无关
 */
public abstract class AbstractPickStone3Pile {

    public static final int MAX_STONES = 32;
    /*
     * true表示后手必胜 (走完后进入本局面必胜)，false表示先手必胜
     */    final boolean[][][] dp = new boolean[MAX_STONES + 1][MAX_STONES + 1][MAX_STONES + 1];

    /**
     * 构造函数
     * @param lastPickWin 如果取最后一手为胜，设lastPickWin为 true.如果取最后一手为负，设lastPickWin为 false.
     */
    public AbstractPickStone3Pile(boolean lastPickWin) {
        dp[0][0][0] = lastPickWin;
    }

    public void play() {
        for (int i = 1; i <= MAX_STONES; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j][k] = isWinPosition(i, j, k);
                }
            }
        }
        printDp();
    }

    private void printDp() {
        for (int k = 0; k <= MAX_STONES ; k++) {
            System.out.print("\n\nLayer " + k  + "\n ");

            for (int i = 0; i <= MAX_STONES; i++) {
                System.out.print(i % 10);
            }

            for (int i = 0; i <= MAX_STONES; i++) {
                System.out.print("\n" + i % 10);
                for (int j = 0; j <= MAX_STONES; j++) {
                    System.out.print(dp[i][j][k] ? "W" : "L");
                }
            }
        }

    }

    public boolean isWinPosition(int i, int j, int k) {
        List<Integer> stones0 = getLegalStones(i, j, k, 0);
        for (Integer num : stones0) {
            if (i < num) continue;
            if (dp[i - num][j][k]) {
                return false;
            }
        }
        List<Integer> stones1 = getLegalStones(i, j, k, 1);
        for (Integer num : stones1) {
            if (j < num) continue;
            if (dp[i][j - num][k]) {
                return false;
            }
        }
        List<Integer> stones2 = getLegalStones(i, j, k, 2);
        for (Integer num : stones2) {
            if (k < num) continue;
            if (dp[i][j][k - num]) {
                return false;
            }
        }
        dp[i][j][k] = dp[i][k][j] = dp[j][i][k] = dp[j][k][i] = dp[k][i][j] = dp[k][j][i] = true;
        return true;
    }

    abstract List<Integer> getLegalStones(int i, int j, int k, int pileIndex);
}

