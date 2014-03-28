package net.edgecraft.edgefeelings.listener;

import net.edgecraft.edgefeelings.FeelingType;
import net.edgecraft.edgefeelings.event.FeelingTickEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class FeelingTickListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onFeelingTick(FeelingTickEvent ev) {
        FeelingType type = ev.getFeeling().getType();
        Player player = Bukkit.getPlayerExact(ev.getFeelingUser().getUser().getName());
        if (player == null) {
            return;
        }

        if (type == FeelingType.HUNGER) {
            ev.getFeeling().setCurrentValue(player.getFoodLevel());
            return;
        }

        ev.getFeeling().addToCurrentValue(type.getDefaultChangement());
    }

}
