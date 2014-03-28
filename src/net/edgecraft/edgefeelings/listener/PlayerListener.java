package net.edgecraft.edgefeelings.listener;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgefeelings.EdgeFeelingsAPI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void addFeelingUser(PlayerJoinEvent ev) {
        User user = EdgeCoreAPI.userAPI().getUser(ev.getPlayer().getName());
        if (user != null) {
            EdgeFeelingsAPI.registerFeelingUser(user);
        }
    }

}
