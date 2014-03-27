package net.edgecraft.edgefeelings.modifier;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingUser;

public class ConstantModifier extends Modifier {

    private double value;
    
    public ConstantModifier(int priority, double value) {
        super(priority);
        this.value = value;
    }

    @Override
    public double modify(FeelingUser feelingUser, Feeling feeling, double input) {
        return value;
    }

}
