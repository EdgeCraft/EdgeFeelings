package net.edgecraft.edgefeelings.listener;

import java.util.HashMap;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.event.FeelingTickEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

@SuppressWarnings("unused")
public class HungerBukkitMirrorListener implements Listener {
    
    private HashMap<String, Integer> lastFoodLevels = new HashMap<>();
    
    public void mirrorHungerToBukkit(FeelingTickEvent ev) {
        Player player = Bukkit.getPlayerExact(ev.getFeelingUser().getUser().getName());
        if (player == null) {
            return;
        }
        
        String name = player.getName();
        int lastFoodLevel = lastFoodLevels.containsKey(name) ? lastFoodLevels.get(name) : 20;

        Feeling feeling = ev.getFeeling();
        int foodLevel = (int) Math.ceil(((feeling.getCurrentValue() / feeling.getCurrentMaxValue()) * 20));
        if (lastFoodLevel != foodLevel) {
            player.setFoodLevel(Math.min(foodLevel, 20));
        }
        
        lastFoodLevels.put(name, foodLevel);
    }
    
}
