package net.edgecraft.edgefeelings.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingUser;
import net.edgecraft.edgefeelings.modifier.Modifier;

@SuppressWarnings("serial")
public class ModifierSet extends HashSet<Modifier> {

    public double applyModifiers(FeelingUser feelingUser, Feeling feeling, double input) {
        List<Modifier> modifiers = new ArrayList<Modifier>();
        modifiers.addAll(this);
        Collections.sort(modifiers);
        
        Iterator<Modifier> i = modifiers.iterator();
        while (i.hasNext()) {
            input = i.next().modify(feelingUser, feeling, input);
        }
        
        return input;
    }

}
