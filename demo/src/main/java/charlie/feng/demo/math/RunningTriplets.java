package charlie.feng.demo.math;

import java.util.Arrays;

/**
 * 有九个队参加跑步比赛，每个队有三个队员，每个队的选手都穿着有同样数字的制服。他们通过终点的方式是每一个队的第二个队员，他要和他的另外
 * 两个队友要相隔他们队的制服的数字相同数目的队员。如下图所示。数字二队的第二个完成比赛的选手，他与他两边的队友的完成顺序都被两个其他队
 * 的选手隔开了。（注意图示位置并不是数字二队的队员完成时的必须的位置）。制服是其他数字的队的的完成顺序也符合同样规律。假设一队的一个队
 * 员赢得了比赛，你能够指出他们所有人通过终点的完成顺序吗？这个难题是至少有一种解决方法吗？（没有两个或更多的参赛选手是同时通过终点。）
 */
public class RunningTriplets {

    public static void main(String[] args) {
        int[] filled = new int[27];
        filled[0] = filled[2] = filled[4] = 1;
        tryFill(filled, 9);
    }

    private static void tryFill(int[] filled, int d) {
        if (d == 1) {
            System.out.println("Result : " + Arrays.toString(filled));
        }

        int lastPossiblePosition = 27 - 2 * d - 3;
        for (int j = 1; j <= lastPossiblePosition; j++) {
            if (filled[j] == 0 && filled[j + d + 1] == 0 && filled[j + 2 * d + 2] == 0) {
                int[] newFilled = filled.clone();
                newFilled[j] = newFilled[j + d + 1] = newFilled[j + 2 * d + 2] = d;
                tryFill(newFilled, d - 1);
            }
        }
    }

}
