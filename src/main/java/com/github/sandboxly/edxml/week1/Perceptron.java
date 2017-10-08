package com.github.sandboxly.edxml.week1;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * TODO Add JavaDoc comment for Perceptron
 *
 * @author Stephan Lengl
 */
public class Perceptron<T extends Number> {

    private ScalarVector<T>[] dataSet;


    private double[] trainingClassifications;

    private ScalarVector<T> currentHypothesis;

    private ScalarVector<T> bestHypothesis;

    private int bestHypothesisErrorCount;

    public Perceptron(Class<T> componentType, int dimensions, int numPoints) {
        dataSet = PerceptronUtils.generateRandomPoints(numPoints, dimensions, componentType);
        ScalarVector<T> targetFunction = PerceptronUtils.generateRandomTargetFunction(dimensions, componentType);
        trainingClassifications = applyFunction(dataSet, targetFunction);
        currentHypothesis = PerceptronUtils.generateInitialHypothesis(dimensions, componentType);
        bestHypothesis = currentHypothesis;
        bestHypothesisErrorCount = dataSet.length + 1;
    }

    private double[] applyFunction(ScalarVector<T>[] dataSet, ScalarVector<T> function) {
        double[] classifications = new double[dataSet.length];
        for (int i = 0; i < dataSet.length; i++) {
            ScalarVector<T> currentPoint = dataSet[i];
            double classification = Math.signum(currentPoint.innerProduct(function).doubleValue());
            classifications[i] = classification;
        };
        return classifications;
    }

    public boolean iterate() {
        double[] classificationResult = applyFunction(dataSet, currentHypothesis);
        List<ScalarVector<T>> misclassifiedPoints = getMisclassifiedPoints(classificationResult);
        int errorCount = misclassifiedPoints.size();
        System.out.println(String.format("Error count = %d!", errorCount));
        if (errorCount == 0) {
            bestHypothesis = currentHypothesis;
            bestHypothesisErrorCount = 0;
            return true;
        } else if (errorCount < bestHypothesisErrorCount) {
            bestHypothesis = currentHypothesis;
            bestHypothesisErrorCount = errorCount;
        }
        nudgeCurrentHypothesis(pickRandomItemFromList(misclassifiedPoints));
        return false;
    }

    private List<ScalarVector<T>> getMisclassifiedPoints(double[] classificationResult) {
        List<ScalarVector<T>> misclassifiedPoints = new ArrayList<>();
        for (int i = 0; i < classificationResult.length; i++) {
            if (classificationResult[i] != trainingClassifications[i]) {
                ScalarVector<T> misclassifiedPoint = dataSet[i];
                misclassifiedPoints.add(misclassifiedPoint);
            }
        }
        return misclassifiedPoints;
    }

    private void nudgeCurrentHypothesis(ScalarVector<T> point) {
        double trainingPointClassification = point.innerProduct(currentHypothesis).doubleValue();
        if (trainingPointClassification == 0) {
            trainingPointClassification = 1.0;
        }
        int correctClassificationIndex = Arrays.asList(dataSet).indexOf(point);
        if (trainingPointClassification == -1.0) {
            currentHypothesis = currentHypothesis.add(point.scalarProduct((T) new Double(-1)));
        } else {
            currentHypothesis = currentHypothesis.add(point);
        }
    }

    private ScalarVector<T> pickRandomItemFromList(List<ScalarVector<T>> list) {
        return list.get(new SecureRandom().nextInt(list.size()));
    }
}
