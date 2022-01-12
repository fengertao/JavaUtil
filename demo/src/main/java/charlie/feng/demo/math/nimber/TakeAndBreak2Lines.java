package charlie.feng.demo.math.nimber;

import java.util.ArrayList;
import java.util.List;

/**
 * 2xn的连续方格，每次可以涂黑1x1或者2x2，问1000内的先手输的n是哪些
 */
public class TakeAndBreak2Lines extends AbstractTakeAndBreak implements IPlayHookable {

    /*
    本题中，nim[n]指n长度的上下行都没有涂黑的连续长条
    nim[maxStone+1]=1，指上行涂黑下行留白或者上行涂白下行留黑的列，正好需要1步，nimber=1

    每个动作可以有5种情况：
    * 尽头拿1个，变成n-1长度的双行和1长度的单方格
    * 中间拿1个，变成i长度和n-i-1长度的双行和1长度的单方格
    * 尽头拿2个，变成n-2长度的双行
    * 中间拿2个，变成i长度和n-2-i长度的双行
    * 把单方格拿走
     */

    public static void main(String[] args) {
        new TakeAndBreak2Lines().play();
    }

    @Override
    public void onPrePlay() {
        //上行涂黑下行留白或者上行涂白下行留黑的列，1格石子只能1次拿走
        nim[MAX_STONES + 1] = 1;
    }

    @Override
    public void onPostPlay() {

    }

    @Override
    public List<List<Integer>> getLegalReminders(int n) {
        List<List<Integer>> stacks = new ArrayList<>();
        if (n == 1) {
            stacks.add(List.of(MAX_STONES + 1));
        } else if (n == 2) {
            stacks.add(List.of(1, MAX_STONES + 1));
            stacks.add(List.of(0));
        } else {
            stacks.add(List.of(n - 1, MAX_STONES + 1));
            stacks.add(List.of(n - 2));
            for (int i = 1; i <= n / 2; i++) {
                stacks.add(List.of(i, n - 1 - i, MAX_STONES + 1));
                stacks.add(List.of(i, n - 2 - i));
            }
        }
        return stacks;
    }

}
