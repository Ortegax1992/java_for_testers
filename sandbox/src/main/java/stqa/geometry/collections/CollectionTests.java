package stqa.geometry.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

public class CollectionTests {

    @Test
    void setTests() {
        var set = Set.of("a", "b", "c");
        Assertions.assertEquals(set.size(), 3);
        var element = set.stream().findAny().get();
    }

    @Test
    void testMap() {
        var digits = new HashMap<Character, String>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");

        Assertions.assertEquals("one", digits.get('1'));
        digits.put('1', "один");
        Assertions.assertEquals("один", digits.get('1'));
    }
}
