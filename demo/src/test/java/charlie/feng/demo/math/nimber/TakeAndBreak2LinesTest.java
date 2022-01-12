package charlie.feng.demo.math.nimber;

import charlie.feng.demo.math.MathUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TakeAndBreak2LinesTest {

    @Test
    void play() {
        TakeAndBreak2Lines game = new TakeAndBreak2Lines(){
            protected void printNim() {
            }
        };
        AbstractPickStone.maxStones = 50;
        game.play();
        String nimStr = Arrays.stream(game.nim).limit(game.maxStones+1).mapToObj(i ->  MathUtil.toBase36(i)).reduce("", (str, c) -> str + c , (a,b) -> a+b);
        Assertions.assertEquals("002214331426502714331477502844631877502214631827502", nimStr);
    }
}