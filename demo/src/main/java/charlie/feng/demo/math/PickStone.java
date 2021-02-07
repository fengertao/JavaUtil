package charlie.feng.demo.math;

import java.util.Arrays;

public class PickStone {

    public static final int TRY_STONES = 2000;
    public static final int NUMBER_CHOICE = 3;
    public static final int MAX_STONE_PER_PICK = 30;

    public static void main(String[] args) {
        int[] choices = new int[NUMBER_CHOICE];
        for (choices[0] = 1; choices[0] <= MAX_STONE_PER_PICK; choices[0]++) {
            for (choices[1] = choices[0] + 1; choices[1] <= MAX_STONE_PER_PICK; choices[1]++) {
                for (choices[2] = choices[1] + 1; choices[2] <= MAX_STONE_PER_PICK; choices[2]++) {
                    int loopLength = calculateLoopLength(choices);
                    System.out.printf(" %2d %2d %2d: %3d\n", choices[0], choices[1], choices[2], loopLength);
                }
            }
        }
    }

    public static int calculateLoopLength(int[] choices) {
        // successfulZone[i] equals true means if current player left i stones, he will win the game
        Boolean[] successfulZone = new Boolean[TRY_STONES];
        for (int stoneLeft = 0; stoneLeft < TRY_STONES; stoneLeft++) {
            successfulZone[stoneLeft] = true;
            for (int choice : choices) {
                if ((stoneLeft - choice) >= 0) {
                    if (successfulZone[stoneLeft - choice]) {
                        successfulZone[stoneLeft] = false;
                        break;
                    }
                }
            }
        }
        /* Uncomment below lines for debug successful zone
        for (int i = 0 ; i< TRY_STONES; i++  ) {
            System.out.print(successfulZone[i] ? "W" : "L");
        }
        */
        return getLoopLength(successfulZone, choices);
    }

    /**
     * In a precalculated successfulZone, search a loop pattern and return length of loop.
     * Some Leading steps need to be skipped
     *
     * @param successfulZone precalculated successful zone
     * @param choices        choice array.
     * @return
     */
    public static int getLoopLength(Boolean[] successfulZone, int[] choices) {
        // We don't know how many leading steps to be skip, as least bigger than sum of steps * 5.
        int stepsToSkip = Arrays.stream(choices).reduce(Integer::sum).getAsInt() * 5;

        for (int testLoopLength = 1; testLoopLength <= TRY_STONES / 2; testLoopLength++) {
            boolean invalidLoop = false;
            for (int indexInLoop = stepsToSkip; indexInLoop < stepsToSkip + testLoopLength; indexInLoop++) {
                Boolean currentZone = successfulZone[indexInLoop];
                int currentLoop = 1;
                while (currentLoop * testLoopLength + indexInLoop < TRY_STONES) {
                    if (successfulZone[currentLoop * testLoopLength + indexInLoop] != currentZone) {
                        invalidLoop = true;
                        break;
                    }
                    currentLoop++;
                }
            }
            if (!invalidLoop) {
                return testLoopLength;
            }
        }
        throw new RuntimeException("Loop length not detected, please adjust parameter and retry");
    }
}