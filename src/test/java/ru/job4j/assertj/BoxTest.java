package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        var box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .isNotBlank()
                .contains("Sphe")
                .containsIgnoringCase("ere")
                .doesNotContain("Tetra")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        var box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name)
                .startsWith("Tetra")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisUnknown() {
        var box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .endsWith("ect")
                .isEqualTo("Unknown object");
    }

    @Test
    void whenVerticesIsPositive() {
        var box = new Box(4, 10);
        int actual = box.getNumberOfVertices();
        assertThat(actual)
                .isPositive()
                .isEqualTo(4);
    }

    @Test
    void whenVerticesIsNegative() {
        var box = new Box(3, 10);
        int actual = box.getNumberOfVertices();
        assertThat(actual)
                .isLessThan(0)
                .isNegative();
    }

    @Test
    void whenVertex1ThenIsNotExist() {
        var box = new Box(1, 1);
        boolean actual = box.isExist();
        assertThat(actual).isFalse();
    }

    @Test
    void whenEdgeZeroThenIsNotExist() {
        var box = new Box(8, 0);
        boolean actual = box.isExist();
        assertThat(actual).isFalse();
    }

    @Test
    void whenVertex8Edge1ThenIsExist() {
        var box = new Box(8, 1);
        boolean actual = box.isExist();
        assertThat(actual).isTrue();
    }

    @Test
    void whenVertex4Edge4WhenArea27Dot7() {
        var box = new Box(4, 4);
        double actual = box.getArea();
        assertThat(actual)
                .isNotZero()
                .isPositive()
                .isGreaterThan(27.7D)
                .isLessThan(28D)
                .isCloseTo(27.7D, withPrecision(0.09D));
    }

    @Test
    void whenVertex3Edge4WhenAreaZero() {
        var box = new Box(3, 4);
        double actual = box.getArea();
        assertThat(actual).isZero();
    }

}