package net.edgecraft.edgefeelings.runnable;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgefeelings.EdgeFeelingsAPI;
import net.edgecraft.edgefeelings.FeelingUser;

public class FeelingUpdaterRunnable implements Runnable {

    @Override
    public void run() {
        for (User user : EdgeCoreAPI.userAPI().getUsers().values()) {
            FeelingUser feelingUser = EdgeFeelingsAPI.getFeelingPlayer(user);
            
            if (feelingUser != null) {
                feelingUser.tickFeelings();
            }
        }
    }

}
