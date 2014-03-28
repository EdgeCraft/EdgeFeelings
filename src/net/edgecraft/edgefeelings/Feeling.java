package net.edgecraft.edgefeelings;

import java.util.HashMap;

import net.edgecraft.edgefeelings.event.FeelingTickEvent;

import org.bukkit.Bukkit;

public class Feeling {

    private final FeelingType type;

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
    }

    public void onTick(FeelingUser feelingUser) {
        FeelingTickEvent event = new FeelingTickEvent(feelingUser, this);
        Bukkit.getServer().getPluginManager().callEvent(event);
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

}
