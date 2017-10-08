package com.github.sandboxly.edxml.week1;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * TODO Add JavaDoc comment for PerceptronUtils
 *
 * @author Stephan Lengl
 */
public class PerceptronUtils {

    public static <T extends Number> ScalarVector<T>[] generateRandomPoints(int numPoints, int dimensions, Class<T> componentType) {
        ScalarVector<T>[] dataSet = (ScalarVector<T>[]) Array.newInstance(new ScalarVector(componentType, dimensions + 1).getClass(), numPoints);
        for (int i = 0; i < numPoints; i++) {
            Random random = new Random();
            ScalarVector<T> currentPoint = new ScalarVector<>(componentType, dimensions + 1);
            currentPoint.setComponent(0, (T) new Double(1.0));
            for (int n = 1; n < dimensions + 1; n++) {
                currentPoint.setComponent(n, (T) new Double((random.nextDouble() * 2) - 1.0));
            }
            dataSet[i] = currentPoint;
        }
        return dataSet;
    }

    public static <T extends Number> ScalarVector<T> generateRandomTargetFunction(int dimensions, Class<T> componentType) {
        Random random = new Random();
        ScalarVector<T> targetFunction = new ScalarVector<>(componentType, dimensions + 1);
        for (int i = 0; i < dimensions + 1; i++) {
            targetFunction.setComponent(i, (T) new Double(random.nextDouble()*2 - 1));
        }
        return targetFunction;
    }

    public static <T extends Number> ScalarVector<T> generateInitialHypothesis(int dimensions, Class<T> componentType) {
        Random random = new Random();
        ScalarVector<T> hypothesis = new ScalarVector<>(componentType, dimensions + 1);
        for (int i = 0; i < dimensions + 1; i++) {
            hypothesis.setComponent(i, (T) new Double(0));
        }
        return hypothesis;
    }
}
