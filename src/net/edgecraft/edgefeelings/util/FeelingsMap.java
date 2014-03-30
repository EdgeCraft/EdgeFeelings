package net.edgecraft.edgefeelings.util;

import java.util.HashMap;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingType;

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
