package charlie.feng.demo.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MathUtilTest {

    @Test
    void getAllFactors() {
        Assertions.assertEquals(List.of(1), MathUtil.getAllFactors(1));
        Assertions.assertEquals(List.of(1,2), MathUtil.getAllFactors(2));
        Assertions.assertEquals(List.of(1,11), MathUtil.getAllFactors(11));
        Assertions.assertEquals(List.of(1,3,9), MathUtil.getAllFactors(9));
        Assertions.assertEquals(List.of(1,2,4,8,16), MathUtil.getAllFactors(16));
        Assertions.assertEquals(List.of(1,2,5,10), MathUtil.getAllFactors(10));
        Assertions.assertEquals(List.of(1,2,3,4,6,8,12,24), MathUtil.getAllFactors(24));
    }

    @Test
    void getAllPrimeFactors() {
        Assertions.assertEquals(List.of(), MathUtil.getAllPrimeFactors(1,false));
        Assertions.assertEquals(List.of(2), MathUtil.getAllPrimeFactors(2,false));
        Assertions.assertEquals(List.of(11), MathUtil.getAllPrimeFactors(11,false));
        Assertions.assertEquals(List.of(3), MathUtil.getAllPrimeFactors(9,false));
        Assertions.assertEquals(List.of(2), MathUtil.getAllPrimeFactors(16,false));
        Assertions.assertEquals(List.of(2,5), MathUtil.getAllPrimeFactors(10,false));
        Assertions.assertEquals(List.of(2,3), MathUtil.getAllPrimeFactors(24,false));
        Assertions.assertEquals(List.of(1), MathUtil.getAllPrimeFactors(1,true));
        Assertions.assertEquals(List.of(1,2), MathUtil.getAllPrimeFactors(2,true));
        Assertions.assertEquals(List.of(1,11), MathUtil.getAllPrimeFactors(11,true));
        Assertions.assertEquals(List.of(1,3), MathUtil.getAllPrimeFactors(9,true));
        Assertions.assertEquals(List.of(1,2), MathUtil.getAllPrimeFactors(16,true));
        Assertions.assertEquals(List.of(1,2,5), MathUtil.getAllPrimeFactors(10,true));
        Assertions.assertEquals(List.of(1,2,3), MathUtil.getAllPrimeFactors(24,true));
    }

    @Test
    void toBase36() {
        Assertions.assertEquals('0', MathUtil.toBase36(0));
        Assertions.assertEquals('9', MathUtil.toBase36(9));
        Assertions.assertEquals('A', MathUtil.toBase36(10));
        Assertions.assertEquals('Z', MathUtil.toBase36(35));
        Assertions.assertEquals('?', MathUtil.toBase36(36));
        Assertions.assertEquals('-', MathUtil.toBase36(-1));
    }
}