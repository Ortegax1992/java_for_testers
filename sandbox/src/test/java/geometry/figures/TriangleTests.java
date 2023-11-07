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
        var t = new Triangle(3, 3, 4);
        MathContext context = new MathContext(4, RoundingMode.HALF_UP);
        BigDecimal result = new BigDecimal(t.area(), context);
        Assertions.assertEquals(4.472, result.doubleValue());
    }

    @Test
    void calculatePerimeter() {
        Assertions.assertEquals(16, new Triangle().perimeter(4, 5, 7));
    }

    @Test
    void calculateSemiperimeter(){
        Assertions.assertEquals(12,new Triangle().semiperimeter(6,8,10));
    }
}
