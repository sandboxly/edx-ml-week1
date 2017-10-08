package com.github.sandboxly.edxml.week1;

import org.junit.Test;

/**
 * TODO Add JavaDoc comment for PerceptronTest
 *
 * @author Stephan Lengl
 */
public class PerceptronTest {

    @Test
    public void test() {
        Perceptron<Double> perceptron = new Perceptron<>(Double.class, 2, 10);
        boolean converged = false;
        int iteration = 1;
        while (!(converged = perceptron.iterate())) {
            iteration ++;
        }
        System.out.println(String.format("Perceptron converged after %d iterations!", iteration));
    }
}
