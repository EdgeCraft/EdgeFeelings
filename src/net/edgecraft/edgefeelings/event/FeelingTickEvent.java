package net.edgecraft.edgefeelings.event;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingUser;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FeelingTickEvent extends Event implements Cancellable {
    
    private static final HandlerList handlers = new HandlerList();
    
    private boolean cancelled = false;
    
    private FeelingUser user;
    private Feeling feeling;

    public FeelingTickEvent(FeelingUser user, Feeling feeling) {
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

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
    
}
