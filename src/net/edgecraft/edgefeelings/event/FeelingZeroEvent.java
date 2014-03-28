package net.edgecraft.edgefeelings.event;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingUser;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FeelingZeroEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    
    private FeelingUser user;
    private Feeling feeling;
    
    public FeelingZeroEvent(FeelingUser user, Feeling feeling) {
        this.user = user;
        this.feeling = feeling;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
     
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public FeelingUser getUser() {
        return user;
    }

    public Feeling getFeeling() {
        return feeling;
    }
    
    

}
