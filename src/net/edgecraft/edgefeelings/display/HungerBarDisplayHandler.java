package net.edgecraft.edgefeelings.display;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingUser;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HungerBarDisplayHandler implements DisplayHandler {

    private int lastFoodLevel;
    
    @Override
    public void display(FeelingUser user, Feeling feeling) {
        Player player = Bukkit.getPlayerExact(user.getUser().getName());
        if (player == null) {
            return;
        }

        int foodLevel = (int) Math.ceil(((feeling.getCurrentValue() / feeling.getCurrentMaxValue()) * 20));
        if (lastFoodLevel != foodLevel) {
            player.setFoodLevel(Math.min(foodLevel, 20));
        }
        
        lastFoodLevel = foodLevel;
    }

}
