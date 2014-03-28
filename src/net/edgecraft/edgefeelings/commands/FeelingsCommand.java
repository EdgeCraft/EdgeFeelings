package net.edgecraft.edgefeelings.commands;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;
import net.edgecraft.edgefeelings.EdgeFeelingsAPI;
import net.edgecraft.edgefeelings.Feeling;
import net.edgecraft.edgefeelings.FeelingType;
import net.edgecraft.edgefeelings.FeelingUser;
import net.edgecraft.edgefeelings.util.FeelingUsersMap;

@SuppressWarnings("unused")
public class FeelingsCommand extends AbstractCommand {

    @Override
    public Level getLevel() {
        return Level.USER;
    }

    @Override
    public String[] getNames() {
        return new String[] {"feelings", "feels"};
    }

    @Override
    public boolean runImpl(Player player, User user, String[] args) throws Exception {
        FeelingUser feelingUser = EdgeFeelingsAPI.getFeelingUser(user);
        
        Feeling selected = null;
        if (args.length > 1) {
            try {
                FeelingType selectedType = FeelingType.valueOf(args[0]);
                selected = feelingUser.getFeelings().get(selectedType);
            } catch (IllegalArgumentException e) {
            }
        }
        
        if (selected != null) {
            player.sendMessage(getFeelingStatus(selected));
        } else {
            for (Feeling feeling : feelingUser.getFeelings().values()) {
                player.sendMessage(getFeelingStatus(feeling));
            }
        }
        return true;
        
    }
    
    private String getFeelingStatus(Feeling feeling) {
        return String.format("%s: %f / %f", feeling.getType().toString(), feeling.getCurrentValue(), feeling.getCurrentMaxValue());
    }

    @Override
    public void sendUsageImpl(CommandSender sender) {
        StringBuilder builder = new StringBuilder();
        builder.append(EdgeCore.usageColor + "/feelings ");
        builder.append("[" + StringUtils.join(FeelingType.values(), "'") + "]");
        
        sender.sendMessage(builder.toString());
    }

    @Override
    public boolean validArgsRange(String[] args) {
        return args.length <= 2;
    }

}
