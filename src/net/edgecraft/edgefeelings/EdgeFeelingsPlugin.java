package net.edgecraft.edgefeelings;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgefeelings.commands.FeelingsCommand;
import net.edgecraft.edgefeelings.listener.FeelingTickListener;
import net.edgecraft.edgefeelings.listener.PlayerListener;
import net.edgecraft.edgefeelings.runnable.FeelingUpdaterRunnable;
import net.edgecraft.edgefeelings.util.FeelingUsersMap;

import org.bukkit.plugin.java.JavaPlugin;

public class EdgeFeelingsPlugin extends JavaPlugin {
    
    private static EdgeFeelingsPlugin singleton;
    
    private FeelingUsersMap map;

    @Override
    public void onEnable() {
        map = new FeelingUsersMap();
        
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new FeelingTickListener(), this);
        getServer().getScheduler().runTaskTimer(this, new FeelingUpdaterRunnable(), 1, 1);
        
        EdgeCoreAPI.commandsAPI().registerCommand(new FeelingsCommand());
    }

    @Override
    public void onLoad() {
        EdgeFeelingsPlugin.singleton = this;
    }
    
    public static EdgeFeelingsPlugin getSingleton() {
        return singleton;
    }

    public FeelingUsersMap getFeelingUsers() {
        return map;
    }

}
