package me.georgeawp.staffutils.commands;

import me.georgeawp.staffutils.StaffUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class StaffChatToggleCommand implements CommandExecutor {

    //Create hash map for those who have the command toggled
    Map<String, Long> staffchattoggled;

    public StaffChatToggleCommand() {
        this.staffchattoggled = new HashMap<String, Long>();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            //The sender is a player
            Player p = (Player) sender;

            //The player does not have permission to toggle the staffchat
            if (!p.hasPermission("staffutils.staffchat.toggle")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&' , StaffUtils.getInstance().config.getString("commands.staffchat.")));
            } else {

            //The player has permissions to toggle the staffchat
                    //Check if they are already on the hashmap staffchattoggled

                //Create the 'cooldown'
                final int toggledTime = StaffUtils.getInstance().config.getInt("commands.staffchattoggle.toggledtime");

                if (this.staffchattoggled.containsKey(p.getName()) && this.staffchattoggled.get(p.getName()) > System.currentTimeMillis()) {
                    //Toggle staffchat on
                        staffchattoggled.remove(p.getName()) ;
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&' , StaffUtils.getInstance().config.getString("commands.staffchattoggle.on")));
                        return true;
                    }

                    else if (!this.staffchattoggled.containsKey(p.getName())) {

                        //Toggle staffchat off
                        this.staffchattoggled.put(p.getName(), System.currentTimeMillis() + toggledTime * 1000 * 60);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&' , StaffUtils.getInstance().config.getString("commands.staffchattoggle.off" + " &c" + StaffUtils.getInstance().config.getString("commands.staffchattoggle.cooldown" + " minutes"))));
                        return true;
                    }



            }

        } else {
            //The sender is not a player
        }







        return false;
    }
}
