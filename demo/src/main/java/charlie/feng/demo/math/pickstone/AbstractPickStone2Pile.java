package charlie.feng.demo.math.pickstone;


import java.util.List;

/**
 * 取石子游戏。两选手轮流取石子
 * 假设：
 * 1. 不能取石子方为负
 * 2. 可以取子数量只和当前局面有关，和初始局面，取石子过程无关
 */
public abstract class AbstractPickStone2Pile {

    public static final int MAX_STONES = 60;
    final boolean[][] dp = new boolean[MAX_STONES + 1][MAX_STONES + 1];

    public void play() {
        /*
         * true表示后手必胜 (走完后进入本局面必胜)，false表示先手必胜
         */
        dp[0][0] = true;
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
        List<Integer> stones0 = getLegalStones(i, j, 0);
        for (Integer num : stones0) {
            if (i < num) continue;

            if (dp[i - num][j]) {
                return false;
            }
        }
        List<Integer> stones1 = getLegalStones(i, j, 1);
        for (Integer num : stones1) {
            if (j < num) continue;

            if (dp[i][j - num]) {
                return false;
            }
        }
        dp[i][j] = true;
        dp[j][i] = true;
        return true;
    }


    abstract List<Integer> getLegalStones(int i, int j, int pileIndex);
}

