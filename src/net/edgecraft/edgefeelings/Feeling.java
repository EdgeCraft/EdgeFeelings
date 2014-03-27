package net.edgecraft.edgefeelings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;

import net.edgecraft.edgefeelings.display.DisplayHandler;
import net.edgecraft.edgefeelings.util.ModifierSet;

@SuppressWarnings("unused")
public class Feeling {

    private final FeelingType type;
    
    private final Collection<DisplayHandler> displayHandlers;
    
    private double currentValue;
    private double currentMaxValue;
    
    private final ModifierSet maxValueModifiers;
    private final ModifierSet valueModifiers;
    
    public static HashMap<FeelingType, Feeling> getDefaultFeelings() {
        HashMap<FeelingType, Feeling> feelings = new HashMap<FeelingType, Feeling>();
        for (FeelingType type : FeelingType.values()) {
            feelings.put(type, new Feeling(type));
        }
        
        return feelings;
    }
    
    public Feeling(FeelingType type) {
        this(type, type.getDefaultInitialValue());
    }
    
    public Feeling(FeelingType type, double currentValue) {
        this.type = type;
        this.currentValue = currentValue;
        this.currentMaxValue = type.getDefaultMaxValue();
        this.maxValueModifiers = new ModifierSet();
        this.valueModifiers = new ModifierSet();
        this.displayHandlers = Arrays.asList(type.getDefaultDisplayHandler());
        
        maxValueModifiers.addAll(type.getDefaultMaxValueModifiers());
        valueModifiers.addAll(type.getDefaultValueModifiers());
    }

    public void onTick(FeelingUser feelingUser) {
        double movment = valueModifiers.applyModifiers(feelingUser, this, 0);
        double newMaxValue = maxValueModifiers.applyModifiers(feelingUser, this, 0);
        
        this.currentMaxValue = newMaxValue;
        this.currentValue = Math.min(currentValue + movment, newMaxValue);
        
        if (displayHandlers != null) {
            for (DisplayHandler handler : displayHandlers) {
                handler.display(feelingUser, this);
            }
        }
        
        if (currentValue <= 0) {
            Bukkit.getServer().getPluginManager().callEvent(new FeelingZeroEvent(feelingUser, this));
        }
    }

    public FeelingType getType() {
        return type;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public double getCurrentMaxValue() {
        return currentMaxValue;
    }

    public ModifierSet getMaxValueModifiers() {
        return maxValueModifiers;
    }

    public ModifierSet getValueModifiers() {
        return valueModifiers;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public Collection<DisplayHandler> getDisplayHandlers() {
        return displayHandlers;
    }
    
}
