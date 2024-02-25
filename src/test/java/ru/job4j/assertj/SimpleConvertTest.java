package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        var simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .containsExactly("first", "second", "three", "four", "five")
                .startsWith("first")
                .endsWith("five");
    }

    @Test
    void checkToList() {
        var simpleConvert = new SimpleConvert();
        List<String> actual = simpleConvert.toList("first", "second", "three", "four", "five", "six");
        assertThat(actual).hasSize(6)
                .allSatisfy(e -> {
                    assertThat(e).isNotBlank();
                    assertThat(e).isLowerCase();
                })
                .anyMatch(e -> e.length() == 3)
                .containsSequence("five", "six")
                .last().isEqualTo("six");
        assertThat(actual).element(3).isEqualTo("four");

    }

    @Test
    void checkToSet() {
        var simpleConvert = new SimpleConvert();
        Set<String> actual = simpleConvert.toSet("six", "first", "second", "three", "four", "five", "six");
        actual.add("first");
        assertThat(actual).hasSize(6)
                .containsAnyOf("six")
                .doesNotContain("zero")
                .doesNotHaveDuplicates()
                .containsExactlyInAnyOrder("six", "five", "first", "second", "three", "four")
                .filteredOn(e -> e.startsWith("f"))
                .hasSize(3);
    }

    @Test
    void checkToMap() {
        var simpleConvert = new SimpleConvert();
        Map<String, Integer> actual = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(actual).hasSize(5)
                .containsKey("five")
                .containsKeys("second", "three")
                .containsOnlyKeys("first", "second", "three", "four", "five")
                .containsValue(4)
                .containsValues(2, 3)
                .doesNotContainEntry("first", 1)
                .doesNotContainValue(6)
                .containsEntry("five", 4)
                .allSatisfy((k, v) -> {
                    assertThat(k.length()).isGreaterThan(3);
                    assertThat(v).isLessThan(5);
                });
    }
}