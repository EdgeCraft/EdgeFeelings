package net.edgecraft.edgefeelings;

import java.util.Arrays;
import java.util.List;

import net.edgecraft.edgefeelings.display.ChatDisplayHandler;
import net.edgecraft.edgefeelings.display.DisplayHandler;
import net.edgecraft.edgefeelings.display.HungerBarDisplayHandler;
import net.edgecraft.edgefeelings.modifier.BukkitHungerSourceModifier;
import net.edgecraft.edgefeelings.modifier.Modifier;
import net.edgecraft.edgefeelings.modifier.ConstantModifier;

public enum FeelingType {

    HUNGER(20, 20, Arrays.asList(new ConstantModifier(-1, 20)), Arrays.asList(new BukkitHungerSourceModifier()), new HungerBarDisplayHandler()), 
    THURST(20, -0.001), 
    MOOD(20, -0.001), 
    AWAKENESS(20, -0.001);

    private final double defaultMaxValue;
    private final double defaultInitialValue;
    private final List<Modifier> defaultMaxValueModifiers;
    private final List<Modifier> defaultValueModifiers;
    private final DisplayHandler defaultDisplayHandler;
    
    private FeelingType(double defaultMaxValue, double defaultDecrement) {
        this.defaultInitialValue = defaultMaxValue;
        this.defaultMaxValue = defaultMaxValue;
        this.defaultMaxValueModifiers = Arrays.asList(new ConstantModifier(-1, defaultMaxValue));
        this.defaultValueModifiers = Arrays.asList(new ConstantModifier(-1, defaultDecrement));
        this.defaultDisplayHandler = new ChatDisplayHandler();
    }

    private FeelingType(double defaultMaxValue, double defaultInitialValue,
            List<Modifier> defaultMaxValueModifiers, List<Modifier> defaultValueModifiers,
            DisplayHandler defaultDisplayHandler) {
        this.defaultMaxValue = defaultMaxValue;
        this.defaultInitialValue = defaultInitialValue;
        this.defaultMaxValueModifiers = defaultMaxValueModifiers;
        this.defaultValueModifiers = defaultValueModifiers;
        this.defaultDisplayHandler = defaultDisplayHandler;
    }

    public double getDefaultMaxValue() {
        return defaultMaxValue;
    }
    
    public double getDefaultInitialValue() {
        return defaultInitialValue;
    }
    
    public List<Modifier> getDefaultMaxValueModifiers() {
        return defaultMaxValueModifiers;
    }
    
    public List<Modifier> getDefaultValueModifiers() {
        return defaultValueModifiers;
    }

    public DisplayHandler getDefaultDisplayHandler() {
        return defaultDisplayHandler;
    }
    
}
