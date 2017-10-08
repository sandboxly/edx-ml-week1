package com.github.sandboxly.edxml.week1;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * TODO Add JavaDoc comment for ScalarVector
 *
 * @author Stephan Lengl
 */
public class ScalarVector<T extends Number> {

    private int dimensions;

    private T[] components;

    private Class<T> componentType;

    public ScalarVector(T... components) {
        this.componentType = (Class<T>) components[0].getClass();
        this.dimensions = components.length;
        this.components = components;
    }

    public ScalarVector(Class<T> componentType, int dimensions) {
        this.dimensions = dimensions;
        this.componentType = componentType;
        this.components = (T[]) Array.newInstance(componentType, dimensions);
    }

    public ScalarVector<T> add(ScalarVector<T> anotherVector) {
        if (!componentType.isAssignableFrom(anotherVector.componentType)
                || !anotherVector.componentType.isAssignableFrom(componentType)) {
            throw new IllegalArgumentException(String.format("Incompatible component types: %s, %s.",
                    componentType,
                    anotherVector.componentType));
        }
        if (anotherVector.dimensions != dimensions) {
            throw new IllegalArgumentException(String.format("Dimensionality mismatch: %d != %d",
                    dimensions,
                    anotherVector.dimensions));
        }
        for (int i = 0; i < dimensions; i++) {
            BigDecimal c1 = new BigDecimal(components[i].doubleValue());
            BigDecimal c2 = new BigDecimal(anotherVector.components[i].doubleValue());
            components[i] = ((T) new Double(c1.add(c2).doubleValue()));
        }
        return this;
    }

    public T innerProduct(ScalarVector<T> anotherVector) {
        if (!componentType.isAssignableFrom(anotherVector.componentType)
                || !anotherVector.componentType.isAssignableFrom(componentType)) {
            throw new IllegalArgumentException(String.format("Incompatible component types: %s, %s.",
                    componentType,
                    anotherVector.componentType));
        }
        if (anotherVector.dimensions != dimensions) {
            throw new IllegalArgumentException(String.format("Dimensionality mismatch: %d != %d",
                    dimensions,
                    anotherVector.dimensions));
        }
        ScalarVector<T> resultVector = new ScalarVector<T>(componentType, dimensions);
        BigDecimal totalResult = new BigDecimal(0.0);
        for (int i = 0; i < dimensions; i++) {
            BigDecimal c1 = new BigDecimal(components[i].doubleValue());
            BigDecimal c2 = new BigDecimal(anotherVector.components[i].doubleValue());
            BigDecimal result = c1.multiply(c2);
            totalResult = totalResult.add(result);
        }
        return (T) new Double(totalResult.doubleValue());
    }

    public ScalarVector<T> scalarProduct(T scalar) {
        BigDecimal bdScalar = new BigDecimal(scalar.doubleValue());
        for (int i = 0; i < dimensions; i++) {
            BigDecimal c = new BigDecimal(components[i].doubleValue());
            c = c.multiply(bdScalar);
            components[i] = (T) new Double(c.doubleValue());
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScalarVector<?> that = (ScalarVector<?>) o;

        if (dimensions != that.dimensions) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(components, that.components)) return false;
        return componentType.equals(that.componentType);
    }

    @Override
    public int hashCode() {
        int result = dimensions;
        result = 31 * result + Arrays.hashCode(components);
        result = 31 * result + componentType.hashCode();
        return result;
    }

    public void setComponent(int componentIndex, T value) {
        components[componentIndex] = value;
    }
}
