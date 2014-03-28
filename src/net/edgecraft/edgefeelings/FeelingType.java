package net.edgecraft.edgefeelings;

import java.util.Arrays;
import java.util.Collection;

import net.edgecraft.edgefeelings.display.DisplayHandler;
import net.edgecraft.edgefeelings.display.HungerBarDisplayHandler;

public enum FeelingType {

    HUNGER(20, 20, 0, Arrays.asList(new HungerBarDisplayHandler())), 
    THURST(20, -0.001), 
    MOOD(20, -0.001), 
    AWAKENESS(20, -0.001);

    private final double defaultMaxValue;
    private final double defaultInitialValue;
    private final double defaultChangement;    
    
    private final Collection<DisplayHandler> defaultDisplayHandlers;
    
    private FeelingType(double defaultMaxValue, double defaultDecrement) {
        this.defaultInitialValue = defaultMaxValue;
        this.defaultMaxValue = defaultMaxValue;
        this.defaultChangement = defaultDecrement;
        this.defaultDisplayHandlers = null;
    }

    private FeelingType(double defaultMaxValue, double defaultInitialValue, double defaultDecrement, Collection<DisplayHandler> defaultDisplayHandlers) {
        this.defaultMaxValue = defaultMaxValue;
        this.defaultInitialValue = defaultInitialValue;
        this.defaultDisplayHandlers = defaultDisplayHandlers;
        this.defaultChangement = defaultDecrement;
    }

    public double getDefaultMaxValue() {
        return defaultMaxValue;
    }
    
    public double getDefaultInitialValue() {
        return defaultInitialValue;
    }

    public Collection<DisplayHandler> getDefaultDisplayHandlers() {
        return defaultDisplayHandlers;
    }

    public double getDefaultChangement() {
        return defaultChangement;
    }
    
}
