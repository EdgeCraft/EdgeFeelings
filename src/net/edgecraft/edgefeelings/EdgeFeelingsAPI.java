package net.edgecraft.edgefeelings;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgefeelings.util.FeelingUsersMap;

public class EdgeFeelingsAPI {

    private static final EdgeFeelingsPlugin plugin = EdgeFeelingsPlugin.getSingleton();
    
    public static FeelingUser getFeelingUser(User user) {
        return plugin.getFeelingUsers().get(user);
    }
    
    public static FeelingUsersMap getFeelingUsers() {
        return plugin.getFeelingUsers();
    }
    
    public static FeelingUser registerFeelingUser(User user) {
        return plugin.getFeelingUsers().registerUser(user);
    }
    
}
