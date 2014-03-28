package net.edgecraft.edgefeelings;

import java.util.HashMap;
import java.util.List;

import net.edgecraft.edgecore.user.User;

import org.apache.commons.lang.Validate;

@SuppressWarnings("unused")
public class FeelingUser {

    private final User user;

    private final HashMap<FeelingType, Feeling> feelings;

    public FeelingUser(User user) {
        this(user, Feeling.getDefaultFeelings());
    }

    public FeelingUser(User user, HashMap<FeelingType, Feeling> feelings) {
        Validate.notNull(user);
        Validate.notNull(feelings);
        this.user = user;
        this.feelings = feelings;
    }

    public void tickFeelings() {
        for (Feeling feeling : feelings.values()) {
            feeling.onTick(this);
        }
    }

    public User getUser() {
        return user;
    }

    public HashMap<FeelingType, Feeling> getFeelings() {
        return feelings;
    }

}
