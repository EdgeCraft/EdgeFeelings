package net.edgecraft.edgefeelings.display;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingUser;

public class ChatDisplayHandler implements DisplayHandler {

    private static final int delay = 40;
    private int counter = 0;
    
    @Override
    public void display(FeelingUser user, Feeling feeling) {
        counter++;
        if (counter > delay) {
            counter = 0;
            
            Player player = Bukkit.getPlayerExact(user.getUser().getName());
            if (player == null) {
                return;
            }
            
            
            player.sendMessage(feeling.getType().toString() + " = " + (feeling.getCurrentValue() / feeling.getCurrentMaxValue()));
        }        
    }

}
