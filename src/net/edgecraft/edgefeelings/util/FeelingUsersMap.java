package net.edgecraft.edgefeelings.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingType;
import net.edgecraft.edgefeelings.FeelingUser;

@SuppressWarnings("serial")
public class FeelingUsersMap extends HashMap<User, FeelingUser> {

    public FeelingUser registerUser(User user) {
        if (containsKey(user)) {
            return super.get(user);
        }

        FeelingUser feelingUser = new FeelingUser(user);
        put(user, feelingUser);
        return feelingUser;
    }

    @Override
    public FeelingUser get(Object key) {
        if (key instanceof User) {
            registerUser((User) key);
        }
        return super.get(key);
    }
    
    public void saveToDatabase() throws Exception {
        for (FeelingUser user : this.values()) {
            for (Feeling feeling : user.getFeelings().values()) {
                double currentMaxValue = feeling.getCurrentMaxValue();
                double currentValue = feeling.getCurrentValue();
                
                PreparedStatement stmt = EdgeCoreAPI.databaseAPI().prepareQuery("SELECT * FROM edgefeelings_feelings WHERE id = ? AND feeling_type = ?");
                stmt.setInt(1, user.getUser().getID());
                stmt.setString(2, feeling.getType().toString());
                
                stmt.execute();
                ResultSet result = stmt.getResultSet();
                if (result.first()) {
                    double previousValue = result.getDouble("value");
                    double previousMaxValue = result.getDouble("max_value");
                    
                    if (previousMaxValue == currentMaxValue && previousValue == currentValue) {
                        continue;
                    }
                    
                    PreparedStatement update = EdgeCoreAPI.databaseAPI().prepareUpdate("UPDATE edgefeelings_feelings SET value = ?, max_value = ? WHERE id = ? AND feeling_type = ?");
                    update.setDouble(1, currentValue);
                    update.setDouble(2, currentMaxValue);
                    update.setInt(3, user.getUser().getID());
                    update.setString(4, feeling.getType().toString());
                    
                    update.execute();
                } else {
                    PreparedStatement insert = EdgeCoreAPI.databaseAPI().prepareUpdate("INSERT INTO edgefeelings_feelings (id, feeling_type, value, max_value) VALUES (?, ?, ?, ?)");
                    insert.setInt(1, user.getUser().getID());
                    insert.setString(2, feeling.getType().toString());
                    insert.setDouble(3, currentValue);
                    insert.setDouble(4, currentMaxValue);
                    
                    insert.execute();
                }                
            }
        }
    }

    public void readFromDatabase() throws Exception {
        PreparedStatement stmt = EdgeCoreAPI.databaseAPI().prepareQuery("SELECT * FROM edgefeelings_feelings");
        
        stmt.execute();
        ResultSet result = stmt.getResultSet();
        
        while (result.next()) {
            int id = result.getInt("id");
            String feelingType = result.getString("feeling_type");
            double value = result.getDouble("value");
            double maxValue = result.getDouble("max_value");
                    
            User user = EdgeCoreAPI.userAPI().getUser(id);
            if (user == null) {
                continue;
            }
            
            FeelingType type;
            try {
                type = FeelingType.valueOf(feelingType);
            } catch (IllegalArgumentException e) {
                continue;
            }
            
            FeelingUser feelingUser = get(user);
            Feeling feeling = feelingUser.getFeelings().get(type);
            feeling.setCurrentValue(value);
            feeling.setCurrentMaxValue(maxValue);
        }
    }
    
}
