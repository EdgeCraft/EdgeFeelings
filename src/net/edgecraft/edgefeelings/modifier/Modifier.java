package net.edgecraft.edgefeelings.modifier;

import java.util.UUID;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingUser;


public class Modifier implements Comparable<Modifier> {

    private UUID uid;
    private int priority;

    public Modifier(int priority) {
        this.priority = priority;
        this.uid = UUID.randomUUID();
    }
    
    public Modifier(UUID uid, int priority) {
        this.uid = uid;
        this.priority = priority;
    }

    public double modify(FeelingUser feelingUser, Feeling feeling, double input) {
        return input;
    }

    @Override
    public int compareTo(Modifier modifier) {
        return modifier.priority - this.priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + priority;
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Modifier)) {
            return false;
        }
        Modifier other = (Modifier) obj;
        if (priority != other.priority) {
            return false;
        }
        if (uid == null) {
            if (other.uid != null) {
                return false;
            }
        } else if (!uid.equals(other.uid)) {
            return false;
        }
        return true;
    }

    public UUID getUid() {
        return uid;
    }
    
}
