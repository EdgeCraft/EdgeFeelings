package net.edgecraft.edgefeelings.listener;

import net.edgecraft.edgefeelings.FeelingType;
import net.edgecraft.edgefeelings.event.FeelingTickEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class FeelingTickListener implements Listener {
    
    @EventHandler(priority = EventPriority.LOW)
    public void onFeelingTick(FeelingTickEvent ev) {
        FeelingType type = ev.getFeeling().getType();
        
        ev.getFeeling().addToCurrentValue(type.getDefaultChangement());
    }

}
