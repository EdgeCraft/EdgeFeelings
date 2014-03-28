package net.edgecraft.edgefeelings;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("unused")
public enum FeelingType {

    HUNGER(20, 20, 0), THURST(20, -0.001), MOOD(20, -0.001), AWAKENESS(20, -0.001);

    private final double defaultMaxValue;
    private final double defaultInitialValue;
    private final double defaultChangement;

    private FeelingType(double defaultMaxValue, double defaultDecrement) {
        this.defaultInitialValue = defaultMaxValue;
        this.defaultMaxValue = defaultMaxValue;
        this.defaultChangement = defaultDecrement;
    }

    private FeelingType(double defaultMaxValue, double defaultInitialValue, double defaultDecrement) {
        this.defaultMaxValue = defaultMaxValue;
        this.defaultInitialValue = defaultInitialValue;
        this.defaultChangement = defaultDecrement;
    }

    public double getDefaultMaxValue() {
        return defaultMaxValue;
    }

    public double getDefaultInitialValue() {
        return defaultInitialValue;
    }

    public double getDefaultChangement() {
        return defaultChangement;
    }

}
