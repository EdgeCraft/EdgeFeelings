package net.edgecraft.edgefeelings;

import java.util.HashMap;

@SuppressWarnings("serial")
public class FeelingsMap extends HashMap<FeelingType, Feeling> {

    @Override
    public Feeling get(Object key) {
        if (key instanceof FeelingType) {
            FeelingType type = (FeelingType) key;
            if (!super.containsKey(type)) {
                super.put(type, new Feeling(type));
            }
        }
        
        return super.get(key);
    }
    
}
