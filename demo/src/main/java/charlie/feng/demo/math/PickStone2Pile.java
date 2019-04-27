package charlie.feng.demo.math;

import java.util.Arrays;

public class PickStone2Pile {

    public static final int TRY_STONES = 40;
    public static final int NUMBER_CHOICE = 3;
    public static final int MAX_STONE_PER_PICK = 30;

    public static void main(String[] args) {
//        int[] choices = new int[NUMBER_CHOICE];
//        for (choices[0] = 1; choices[0]<=MAX_STONE_PER_PICK; choices[0]++ ){
//            for (choices[1] = choices[0]+1; choices[1]<=MAX_STONE_PER_PICK; choices[1]++) {
//                for (choices[2] = choices[1]+1; choices[2]<=MAX_STONE_PER_PICK; choices[2]++) {
//                    int loopLength = calculateLoopLength(choices);
//                    System.out.printf(" %2d %2d %2d: %3d\n", choices[0], choices[1], choices[2], loopLength);
//                }
//            }
//        }
        int[] choices = new int[]{2, 5, 6};
        int loopLength = calculateLoopLength(choices);
    }

    public static int calculateLoopLength(int[] choices) {
        // if successfulZone[i] equals true, it means with so many stone left, next player will win the game.
        // if successfulZone[i] equals false, it means with so many stone left, next player will lose the game.
        Boolean[][] successfulZone = new Boolean[TRY_STONES][TRY_STONES];
        for (int stoneLeft1 = 0; stoneLeft1 < TRY_STONES; stoneLeft1++) {
            for (int stoneLeft2 = 0; stoneLeft2 < TRY_STONES; stoneLeft2++) {
                successfulZone[stoneLeft1][stoneLeft2] = true;
                for (int iChoice = 0; iChoice < choices.length; iChoice++) {
                    if (((stoneLeft1 - choices[iChoice]) >= 0) && (successfulZone[stoneLeft1 - choices[iChoice]][stoneLeft2] == true)) {
                        successfulZone[stoneLeft1][stoneLeft2] = false;
                        break;
                    }
                    if (((stoneLeft2 - choices[iChoice]) >= 0) && (successfulZone[stoneLeft1][stoneLeft2 - choices[iChoice]] == true)) {
                        successfulZone[stoneLeft1][stoneLeft2] = false;
                        break;
                    }
                }
            }
        }


        System.out.println( " 0123456789012345678901234567890123456789");
        for (int i = 0; i < TRY_STONES; i++) {
            System.out.print( i%10);
            for (int j = 0; j < TRY_STONES; j++) {
                System.out.print(successfulZone[i][j] ? "W" : "L");
            }
            System.out.println();
        }
        return 0;
    }
}