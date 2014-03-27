package net.edgecraft.edgefeelings.modifier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingType;
import net.edgecraft.edgefeelings.FeelingUser;

public class BukkitHungerSourceModifier extends Modifier {

    public BukkitHungerSourceModifier() {
        super(-1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double modify(FeelingUser feelingUser, Feeling feeling, double input) {
        Player player = Bukkit.getPlayerExact(feelingUser.getUser().getName());
        if (player == null) {
            return 0;
        }
        
        double currentValue = feelingUser.getFeelings().get(FeelingType.HUNGER).getCurrentValue();
        int foodLevel = player.getFoodLevel();
        
        return ((double) foodLevel) - currentValue;
    }

}
