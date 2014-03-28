package net.edgecraft.edgefeelings;

import java.util.Collection;
import java.util.HashMap;

import net.edgecraft.edgefeelings.display.DisplayHandler;
import net.edgecraft.edgefeelings.event.FeelingTickEvent;
import net.edgecraft.edgefeelings.event.FeelingZeroEvent;

import org.bukkit.Bukkit;

@SuppressWarnings("unused")
public class Feeling {

    private final FeelingType type;
    
    private final Collection<DisplayHandler> displayHandlers;
    
    private double currentValue;
    private double currentMaxValue;
    
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
        this.displayHandlers = type.getDefaultDisplayHandlers();
    }

    public void onTick(FeelingUser feelingUser) {
        FeelingTickEvent event = new FeelingTickEvent(feelingUser, this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        
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

    public void setCurrentMaxValue(double currentMaxValue) {
        this.currentMaxValue = currentMaxValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
    
    public void addToCurrentValue(double increment) {
        this.currentValue += increment;
    }

    public Collection<DisplayHandler> getDisplayHandlers() {
        return displayHandlers;
    }
    
}
