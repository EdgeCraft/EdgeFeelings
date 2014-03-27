package net.edgecraft.edgefeelings;

import net.edgecraft.edgefeelings.listener.PlayerListener;
import net.edgecraft.edgefeelings.runnable.FeelingUpdaterRunnable;
import net.edgecraft.edgefeelings.util.FeelingPlayersMap;

import org.bukkit.plugin.java.JavaPlugin;

public class EdgeFeelingsPlugin extends JavaPlugin {
    
    private static EdgeFeelingsPlugin singleton;
    
    private FeelingPlayersMap map;

    @Override
    public void onEnable() {
        map = new FeelingPlayersMap();
        
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getScheduler().runTaskTimer(this, new FeelingUpdaterRunnable(), 1, 1);
    }

    @Override
    public void onLoad() {
        EdgeFeelingsPlugin.singleton = this;
    }
    
    public static EdgeFeelingsPlugin getSingleton() {
        return singleton;
    }

    public FeelingPlayersMap getFeelingPlayers() {
        return map;
    }

}
