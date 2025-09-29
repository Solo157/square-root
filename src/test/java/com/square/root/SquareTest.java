package com.square.root;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SquareTest {

    private SquareRootService squareRootService = new SquareRootService();

    /**
     * Проверяет, что для уравнения x^2 + 1 = 0 корней нет (пустой массив).
     */
    @Test
    public void testNoRootsForEquation_x2Plus1_equalsZero() {
        double a = 1;
        double b = 0;
        double c = 1;
        double epsilon = 0;

        double[] roots = squareRootService.solve(a, b, c, epsilon);

        assertEquals(0, roots.length);
    }

    /**
     * Проверяет, что для уравнения x^2 - 1 = 0 есть два корня: 1 и -1.
     */
    @Test
    public void testTwoDistinctRootsForEquation_x2Minus1_equalsZero() {
        double a = 1;
        double b = 0;
        double c = -1;
        double epsilon = 0;

        double[] roots = squareRootService.solve(a, b, c, epsilon);

        assertEquals(2, roots.length);
        assertEquals(1.0, roots[0], 0.01);
        assertEquals(-1.0, roots[1], 0.01);
    }

    /**
     * Проверяет, что для уравнения x^2 + 2x + 1 = 0 есть один корень двойной кратности: -1.
     */
    @Test
    public void testOneDoubleRootForEquation_x2Plus2xPlus1_equalsZero() {
        double a = 1;
        double b = 2;
        double c = 1;
        double epsilon = 0;

        double[] roots = squareRootService.solve(a, b, c, epsilon);

        assertEquals(1, roots.length);
        assertEquals(-1.0, roots[0], 0.01);
    }

    /**
     * Проверяет, что при a близком к 0 метод выбрасывает IllegalArgumentException.
     */
    @Test
    public void testExceptionThrownWhenCoefficientAIsZero() {
        double a = 0;
        double b = 1;
        double c = 1;
        double epsilon = 0;

        assertThrows(IllegalArgumentException.class, () -> {
            squareRootService.solve(a, b, c, epsilon);
        });
    }

    /**
     * Проверяет случай одного корня с дискриминантом > 0, но меньше epsilon.
     */
    @Test
    public void testOneRootWhenDiscriminantIsPositiveButLessThanEpsilon() {
        double a = 1;
        double b = 2;
        double c = 1.0000002;
        double epsilon = 0.000001;

        double[] roots = squareRootService.solve(a, b, c, epsilon);

        assertEquals(1, roots.length);
        assertEquals(-1.0, roots[0], 0.01);
    }

    /**
     * Проверяет, что при аргументах NaN выбрасывается исключение.
     */
    @Test
    public void testExceptionThrownWhenCoefficientIsNaN() {
        double a = Double.NaN;
        double b = 2;
        double c = 1;
        double epsilon = 0.000001;

        assertThrows(IllegalArgumentException.class, () -> {
            squareRootService.solve(a, b, c, epsilon);
        });
    }

    /**
     * Проверяет, что при b == Double.POSITIVE_INFINITY выбрасывается исключение.
     */
    @Test
    public void testExceptionThrownWhenCoefficientBIsPositiveInfinity() {
        double a = 1;
        double b = Double.POSITIVE_INFINITY;
        double c = 1;
        double epsilon = 0.000001;

        assertThrows(IllegalArgumentException.class, () -> {
            squareRootService.solve(a, b, c, epsilon);
        });
    }

    /**
     * Проверяет, что при c == Double.NEGATIVE_INFINITY выбрасывается исключение.
     */
    @Test
    public void testExceptionThrownWhenCoefficientCIsNegativeInfinity() {
        double a = 1;
        double b = 2;
        double c = Double.NEGATIVE_INFINITY;
        double epsilon = 0.000001;

        assertThrows(IllegalArgumentException.class, () -> {
            squareRootService.solve(a, b, c, epsilon);
        });
    }

}
