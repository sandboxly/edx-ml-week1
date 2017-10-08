package com.github.sandboxly.edxml.week1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * TODO Add JavaDoc comment for ScalarVectorTest
 *
 * @author Stephan Lengl
 */
public class ScalarVectorTest {

    @Test
    public void add() {
        ScalarVector<Double> v1 = new ScalarVector<>(1.0, 1.0, 2.0, 3.0, 5.0);
        ScalarVector<Double> v2 = new ScalarVector<>(5.0, 3.0, 2.0, 1.0, 1.0);
        ScalarVector<Double> result = v1.add(v2);
        ScalarVector<Double> expectedResult = new ScalarVector<>(6.0, 4.0, 4.0, 4.0, 6.0);
        assertEquals(expectedResult, result);
    }

    @Test
    public void innerProduct() {
        ScalarVector<Double> v1 = new ScalarVector<>(1.0, 1.0, 2.0, 3.0, 5.0);
        ScalarVector<Double> v2 = new ScalarVector<>(5.0, 3.0, 2.0, 1.0, 1.0);
        Double innerProduct = v1.innerProduct(v2);
        assertTrue(20.0 == innerProduct.doubleValue());
    }

    @Test
    public void scalarProduct() {
        ScalarVector<Double> v = new ScalarVector<>(1.0, 1.0, 2.0, 3.0, 5.0);
        ScalarVector<Double> result = v.scalarProduct(10.0);
        ScalarVector<Double> expectedResult = new ScalarVector<>(10.0, 10.0, 20.0, 30.0, 50.0);
        assertEquals(expectedResult, result);
    }
}