package net.edgecraft.edgefeelings.util;

import java.util.HashMap;

import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgefeelings.FeelingUser;

@SuppressWarnings("serial")
public class FeelingPlayersMap extends HashMap<User, FeelingUser> {

    public FeelingUser registerUser(User user) {
        if (containsKey(user)) {
            return get(user);
        }
        
        FeelingUser feelingUser = new FeelingUser(user);
        put(user, feelingUser);
        return feelingUser;
    }

}
