package net.edgecraft.edgefeelings;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgefeelings.util.FeelingPlayersMap;

public class EdgeFeelingsAPI {

    private static final EdgeFeelingsPlugin plugin = EdgeFeelingsPlugin.getSingleton();
    
    public static FeelingUser getFeelingPlayer(User user) {
        return plugin.getFeelingPlayers().get(user);
    }
    
    public static FeelingPlayersMap getFeelingPlayers() {
        return plugin.getFeelingPlayers();
    }
    
    public static FeelingUser registerFeelingUser(User user) {
        return plugin.getFeelingPlayers().registerUser(user);
    }
    
}
