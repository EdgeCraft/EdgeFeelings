package net.edgecraft.edgefeelings.display;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingUser;

public interface DisplayHandler {

    public void display(FeelingUser user, Feeling feeling);
    
}
