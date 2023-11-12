package geometry.figures;

import lesson1.geometry.figures.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TriangleTests {

    @Test
    void calculateArea() {
        BigDecimal result = new BigDecimal(new Triangle(3, 3, 4).area(), new MathContext(4, RoundingMode.HALF_UP));
        Assertions.assertEquals(4.472, result.doubleValue());
    }

    @Test
    void calculatePerimeter() {
        Assertions.assertEquals(16, new Triangle(4, 5, 7).perimeter());
    }

    @Test
    void cannotCreateTriangeWithNegativeSide() {
        try {
            new Triangle(-3, 5, 7);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    void cannotCreateTriangleWithUncorrectSumOfTwoSides() {
        try {
            new Triangle(3, 4, 8);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }
}
